package com.sixman.roomus.config.jwt.filter;


import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import java.io.IOException;

public class SecondFilter implements Filter {

    //filter를 상속 받아서 filter를 구현해 준다.
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
