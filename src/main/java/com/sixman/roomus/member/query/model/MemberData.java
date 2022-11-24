package com.sixman.roomus.member.query.model;

import com.sixman.roomus.commons.vo.DateSet;
import com.sixman.roomus.member.command.domain.model.Role;
import com.sixman.roomus.member.query.dto.UserSerchDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@NamedNativeQuery(
        name = "UserSerchQuery",
        query = "SELECT MEMBER_NO AS memberNo, " +
                "MEMBER_NAME AS memberName " +
                "FROM MEMBER_INFO WHERE MEMBER_NAME = :name",
        resultSetMapping = "user_serch_dto"
)
@SqlResultSetMapping(
        name = "user_serch_dto",
        classes = @ConstructorResult(
                targetClass = UserSerchDTO.class,
                columns = {
                        @ColumnResult(name = "memberNo", type = Integer.class),
                        @ColumnResult(name = "memberName", type = String.class)
                }
        )
)
@Table(name = "MEMBER_INFO")
public class MemberData {
    @Id
    @Column(name = "MEMBER_NO")
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
    private DateSet registDate; // 날짜 저장 방식 확인 필방 2022-11-11 방식으로 인코딩 설정함k

    @Embedded
    @AttributeOverride(name="date", column = @Column(name = "MODIFY_DATE"))
    private DateSet modifyDate;

    @Embedded
    @AttributeOverride(name="date", column = @Column(name = "DELETE_DATE"))
    private DateSet deleteDate;

    //User의 권한 목록을 가져오기 위함
    public List<String> getRoleList(){
        if(this.role.getValue().length()>0){
            return Arrays.asList(this.role.getValue().split(","));
        }
        return new ArrayList<>();
    }


    public MemberData() {
    }

    public MemberData(int memberNo, String memberId, String password, MemberInfo memberInfo, Role role, String state, DateSet registDate, DateSet modifyDate, DateSet deleteDate) {
        this.memberNo = memberNo;
        this.memberId = memberId;
        this.password = password;
        this.memberInfo = memberInfo;
        this.role = role;
        this.state = state;
        this.registDate = registDate;
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

    public DateSet getRegistDate() {
        return registDate;
    }

    public void setRegistDate(DateSet registDate) {
        this.registDate = registDate;
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
                ", registDate=" + registDate +
                ", modifyDate=" + modifyDate +
                ", deleteDate=" + deleteDate +
                '}';
    }


}
