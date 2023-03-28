package exam.demo.controller;


import exam.demo.entity.Member;
import exam.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("member", new Member());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("member") Member member, Model model) {
        try {
            memberService.join(member);
            return "redirect:/signup-success";
        } catch (IllegalStateException e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/";
        }
    }
}
