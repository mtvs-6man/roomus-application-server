package com.sixman.roomus.config.jwt.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sixman.roomus.config.auth.PrincipalDetails;
import com.sixman.roomus.member.Dto.LoginDto;
import com.sixman.roomus.member.Dto.LoginRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;


// 시큐리티에서 UsernamePasswordAuthenticationFilter가 있으며 login 요청시 username, password가 post로 전송되면 userNamePasswordAuthenticationFilter가 동작을 하게 된다.
// 그러나 securityConfig에서 formlogin을 disable 처리가 되어 있기 때문에 filter에 별도로 등록을 해주어야 한다.
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter { // url/login으로 요청이 들어오면 해당 요청을 잡아서 아래의 attemptAuthentication를 이용해 실행함

    //로그인시 처리하도록 동작된다.
    private final AuthenticationManager authenticationManager;
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30; // 30분 사용이 가능함
    private String key;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, String key) {
        this.authenticationManager = authenticationManager;
        this.key = key;
    }

    // (/login) 요청시 동작되는 함수이다.
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

//        StringBuilder stringBuilder = new StringBuilder();
//        BufferedReader br = null;
//      request의 buffer 값을 확인해서 옴
//        String line = "";
//
//        try {
//            //body내용 inputstream에 담는다.
//            InputStream inputStream = request.getInputStream();
//            if (inputStream != null) {
//                br = new BufferedReader(new InputStreamReader(inputStream));
//                //더 읽을 라인이 없을때까지 계속
//                while ((line = br.readLine()) != null) {
//                    System.out.println("line : " + line);
//                    stringBuilder.append(line);
//                }
//            }else {
//                System.out.println("데이터 없음");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        // 1. username, password 받아는다.
        try {
//            BufferedReader br = request.getReader(); // 버퍼 스트림을 읽어 오기 위함
//            String input = null;
//            while ((input = br.readLine()) != null){
//                System.out.println(input);
//            }
            ObjectMapper loginObj = new ObjectMapper();
            LoginDto loginDto = loginObj.readValue(request.getInputStream(), LoginDto.class);
            // 사용자가 입력한 정보를 받아서 토큰으로 만들어줌
            // 사용자의 아이디 패스워드를 넣어 임시 토큰을 발행함
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginDto.getMemberId(), loginDto.getMemberPwd());

            // pincipalDetailsService의 loadUserByUsername에 토큰을 넣으면 사용자의 아이디를 뽑아서 db와 비교를 함
            // 사용자의 정보를 확인하여 값 인증되면 autentication의 토큰을 발행함
            // authentication에 로그인한 사용자의 정보가 담기게 된다.
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            // odject를 반환하기 때문에 인증된 사용자의 정보를 다운캐스팅 해서 받아준다.
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            // authentication 객체가 session 영역에 저장된다.
            // 리턴의 이유는 권한 관리를 security가 대신 해주기 때문에 편하려고 하는 것이다.
            // 굳이 jwt 토큰을 사용하면서 세션을 만들 이유가 없으다 권한 처리 때문에 사용하는 것이다.
            return authentication;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 2. 정산인 경우 로그인을 시도하여 authenticationManager로 로그인 시도를 하면 principalDetailsService가 호출 되도록 한다.
        //       호출이 되면 자동으로 loadUserByUsername()함수가 실행된다.
        // 3. PrincialDetails를 세션에 담는다.(권한 관리를 위해서 처리 한다.)
        // 4. jwt 토큰을 생성하여 발행한다.
    }


    @Override // attemptAuthentication 실행 후 인증이 정상적인 경우 아래의 함수가 실행됨
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        // jwt 토큰을 만들어서 request요청한 사용자에게 jwt토큰을 response 해주면된다.
        // 위의 attemptAuthentication가 실행된 후 authResult의 매개변수로 전달되어 해당 객체의 인증된 사용자의 값을 읽어 오는 것이 가능함
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
        // 토큰을 발행ㅔ
        //hmac512를 이용한 알고리즘
        String jwtToken = JWT.create()
                .withSubject("RoomUsToken")
                .withExpiresAt(new Date(System.currentTimeMillis()+ACCESS_TOKEN_EXPIRE_TIME)) // 유효시간을 결정함 withExpiresAt(new Date(System.currentTimeMillis()+ 만료시간)) 유효시간을 결정함
                .withClaim("memberNo", principalDetails.getMember().getMemberNo())
                .withClaim("memberId", principalDetails.getMember().getMemberId())
                .sign(Algorithm.HMAC512(key));

        LoginRequestDTO responseDTO = new LoginRequestDTO();
//        responseDTO.setUserName(principalDetails.getMember().getMemberInfo().getName());
        responseDTO.setUserRole(principalDetails.getMember().getRole());
        responseDTO.setUserNo(principalDetails.getMember().getMemberNo());


        ObjectMapper objectMapper = new ObjectMapper();
        String responseValue = objectMapper.writeValueAsString(responseDTO);

        response.addHeader("Authorization", "Bearer " + jwtToken);
        response.getOutputStream().println(responseValue);
    }

}
