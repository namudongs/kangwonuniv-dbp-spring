package exam.demo.controller;


import exam.demo.entity.Member;
import exam.demo.repository.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;


@Controller
public class HomeController {


    MemberRepository memberRepository;
    @GetMapping("/")
    public String showHome(){
        return "home";
    }
}
