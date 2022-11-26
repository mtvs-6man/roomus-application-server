package com.sixman.roomus.config.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sixman.roomus.config.auth.PrincipalDetails;
import com.auth0.jwt.JWT;
import com.sixman.roomus.member.Dto.TokenDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;

@Component
public class JwtConfig {

    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 240; // 30분 사용이 가능함

    public String createToken(PrincipalDetails principalDetails,String key){
        //hmac512를 이용한 알고리즘
        String jwtToken = JWT.create()
                .withSubject("RoomUsToken")
                .withExpiresAt(new Date(System.currentTimeMillis()+ACCESS_TOKEN_EXPIRE_TIME)) // 유효시간을 결정함 withExpiresAt(new Date(System.currentTimeMillis()+ 만료시간)) 유효시간을 결정함
                .withClaim("memberNo", principalDetails.getMember().getMemberNo())
                .withClaim("memberId", principalDetails.getMember().getMemberId())
                .sign(Algorithm.HMAC512(key));

        return jwtToken;
    }

    public TokenDTO decryption(String token) throws JsonProcessingException {

        token = token.substring(token.lastIndexOf(" ") + 1);
        String[] splitJwt = token.split("\\.");

        Base64.Decoder decoder = Base64.getDecoder();
        ObjectMapper mapper = new ObjectMapper();

        String payload = new String(decoder.decode(splitJwt[1].getBytes()));
        TokenDTO payloadDTO = mapper.readValue(payload, TokenDTO.class);

        return payloadDTO;
    }
}
