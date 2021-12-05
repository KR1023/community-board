package mybatis.communityBoard.controller.user;

import lombok.RequiredArgsConstructor;
import mybatis.communityBoard.dto.board.AddBoardRequestDTO;
import mybatis.communityBoard.dto.board.ModifyBoardRequestDTO;
import mybatis.communityBoard.service.board.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardViewController {

    private final BoardService boardService;

    /**
     * 자유게시판 목록
     * @param pageNum
     * @param keyword
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String boardList(@RequestParam (value="pageNum", required = false) Integer pageNum,
                            @RequestParam (value="keyword", required = false) String keyword, Model model) {
        model.addAttribute("keyword",keyword);
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("boardCnt", boardService.countAll(keyword));
        model.addAttribute("list", boardService.boardList(keyword, pageNum));
        return "user/boardList";
    }

    /**
     * 공지사항 목록
     * @param pageNum
     * @param keyword
     * @param model
     * @return
     */
    @GetMapping("/notice")
    public String noticeList(@RequestParam (value="pageNum", required = false) Integer pageNum,
                             @RequestParam (value="keyword", required = false) String keyword, Model model){
        model.addAttribute("keyword",keyword);
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("boardCnt", boardService.countNotice(keyword));
        model.addAttribute("list", boardService.noticeList(keyword, pageNum));
        return "user/noticeList";
    }

    /**
     * 사이드 메뉴 테스트
     * @return
     */
    @GetMapping("/side")
    public String side(){
        return "user/side/side";
    }

    /**
     * 글 작성 폼
     * @return
     */
    @GetMapping("/add-form")
    public String addForm(){
        return "user/boardAddForm";
    }

    /**
     * 글 작성
     * @param request
     * @return
     */
    @PostMapping("/add")
    public String addArticle(HttpServletRequest request){
        String title = request.getParameter("title");
        String contents = request.getParameter("contents");
        Long id = Long.parseLong(request.getParameter("id"));
        AddBoardRequestDTO dto = new AddBoardRequestDTO(title, contents, id);
        boardService.addBoard(dto);
        return "redirect:/board/list";
    }

    /**
     * 글 상세
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/detail/{id}")
    public String detailBoard(@PathVariable("id") Long id, Model model){
        model.addAttribute("article", boardService.detailBoard(id));
        return "user/boardDetail";
    }

    /**
     * 공지사항 상세
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/notice/{id}")
    public String detailNotice(@PathVariable ("id") Long id, Model model){
        model.addAttribute("article", boardService.detailBoard(id));
        return "user/noticeDetail";
    }

    /**
     * 글 수정 폼
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/mod-form/{id}")
    public String modifyBoard(@PathVariable("id") Long id, Model model){
        model.addAttribute("article", boardService.detailBoard(id));
        return "user/boardModForm";
    }

    /**
     * 글 수정
     * @param request
     * @return
     */
    @PostMapping("/modify")
    public String modifyBoard(HttpServletRequest request){
        Long boardId = Long.parseLong(request.getParameter("boardId"));
        String title = request.getParameter("title");
        String content = request.getParameter("contents");
        ModifyBoardRequestDTO dto = new ModifyBoardRequestDTO(boardId, title, content);
        boardService.modifyBoard(dto);
        return "redirect:/board/detail/" + boardId;
    }

    /**
     * 글 삭제
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public String deleteBoard(@PathVariable ("id") Long id){
        boardService.deleteBoard(id);
        return "redirect:/board/list";
    }

}
