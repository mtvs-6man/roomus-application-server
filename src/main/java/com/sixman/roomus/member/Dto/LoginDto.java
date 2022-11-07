package com.sixman.roomus.member.Dto;

public class LoginDto {
    private String memberId;
    private String memberPwd;

    public LoginDto() {
        super();
    }

    public LoginDto(String memberId, String memberPwd) {
        this.memberId = memberId;
        this.memberPwd = memberPwd;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberPwd() {
        return memberPwd;
    }

    public void setMemberPwd(String memberPwd) {
        this.memberPwd = memberPwd;
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                "memberId='" + memberId + '\'' +
                ", memberPwd='" + memberPwd + '\'' +
                '}';
    }
}
