package com.sixman.roomus.config.auth;


import com.sixman.roomus.member.command.domain.model.Member;
import com.sixman.roomus.member.command.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// http://localhostL8000/login 요청시 실행되어야 하지만 login을 막았기 때문에 동작이 안됨으로 filter를 통해 실행을 해야한다.
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public PrincipalDetailsService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberIdAndState(username,"Y");

        return new PrincipalDetails(member);
    }
}
