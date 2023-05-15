package exam.demo.service;


import exam.demo.entity.Member;
import exam.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SignupService {
    @Autowired
    MemberRepository memberRepository;

    public Long join(Member member){

        memberRepository.save(member);
        return member.getMember_id();
    }


    public List<Member> findMembers(){
        return memberRepository.findAll();
    }


}
