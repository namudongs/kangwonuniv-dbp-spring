package exam.demo.service;

import exam.demo.entity.Member;
import exam.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {
    @Autowired
    private final MemberRepository memberRepository;

    public boolean login(Member member) {

        Member findUser = memberRepository.findByName(member.getName());

        if(findUser == null){
            return false;

        }

        if(! findUser.getPassword().equals(member.getPassword())){
            return false;
        }
        return true;

    }

};