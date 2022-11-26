
package com.sixman.roomus.commons.util;

import com.sixman.roomus.commons.exception.NotMatchedPassword;
import com.sixman.roomus.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityContextUtil {

    private final PasswordEncoder passwordEncoder;
    public Integer getMemberNo() {
        PrincipalDetails principal = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("현재 로그인한 유저 : " + principal.getMember().getMemberNo());

        return principal.getMember().getMemberNo();
    }

    public void checkPassword(String password){
        PrincipalDetails principal = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!passwordEncoder.matches(password, principal.getMember().getPassword())) {
            throw new NotMatchedPassword("비밀번호가 일치하지 않습니다.");
        }
    }
}

