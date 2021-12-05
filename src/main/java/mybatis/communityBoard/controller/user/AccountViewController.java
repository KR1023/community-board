package mybatis.communityBoard.controller.user;

import lombok.RequiredArgsConstructor;
import mybatis.communityBoard.dto.account.AddAccountRequestDTO;
import mybatis.communityBoard.dto.account.ModifyAccountRequestDTO;
import mybatis.communityBoard.service.account.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountViewController {

    private final AccountService accountService;

    /**
     * 회원가입 폼
     * @return
     */
    @GetMapping("/register-form")
    public String registerForm(){
        return "user/addAccount";
    }

    /**
     * 회원 가입
     * @param request
     * @return
     */
    @PostMapping("/addAccount")
    public String addAccount(HttpServletRequest request){
        String userId = request.getParameter("userId");
        String userPwd = request.getParameter("userPwd");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        accountService.addAccount(new AddAccountRequestDTO(userId, userPwd, username, email));

        return "redirect:/account/login-form";
    }

    /**
     * 로그인 폼
     * @return
     */
    @GetMapping("/login-form")
    public String loginForm(){
        return "user/userLoginForm";
    }

    /**
     * 로그인
     * @param request
     * @param rAttr
     * @return
     */
    @PostMapping("/login")
    public String login(HttpServletRequest request, RedirectAttributes rAttr){
        String id = request.getParameter("userId");
        String pwd = request.getParameter("userPwd");
        Long pk = accountService.getPk(id);
        int on = accountService.userLogin(id, pwd);

        if(on == 1){
            HttpSession session = request.getSession();
            session.setAttribute("pk", pk);
            session.setAttribute("account", id);
            session.setAttribute("isLogOn", "true");
            rAttr.addFlashAttribute("id", id);
            return "redirect:/board/list";
        }else{
            rAttr.addFlashAttribute("result","failed");
            return "redirect:/account/login-form";
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
        return "redirect:/board/list";
    }

    /**
     * 정보 수정 페이지
     * @return
     */
    @GetMapping("/mypage")
    public String myPage(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Long id = (Long)session.getAttribute("pk");
        model.addAttribute("info",accountService.detailAccount(id));
        return "user/account/myPage";
    }

    /**
     * 정보 수정
     * @param request
     * @return
     */
    @PostMapping("/mod-acc")
    public String modifyAccount(HttpServletRequest request){
        String username = request.getParameter("username");
        String pwd = request.getParameter("userPwd");
        String email = request.getParameter("email");
        Long pk = Long.parseLong(request.getParameter("pk"));
        ModifyAccountRequestDTO dto = new ModifyAccountRequestDTO(pk, username, pwd, email);
        accountService.modifyAccount(dto);
        return "redirect:/account/mypage";
    }

    /**
     * 회원 탈퇴
     * @param request
     */
    @PostMapping("/delete")
    public ResponseEntity deleteAcc(HttpServletRequest request){
        HttpSession session = request.getSession();
        Long id = Long.parseLong(request.getParameter("id"));
        accountService.deleteAccount(id);
        session.removeAttribute("account");
        session.removeAttribute("isLogOn");
        session.invalidate();
        return new ResponseEntity("", HttpStatus.OK);
    }
}
