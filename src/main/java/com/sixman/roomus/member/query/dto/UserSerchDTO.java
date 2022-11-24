package com.sixman.roomus.member.query.dto;


import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;



public class UserSerchDTO {

    private Integer memberNo;
    private String memberId;
    private String memberName;

    public UserSerchDTO() {
    }

    public UserSerchDTO(Integer memberNo, String memberId, String memberName) {
        this.memberNo = memberNo;
        this.memberId = memberId;
        this.memberName = memberName;
    }

    public Integer getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(Integer memberNo) {
        this.memberNo = memberNo;
    }


    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    @Override
    public String toString() {
        return "UserSerch{" +
                "memberNo=" + memberNo +
                ", memberId='" + memberId + '\'' +
                ", memberName='" + memberName + '\'' +
                '}';
    }
}
