package com.sixman.roomus.member.command.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sixman.roomus.commons.vo.DateSet;
import com.sixman.roomus.member.Dto.JoinDto;
import com.sixman.roomus.member.command.domain.model.Member;
import com.sixman.roomus.member.command.domain.model.MemberInfo;
import com.sixman.roomus.member.command.domain.model.Role;
import com.sixman.roomus.member.command.repository.MemberRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Base64;
import java.util.Map;

@Service
public class MemberService {

    private MemberRepository memberRepository;

    private BCryptPasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder passwordEncoder){
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String memberSinup(JoinDto joinDto){
        LocalDate now = LocalDate.now();

        Member findId = memberRepository.findByMemberId(joinDto.getMemberId());

        if(findId != null){
            return "중복";
        }

        Member member = new Member();

        member.setMemberId(joinDto.getMemberId());
        member.setPassword(passwordEncoder.encode(joinDto.getPwd()));
        member.setMemberInfo(new MemberInfo(joinDto.getMemberName(),joinDto.getMemberEmail()));
        member.setDate(new DateSet(now.toString()));
        member.setRole(Role.USER);

        Member value = memberRepository.save(member);

        if(value.equals(null)){
            return "실패";
        }


        return "회원가입 완료";
    }

    public boolean passConfirm(String token, String confirmPass) throws JsonProcessingException {

        Base64.Decoder decoder = Base64.getDecoder();
        ObjectMapper mapper = new ObjectMapper();

        token = token.substring(token.lastIndexOf(" ") + 1);
        String[] splitJwt = token.split("\\.");

        String payload = new String(decoder.decode(splitJwt[1].getBytes()));
        Map<String, String> payloadMap = mapper.readValue(payload, Map.class);


        Member member = memberRepository.findByMemberId(payloadMap.get("memberId"));

        if(!passwordEncoder.matches(confirmPass, member.getPassword())){
            return false;
        }

        return true;
    }
}
