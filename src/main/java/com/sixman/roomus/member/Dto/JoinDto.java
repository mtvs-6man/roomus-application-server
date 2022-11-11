package com.sixman.roomus.member.Dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class JoinDto {

    @NotEmpty(message = "회원 아이디는 공백일 수 없습니다.")
    @Size(min = 6, message = "회원 아이디는 6자 이상입니다.")
    private String memberId;

    @NotEmpty
    @Size(min = 10)
    private String pwd;
    @NotEmpty
    private String memberName;

    @NotEmpty
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", message = "이메일 형식은 다음과 같습니다. \n examlpe@mail.com")
    private String memberEmail;

    public JoinDto() {
    }

    public JoinDto(String memberId, String pwd, String memberName, String memberEmail) {
        this.memberId = memberId;
        this.pwd = pwd;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    @Override
    public String toString() {
        return "JoinDto{" +
                "memberId='" + memberId + '\'' +
                ", pwd='" + pwd + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                '}';
    }
}
