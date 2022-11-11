package com.sixman.roomus.config.jwt.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//아래의 함수는 서버 실행시 동작된다.
@Configuration
public class FilterConfig {
    // filter는 아래의 함수를 이어서 여러개를 등록하여 요청전 후 처리를 할 수 있다.
    // 커스텀을 해서 필터를 등록하고자 하는 경우 시큐리티 이후에 동작하기 때문에 직접 securityFilter에 등록을 해주어야 한다.
    @Bean // 시큐리티에 들어갈 필터를 직접 구현함
    public FilterRegistrationBean<FirstFilter> firstFilter(){
        //요청이 들어 올때 마다 아래의 필터가 동작된다.
        FilterRegistrationBean<FirstFilter> bean = new FilterRegistrationBean<>(new FirstFilter());
        bean.addUrlPatterns("/*");
        bean.setOrder(0); // 필터의 동작 순서는 setOrder의 숫자가 낮은 순으로 동작됨
        return bean;
    }

    @Bean
    public FilterRegistrationBean<SecondFilter> secondFilter(){
        //요청이 들어 올때 마다 아래의 필터가 동작된다.
        FilterRegistrationBean<SecondFilter> bean = new FilterRegistrationBean<>(new SecondFilter());
        bean.addUrlPatterns("/*");
        bean.setOrder(1); // 필터의 동작 순서는 setOrder의 숫자가 낮은 순으로 동작됨
        return bean;
    }
}
