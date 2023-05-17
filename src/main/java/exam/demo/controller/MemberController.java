package exam.demo.controller;

import exam.demo.constant.SessionConst;
import exam.demo.entity.Member;
import exam.demo.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class MemberController {

    @Autowired
    private SignupService signupService;




    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("member", new Member());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("member") Member member, Model model) {
        try {
            signupService.join(member);
            return "redirect:/";
        } catch (IllegalStateException e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/";
        }
    }
}
