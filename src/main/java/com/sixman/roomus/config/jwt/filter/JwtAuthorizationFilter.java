package com.sixman.roomus.config.jwt.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sixman.roomus.config.auth.PrincipalDetails;
import com.sixman.roomus.member.command.domain.model.Member;
import com.sixman.roomus.member.command.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 시큐리티가 가지고 있는 필터중 filter basicAuthenticationFilter라는 것이 있음
// 해당 필터는 권한 인증이나 필요한 특정 주소를 요청했을 때 위 필터를 무조건 타게 된다.
// 만약 권한 인증이 필요한 주소가 아니라면 해당 필터는 동작하지 않게된다.
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final String AUTH_KEY;
    private final MemberRepository memberRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, @Value("${jwt.secret}") String key, MemberRepository memberRepository) {
        super(authenticationManager);
        this.AUTH_KEY = key;
        this.memberRepository = memberRepository;
    }
    // 인증이나 권한이 필요한 주소요청이 있을 때 해당 필터를 타게 된다.
    @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String jwtHeader = request.getHeader("Authorization");

        // header가 있는지 확인
        if(jwtHeader == null || !jwtHeader.startsWith("Bearer")){
            chain.doFilter(request,response);
            return;
        }

        // jwt 토큰을 검증해서 정상적인 사용자 인지 확인하기
        String jwtToken = request.getHeader("Authorization").replace("Bearer ", "");
        String memberId = JWT.require(Algorithm.HMAC512(AUTH_KEY)).build().verify(jwtToken).getClaim("memberId").asString();

        if(memberId != null){
            Member memberEntiry = memberRepository.findByMemberId(memberId);
            PrincipalDetails principalDetails = new PrincipalDetails(memberEntiry);

            // Jwt 토큰 서명을 통해서 정상인 경우 authentication의 객체를 만들어 준다.
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null,principalDetails.getAuthorities());

            // 강제로 시큐리티의 세션에 접근하여 Authentication 객체를 저장한다.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request,response);
    }
}
