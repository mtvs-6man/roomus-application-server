package com.sixman.roomus.config.jwt.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FirstFilter implements Filter {

    //filter를 상속 받아서 filter를 구현해 준다.
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("filter1 실행됨");
        filterChain.doFilter(servletRequest,servletResponse);

    }
}
