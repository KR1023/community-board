package mybatis.communityBoard.controller.adm;

import lombok.RequiredArgsConstructor;
import mybatis.communityBoard.dto.board.AddBoardRequestDTO;
import mybatis.communityBoard.dto.board.ModifyBoardRequestDTO;
import mybatis.communityBoard.service.account.AccountService;
import mybatis.communityBoard.service.board.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/adm")
@RequiredArgsConstructor
public class AdmController {

    private final AccountService accountService;
    private final BoardService boardService;

    /**
     * 회원 목록
     * @param pageNum
     * @param pageSize
     * @param model
     * @return
     */
    @GetMapping("/account/list")
    public String accountList(@RequestParam (value = "pageNum", required = false) String pageNum,
                              @RequestParam (value = "pageSize", required = false) String pageSize,
                              @RequestParam (value = "keyword", required = false) String keyword, Model model){
        Integer num = 0;

        if(pageNum != null){
            num = Integer.parseInt(pageNum);
        }
        model.addAttribute("keyword", keyword);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("accCnt",accountService.countAll(keyword));
        model.addAttribute("list", accountService.accList(keyword, pageNum, pageSize));
        return "adm/account/accountList";
    }

    /**
     * 회원 상세
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/detail/{id}")
    public String detailAccount(@PathVariable("id") Long id, Model model){
        model.addAttribute("account", accountService.detailAccount(id));
        return "adm/account/detailAccount";
    }

    /**
     * 관리자 로그인 폼
     * @return
     */
    @GetMapping("/login-form")
    public String loginForm(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(!StringUtils.isEmpty(session.getAttribute("account"))){
            if(!session.getAttribute("account").equals("admin")){
                session.invalidate();
            }
        }

        return "adm/account/loginForm";
    }

    /**
     * 관리자 로그인
     * @param request
     * @return
     */
    @PostMapping("/login")
    public String login(HttpServletRequest request, RedirectAttributes rAttr){

        String id = request.getParameter("userId");
        String pwd = request.getParameter("userPwd");
        Long pk = accountService.getPk(id);
        int on = accountService.login(id, pwd);
        if(on == 1){
            HttpSession session = request.getSession();
            session.setAttribute("pk", pk);
            session.setAttribute("account", id);
            session.setAttribute("isLogOn", "true");
            rAttr.addFlashAttribute("id", id);
            return "redirect:/adm/account/list";
        }else{
            rAttr.addFlashAttribute("result","failed");
            return "redirect:/adm/login-form";
        }
    }

    /**
     * 로그아웃
     * @param request
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("account");
        session.removeAttribute("isLogOn");
        session.invalidate();
        return "redirect:/adm/login-form";
    }

    /**
     * 공지사항 목록
     * @param pageNum
     * @param keyword
     * @param model
     * @return
     */
    @GetMapping("/notice/list")
    public String noticeList(@RequestParam (value="pageNum", required = false) Integer pageNum,
                             @RequestParam (value="keyword", required = false) String keyword, Model model){
        model.addAttribute("keyword",keyword);
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("boardCnt", boardService.countNotice(keyword));
        model.addAttribute("list", boardService.noticeList(keyword, pageNum));
        return "adm/board/admNoticeList";
    }

    /**
     * 자유게시판 목록
     * @param pageNum
     * @param keyword
     * @param model
     * @return
     */
    @GetMapping("/board/list")
    public String boardList(@RequestParam (value="pageNum", required = false) Integer pageNum,
                            @RequestParam (value="keyword", required = false) String keyword, Model model) {
        model.addAttribute("keyword",keyword);
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("boardCnt", boardService.countAll(keyword));
        model.addAttribute("list", boardService.boardList(keyword, pageNum));
        return "adm/board/admBoardList";
    }

    @GetMapping("/board/detail/{id}")
    public String detailBoard(@PathVariable("id") Long id, Model model){
        model.addAttribute("article", boardService.detailBoard(id));
        return "adm/board/admBoardDetail";
    }

    /**
     * 공지사항 작성 폼
     * @return
     */
    @GetMapping("/notice/add-form")
    public String noticeAddForm(){
        return "/adm/board/admNoticeAddForm";
    }

    @PostMapping("/notice/add")
    public String addNotice(HttpServletRequest request){
        String title = request.getParameter("title");
        String contents = request.getParameter("contents");
        Long id = Long.parseLong(request.getParameter("id"));
        AddBoardRequestDTO dto = new AddBoardRequestDTO(title, contents, id);
        boardService.addNotice(dto);
        return "redirect:/adm/notice/list";
    }
    
    /**
     * 공지사항 상세
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/notice/detail/{id}")
    public String detailNotice(@PathVariable ("id") Long id, Model model){
        model.addAttribute("article", boardService.detailBoard(id));
        return "adm/board/admNoticeDetail";
    }

    /**
     * 공지사항 수정 폼
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/notice/mod-form/{id}")
    public String modifyBoard(@PathVariable("id") Long id, Model model){
        model.addAttribute("article", boardService.detailBoard(id));
        return "adm/board/admNoticeModForm";
    }

    /**
     * 공지사항 수정
     * @param request
     * @return
     */
    @PostMapping("/notice/modify")
    public String modifyBoard(HttpServletRequest request){
        Long boardId = Long.parseLong(request.getParameter("boardId"));
        String title = request.getParameter("title");
        String content = request.getParameter("contents");
        ModifyBoardRequestDTO dto = new ModifyBoardRequestDTO(boardId, title, content);
        boardService.modifyBoard(dto);
        return "redirect:/adm/notice/detail/" + boardId;
    }

    /**
     * 공지사항 삭제
     * @param id
     * @return
     */
    @GetMapping("/notice/delete/{id}")
    public String deleteBoard(@PathVariable ("id") Long id){
        boardService.deleteBoard(id);
        return "redirect:/adm/notice/list";
    }

}
