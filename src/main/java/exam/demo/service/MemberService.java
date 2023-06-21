package exam.demo.service;

import exam.demo.entity.Member;
import exam.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //회원가입 메서드
    public void saveMember(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
    }

    //중복확인 메서드
    private void validateDuplicateMember(Member member) {
        memberRepository.findByUserName(member.getUsername())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 가입된 회원입니다.");
                });
    }

    public Member getMemberByUsername(String username) {
        Optional<Member> optionalMember = memberRepository.findByUserName(username);
        return optionalMember.orElse(null);
    }

    public Member getMemberByMemberId(Long id){
        Member member = memberRepository.findByMemberId(id);
        return member;
    }


    //회원탈퇴 메서드
    public void withdrawMember(String username) {
        Member member = getMemberByUsername(username);
        memberRepository.delete(member);
    }

    //비밀번호 변경 메서드
    public void changePassword(String username, String currentPassword, String newPassword){
        Member member = getMemberByUsername(username);
        if (passwordEncoder.matches(currentPassword, member.getPassword())) {
            member.updatePassword(passwordEncoder.encode(newPassword));
        } else {
            throw new IllegalArgumentException("현재 비밀번호를 잘못 입력했습니다.");
        }
    }

    public void makeTempPassword(String userName, String tempPassword){
        Member member = getMemberByUsername(userName);

        member.updatePassword(passwordEncoder.encode(tempPassword));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> optionalMember = memberRepository.findByUserName(username);

        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            return member;
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }

    //회원가입시 아이디 중복확인을 위한 메서드
    public boolean isUserNameDuplicate(String userName) {
        Optional<Member> member = memberRepository.findByUserName(userName);
        return member.isPresent();
    }


    public String findMyIdByEmail(String email){
        Member member = memberRepository.findMemberByEmail(email);
        if (member == null) {
            // 이메일로 등록된 아이디가 없는 경우 처리
            return null;
        }

        return member.getUsername();

    }
}
