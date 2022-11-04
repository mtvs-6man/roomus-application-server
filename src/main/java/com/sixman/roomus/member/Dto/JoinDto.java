package com.sixman.roomus.member.Dto;

public class JoinDto {

    private String memberId;
    private String pwd;

    public JoinDto() {
        super();
    }

    public JoinDto(String memberId, String pwd) {
        this.memberId = memberId;
        this.pwd = pwd;
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

    @Override
    public String toString() {
        return "JoinDto{" +
                "memberId='" + memberId + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
