package com.sixman.roomus.config.auth;

import com.sixman.roomus.member.command.domain.model.Member;
import com.sixman.roomus.member.command.domain.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {

    private Member member;

    public PrincipalDetails() {
    }

    public PrincipalDetails(Member member) {
        this.member = member;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 회원이 가진 권한의 정보를 list 형태로 불러와서 authorities list에 추가함
        member.getRoleList().forEach(r -> authorities.add(() -> r));
        System.out.println("잘들어감? : " + authorities);
        authorities.forEach(r -> System.out.println("role : " + r.getAuthority()));
        System.out.println("authorities test : " + authorities.iterator().next().getAuthority());
        return authorities;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getMemberId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
