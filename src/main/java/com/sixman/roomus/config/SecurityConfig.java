package com.sixman.roomus.config;

import com.sixman.roomus.config.jwt.JwtConfig;
import com.sixman.roomus.config.jwt.filter.JwtAuthenticationFilter;
import com.sixman.roomus.config.jwt.filter.JwtAuthorizationFilter;
import com.sixman.roomus.config.jwt.filter.JwtFilter;
import com.sixman.roomus.member.command.domain.model.Member;
import com.sixman.roomus.member.command.domain.model.Role;
import com.sixman.roomus.member.command.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static String AUTH_KEY;
    private final MemberRepository memberRepository;
    private final JwtConfig jwtConfig;
    public SecurityConfig(@Value("${jwt.secret}") String key, MemberRepository memberRepository, JwtConfig jwtConfig) {
        this.AUTH_KEY = key;
        this.memberRepository = memberRepository;
        this.jwtConfig = jwtConfig;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // addfilter는 시큐리티에서 지원하는 필터만 등록이 가능하기 때문에 security 필터가 아닌 경우 요청전에 처리를 하던가 요청 후에 처리를 해야 한다.
        // 시큐리티 필터는 아래와 같은 방법을 사용하지 않을 수도 있
        // Basic필터는 시큐리티의 기본 필터로 해당 필터 동작 이전 혹은 이에 하고 싶은 필터를 넣어주면 된다.
        //http.addFilterBefore(new JwtFilter(), SecurityContextPersistenceFilter.class);// security의 SecurityContextPersistenceFilter 동작 전에 실행한다.

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션을 사용하지 않겠다는 의미
                .and()
                .addFilter(corsFilter()) // 확인 필요 위와 (@CrossOrigin(인증x), 인증이 필요하면 해당 스프링 시큐리티에 필터를 거치도록 한다.)
                .formLogin().disable()
                .httpBasic().disable()
                // 아래의 authenticationManager는 WebSecurityConfigurerAdapter가 가지고 있어서 별도의 선언 없이 사용이 가능하다.
                .addFilter(new JwtAuthenticationFilter(authenticationManager(),AUTH_KEY, jwtConfig)) // 로그인 요청시 해당 필터를 거치도록 설정 | 필수 파라미터 AuthenticationManger을 필수로 등록해야됨
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), AUTH_KEY, memberRepository))
                .authorizeRequests()
                .antMatchers("/member/**") // 회원용
                .hasAnyAuthority(Role.USER.getValue(), Role.SELLER.getValue(), Role.ADMIN.getValue())
                .antMatchers("/seller/**") // 판매자
                .hasAnyAuthority(Role.SELLER.getValue(), Role.ADMIN.getValue())
                .antMatchers("/admin/**")// 관리자
                .hasAnyAuthority(Role.ADMIN.getValue())
                .antMatchers("/**").permitAll()
                .anyRequest().permitAll(); //denyAll();//위의 요청을 제외한 모든 요청을

    }

    @Bean
    CorsFilter corsFilter() {
        CorsConfiguration configuration = new CorsConfiguration();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        configuration.setAllowCredentials(true); // 서버가 응답할 때 json을 자바스크립트에서 처리할 수 있도록 할 것 인지 설정
        configuration.addAllowedOrigin("*"); // 모든 ip에 대한 응답을 허용한
        configuration.addAllowedHeader("*"); // 모든 Header에 대한 응답을 허용함
        configuration.addAllowedMethod("*"); // 모듬 http method 요청에 대한 응답을 허용함
        source.registerCorsConfiguration("/**", configuration);

        return new CorsFilter(source);
    }
}
