package exam.demo.service;


import exam.demo.entity.Member;
import exam.demo.repository.MemberRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;



@Transactional
public class SignupServiceTest {

    @Autowired
    SignupService signupService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {//Given
        Member member = new Member();
        member.setName("kim");
//When
        Long saveId = signupService.join(member);
//Then
        Member findMember = memberRepository.findOne(saveId);
        assertEquals(member.getName(), findMember.getName());
    }
    @Test
    public void 중복_회원_예외() throws Exception {
//Given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
//When
        signupService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> signupService.join(member2));//예외가 발생해야 한다. assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

}
