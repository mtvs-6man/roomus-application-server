package com.sixman.roomus.commons.util;

import com.sixman.roomus.member.command.domain.model.Member;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextUtil {
    public Integer getMemberNo() {
        Member loggedMember = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return loggedMember.getMemberNo();
    }
}
