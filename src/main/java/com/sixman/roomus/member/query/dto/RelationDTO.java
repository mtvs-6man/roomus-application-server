package com.sixman.roomus.member.query.dto;

public class RelationDTO {

    private int memberNo;
    private String memberName;
    private String memberEmail;

    public RelationDTO() {
        super();
    }

    public RelationDTO(int memberNo, String memberName, String memberEmail) {
        this.memberNo = memberNo;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
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
        return "RelationDTO{" +
                "memberNo=" + memberNo +
                ", memberName='" + memberName + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                '}';
    }
}
