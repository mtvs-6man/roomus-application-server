
package com.sixman.roomus.commons.util;

import com.sixman.roomus.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityContextUtil {

    public Integer getMemberNo() {
        PrincipalDetails principal = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("현재 로그인한 유저 : " + principal.getMember().getMemberNo());

        return principal.getMember().getMemberNo();
    }
}

