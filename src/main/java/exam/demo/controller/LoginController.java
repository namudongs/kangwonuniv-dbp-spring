package exam.demo.controller;

import exam.demo.constant.SessionConst;

import exam.demo.entity.Member;
import exam.demo.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    @Autowired
    private final LoginService loginService;

    @GetMapping("login")
    public String showLoginForm(){
        return "login";
    }

    @GetMapping("/")
    public String homeLogin(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)
            Member loginMember,
            Model model) {

        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("member", loginMember.getName());

        return "loggedin";
    }

    @PostMapping("/login")
    public String loginId(@ModelAttribute Member member, HttpServletRequest request) {
        if(!loginService.login(member)){
            return "login";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, member);

        return "redirect:/";
    }

};