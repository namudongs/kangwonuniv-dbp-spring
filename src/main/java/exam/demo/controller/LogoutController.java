package exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {

    @RequestMapping(value="logout", method= RequestMethod.GET)
    public String logoutMainGET(HttpServletRequest request) throws Exception{

        HttpSession session = request.getSession();

        session.invalidate();

        return "redirect:/";
    }
}
