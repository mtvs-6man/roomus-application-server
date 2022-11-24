package com.sixman.roomus.member.query.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sixman.roomus.config.jwt.JwtConfig;
import com.sixman.roomus.member.Dto.TokenDTO;
import com.sixman.roomus.member.query.dto.UserSerchDTO;
import com.sixman.roomus.member.query.repository.MemberDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberViewService {

    private MemberDataRepository memberDataRepository;
    private JwtConfig jwtConfig;

    public MemberViewService(MemberDataRepository memberDataRepository, JwtConfig jwtConfig) {
        this.memberDataRepository = memberDataRepository;
        this.jwtConfig = jwtConfig;
    }

    public List<UserSerchDTO> findUser(String token, String userName) throws JsonProcessingException {

        TokenDTO decryToken = jwtConfig.decryption(token);
        Boolean memberData = memberDataRepository.existsByMemberNo(Integer.parseInt(decryToken.getMemberNo()));

        if(!memberData){
            throw new RuntimeException("유효하지 않은 사용자 입니다.");
        }
        List<UserSerchDTO> result = memberDataRepository.findUser(userName);

        return result;
    }
}
