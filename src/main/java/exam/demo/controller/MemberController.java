package exam.demo.controller;

import exam.demo.dto.MemberDto;
import exam.demo.entity.Member;
import exam.demo.entity.Reservation;
import exam.demo.service.MemberService;
import exam.demo.service.ReservationService;
import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Random;


@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final ReservationService reservationService;


    @GetMapping("/signup")
    public String SignupForm(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(MemberDto memberDto) {

        String encryptedPassword = passwordEncoder.encode(memberDto.getPassword());
        memberDto.setPassword(encryptedPassword);

        Member member = new Member(memberDto);
        memberService.saveMember(member);

        return "redirect:/";
    }


    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }
    //로그인 오류시 에러 메세지
    @GetMapping("/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "login";
    }



    //사용자 정보 페이지
    @GetMapping("/mypage")
    public String myPage(Model model, Principal principal) {
        String username = principal.getName();
        Member member = memberService.getMemberByUsername(username);
        List<Reservation> reservations = reservationService.getReserveByMemeberId(member.getMemberId());
        model.addAttribute("member", member);
        model.addAttribute("reservations", reservations);
        return "mypage";
    }

    // 회원 탈퇴 처리
    @GetMapping("/withdraw")
    public String withdrawUser(Authentication authentication) {
        String username = authentication.getName();
        memberService.withdrawMember(username);
        return "redirect:/"; // 회원 탈퇴 후 리디렉션할 URL
    }

    // 비밀번호 변경 처리
    @PostMapping("/changePassword")
    public String changePassword(Authentication authentication,
                                 @RequestParam String currentPassword,
                                 @RequestParam String newPassword) {
        String username = authentication.getName();
        memberService.changePassword(username, currentPassword, newPassword);
        return "redirect:mypage";
    }

    @PostMapping("/checkDuplicate")
    @ResponseBody
    public boolean checkDuplicateUserName(@RequestParam("userName") String userName) {
        // 중복 체크 로직을 수행하여 중복 여부를 반환합니다.
        boolean isDuplicate = memberService.isUserNameDuplicate(userName);
        return isDuplicate;
    }



    @GetMapping("/findMyId")
    public String showFindIdPage() {
        return "findId"; // 아이디 찾기 페이지의 Thymeleaf 템플릿 이름을 반환합니다.
    }

    @PostMapping("/findMyId")
    public String findId(@RequestParam("email") String email, Model model) {
        String memberId = memberService.findMyIdByEmail(email);

        String foundId = memberId != null ? memberId.substring(0, 3) + "*".repeat(memberId.length() - 3) : null;
        model.addAttribute("foundId", foundId);

        return "foundId"; // 아이디를 찾은 후에 보여줄 페이지의 Thymeleaf 템플릿 이름을 반환합니다.
    }




}
