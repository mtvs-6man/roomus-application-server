package com.sixman.roomus.config.jwt.filter;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JwtFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        System.out.println("JWT filter 실행");

        //토큰 : id, pw가 정상적인 경우 토큰을 만들어 주고 응답해주는 방식으로 한다.
        //요청할 때 마다 header에 Authorization에 value값으로 토큰을 가지고 오는 경우 내 서버에서 발행한 토큰이 맞는지 검증하여 인가 처리를 (rsa, hs256)
//        if(req.getMethod().equals("POST")){
//            String headerAuth = req.getHeader("Authorization");
//
//            filterChain.doFilter(req,res);
//        }else{
//            filterChain.doFilter(servletRequest,servletResponse);
//        }
        filterChain.doFilter(req,res);
    }
}
