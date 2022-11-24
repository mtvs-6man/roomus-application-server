package com.sixman.roomus.member.command.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sixman.roomus.commons.vo.DateSet;
import com.sixman.roomus.config.jwt.JwtConfig;
import com.sixman.roomus.member.Dto.JoinDto;
import com.sixman.roomus.member.Dto.TokenDTO;
import com.sixman.roomus.member.command.domain.model.Member;
import com.sixman.roomus.member.command.domain.model.MemberInfo;
import com.sixman.roomus.member.command.domain.model.Relation;
import com.sixman.roomus.member.command.domain.model.Role;
import com.sixman.roomus.member.command.repository.MemberRepository;
import com.sixman.roomus.member.command.repository.RelationRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class MemberService {

    private MemberRepository memberRepository;
    private RelationRepository relationRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private JwtConfig jwtConfig;

    public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder passwordEncoder, JwtConfig jwtConfig, RelationRepository relationRepository){
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtConfig = jwtConfig;
        this.relationRepository = relationRepository;
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


        TokenDTO tokenDTO = jwtConfig.decryption(token);

        Member member = memberRepository.findByMemberId(tokenDTO.getMemberId());

        if(!passwordEncoder.matches(confirmPass, member.getPassword())){
            return false;
        }

        return true;
    }

    public Member chagePass(String token, String changePass) throws JsonProcessingException {

        TokenDTO tokenDTO = jwtConfig.decryption(token);

        Member member = memberRepository.findByMemberId(tokenDTO.getMemberId());
        member.setPassword(passwordEncoder.encode(changePass));

        Member chanageMember = memberRepository.save(member);
        if(member.getPassword().equals(chanageMember.getPassword())){
            return chanageMember;
        }else{
            return null;
        }

    }

    public String memberRanke(String token) throws JsonProcessingException {

        TokenDTO tokenDTO = jwtConfig.decryption(token);
        Member member = memberRepository.findByMemberId(tokenDTO.getMemberId());

        if((member.getRoleList().contains(Role.SELLER.toString())) || (member.getRoleList().contains(Role.ADMIN.toString()))){
            return "판매자 권한을 가진 회원 입니다.";
        }

        member.setRole(Role.SELLER);

        Member changeMember = memberRepository.save(member);


        if(changeMember.getRole().equals(Role.SELLER.toString())){
            return "제작자 신청이 완료 되었습니다.";
        }

        return "제작자 신청에 실패하였습니다.";
    }

    public String followUser(String token, Integer userNo) throws JsonProcessingException {

        TokenDTO tokenDTO = jwtConfig.decryption(token);
        if(Integer.parseInt(tokenDTO.getMemberNo()) == userNo) {
            return "자신을 팔로우 할 수 없습니다.";
        }

        Member relationMember = memberRepository.findByMemberId(tokenDTO.getMemberId());
        Member followMember = memberRepository.findByMemberNo(userNo);

        if(Objects.isNull(relationMember) || Objects.isNull(followMember)){
            return "유효하지 않은 토큰 혹은 사용자 정보 입니다.";
        }

        Relation relation = relationRepository.findByRelationUserAndFollowUser(relationMember, followMember);

        if(!Objects.isNull(relation)){
            return "이미 팔로우가 되어 있습니다.";
        }

        Relation newRelation = new Relation();
        newRelation.setRelationUser(relationMember);
        newRelation.setFollowUser(followMember);

        relationRepository.save(newRelation);

        return "팔로우 되었습니다.";
    }
}
