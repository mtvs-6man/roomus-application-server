package com.sixman.roomus.member.command.domain.model;

import com.sixman.roomus.member.command.domain.model.MemberNullError;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MemberInfo {
    @Column(name = "MEMBER_NAME")
    private String name;
    @Column(name = "MAMBER_PHONE")
    private String phone;
    @Column(name = "MEMBER_GENDER")
    private String gender;
    @Column(name = "MEMBER_AGE")
    private String age;
    @Column(name = "MEMBER_EMAIL")
    private String email;

    public MemberInfo() {
    }

    public MemberInfo(String name, String phone, String gender, String age, String email) {
        setName(name);
        setPhone(phone);
        setGender(gender);
        setAge(age);
        setEmail(email);
    }

    public void setName(String name) {
        if(name == null){
            throw new MemberNullError("회원 이름");
        }

        this.name = name;
    }

    public void setPhone(String phone) {
        if(phone == null){
            throw new MemberNullError("회원 전화번호");
        }

        this.phone = phone;
    }

    public void setGender(String gender) {
        if(gender == null){
            throw new MemberNullError("회원 성별");
        }
        this.gender = gender;
    }

    public void setAge(String age) {
        if(age == null){
            throw new MemberNullError("회원 나이");
        }
        this.age = age;
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

    public String getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    public String getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }
}
