package com.sixman.roomus.member.command.domain.model;

import com.sixman.roomus.commons.vo.DateSet;
import com.sixman.roomus.member.command.domain.model.vo.Role;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "MEMBER_INFO")
@SequenceGenerator(
        name = "MEMBER_SEQUENCE", // 식별자 생성 이름
        sequenceName = "MEMBER_NO_SEQ", // 데이터 베이스 시퀀스 이름 기본값은 hibernate_sequence 이다
        initialValue = 1, //ddl 생성 시에만 사용되며 ddl을 생성할 때 처음 시작하는 수로 기본 값은 1이다.
        allocationSize = 1   //allocationSize 시퀀스 한 번 호출로 증가하는 수로 기본값은 50이다.
        // catalog, schema 데이터 베이스 catelog, schema 이름
        // 매핑할 ddl
)
public class Member {
    @Id
    @Column(name = "MEMBER_NO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQUENCE")
    private int memberNo;

    @Column(name = "MEMBER_ID", length = 30, unique = true)
    private String memberId;

    @Column(name = "MEMBER_PASS", length = 100)
    private String password;

    @Embedded
    private MemberInfo memberInfo;

    @Column (name = "MEMBER_ROLE", length = 10)
    private Role role;

    @Column(name = "MEMBER_STATE", length = 1)
    private String state; // 나중에 치환시키는 클래스 적용 예정

    @Embedded
    @AttributeOverride(name="date", column = @Column(name = "REGIST_DATE"))
    private DateSet date; // 날짜 저장 방식 확인 필방 2022-11-11 방식으로 인코딩 설정함k

    @Embedded
    @AttributeOverride(name="date", column = @Column(name = "MODIFY_DATE"))
    private DateSet modifyDate;

    @Embedded
    @AttributeOverride(name="date", column = @Column(name = "DELETE_DATE"))
    private DateSet
            deleteDate;

    //User의 권한 목록을 가져오기 위함
    public List<String> getRoleList(){
        if(this.role.getValue().length()>0){
            return Arrays.asList(this.role.getValue().split(","));
        }
        return new ArrayList<>();
    }


    public Member() {
    }

    public Member(int memberNo, String memberId, String password, MemberInfo memberInfo, Role role, String state, DateSet date, DateSet modifyDate, DateSet deleteDate) {
        this.memberNo = memberNo;
        this.memberId = memberId;
        this.password = password;
        this.memberInfo = memberInfo;
        this.role = role;
        this.state = state;
        this.date = date;
        this.modifyDate = modifyDate;
        this.deleteDate = deleteDate;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password =  password;
    }

    public MemberInfo getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(MemberInfo memberInfo) {
        this.memberInfo = memberInfo;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public DateSet getDate() {
        return date;
    }

    public void setDate(DateSet date) {
        this.date = date;
    }

    public DateSet getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(DateSet modifyDate) {
        this.modifyDate = modifyDate;
    }

    public DateSet getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(DateSet deleteDate) {
        this.deleteDate = deleteDate;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberNo=" + memberNo +
                ", memberId='" + memberId + '\'' +
                ", password='" + password + '\'' +
                ", memberInfo=" + memberInfo +
                ", role=" + role +
                ", state='" + state + '\'' +
                ", date=" + date +
                ", modifyDate=" + modifyDate +
                ", deleteDate=" + deleteDate +
                '}';
    }
}
