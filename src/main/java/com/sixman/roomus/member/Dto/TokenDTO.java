package com.sixman.roomus.member.Dto;

public class TokenDTO {

    private String sub;
    private String memberNo;
    private String exp;
    private String memberId;

    public TokenDTO() {
    }
    public TokenDTO(String sub, String memberNo, String exp, String memberId) {
        this.sub = sub;
        this.memberNo = memberNo;
        this.exp = exp;
        this.memberId = memberId;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "TokenDTO{" +
                "sub='" + sub + '\'' +
                ", memberNo='" + memberNo + '\'' +
                ", exp='" + exp + '\'' +
                ", memberId='" + memberId + '\'' +
                '}';
    }
}
