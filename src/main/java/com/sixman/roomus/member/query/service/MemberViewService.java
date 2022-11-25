package com.sixman.roomus.member.query.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sixman.roomus.config.jwt.JwtConfig;
import com.sixman.roomus.member.Dto.TokenDTO;
import com.sixman.roomus.member.query.dto.MyProductDTO;
import com.sixman.roomus.member.query.dto.UserSerchDTO;
import com.sixman.roomus.member.query.model.MemberData;
import com.sixman.roomus.member.query.model.MyProductData;
import com.sixman.roomus.member.query.repository.MemberDataRepository;
import com.sixman.roomus.member.query.repository.MyProductDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MemberViewService {

    private MemberDataRepository memberDataRepository;
    private MyProductDataRepository myProductDataRepository;
    private JwtConfig jwtConfig;

    public MemberViewService(MemberDataRepository memberDataRepository,MyProductDataRepository myProductDataRepository ,JwtConfig jwtConfig) {
        this.memberDataRepository = memberDataRepository;
        this.myProductDataRepository = myProductDataRepository;
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

    public List<MyProductDTO> serchProduct(String token) throws JsonProcessingException {

        TokenDTO tokenDTO = jwtConfig.decryption(token);
        MemberData memberData = memberDataRepository.findByMemberNo(Integer.parseInt(tokenDTO.getMemberNo()));

        if(Objects.isNull(memberData)) return null;
        List<MyProductDTO> myProductDTO = myProductDataRepository.findMyproductList(memberData.getMemberNo());

        if(myProductDTO.size() <= 0) return null;

        return myProductDTO;

    }
}
