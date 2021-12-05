package mybatis.communityBoard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String main(){
        return "redirect:/board/list";
    }

    @GetMapping("/adm")
    public String admMain(){
        return "redirect:/adm/login-form";
    }
}