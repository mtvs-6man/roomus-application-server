package com.sixman.roomus.member.command.domain.model;

import com.sixman.roomus.member.command.domain.model.MemberNullError;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MemberInfo {
    @Column(name = "MEMBER_NAME")
    private String name;

    @Column(name = "MEMBER_EMAIL")
    private String email;

    public MemberInfo() {
    }

    public MemberInfo(String name, String email) {
        setName(name);
        setEmail(email);
    }

    public void setName(String name) {
        if(name == null){
            throw new MemberNullError("회원 이름");
        }

        this.name = name;
    }

    public void setEmail(String email) {
        if(email == null){
            throw new MemberNullError("회원 이메일");
        }
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
}
