package com.sixman.roomus.member.query.model;

import com.sixman.roomus.member.command.domain.model.Member;
import com.sixman.roomus.member.query.dto.RelationDTO;

import javax.persistence.*;

@Entity
@NamedNativeQuery(
        name = "followersList",
        query = "select\n" +
                "    mi.member_no as memberNo ,\n" +
                "    mi.member_name as memberName,\n" +
                "    mi.member_email as memberEmail\n" +
                "from tbl_relation tl\n" +
                "left join member_info mi on mi.member_no = tl.follow_user\n" +
                "where tl.relation_user= :memberData \n" +
                "AND mi.member_state = 'Y';",
        resultSetMapping = "RelationDTO"
)
@NamedNativeQuery(
        name = "followList",
        query = "select\n" +
                "    mi.member_no as memberNo,\n" +
                "    mi.member_name as memberName,\n" +
                "    mi.member_email as memberEmail\n" +
                "from tbl_relation tl\n" +
                "left join member_info mi on mi.member_no = tl.follow_user\n" +
                "where tl.follow_user = :memberData \n" +
                "  AND mi.member_state = 'Y';",
        resultSetMapping = "RelationDTO"
)

@SqlResultSetMapping(
        name = "RelationDTO",
        classes = @ConstructorResult(
                targetClass = RelationDTO.class,
                columns = {
                        @ColumnResult(name="memberNo", type = Integer.class),
                        @ColumnResult(name="memberName", type = String.class),
                        @ColumnResult(name ="memberEmail", type = String.class)
                }
        )
)

@Table(name = "TBL_RELATION")
public class RelationData {
    @Id
    @Column(name = "RELATION_ID")
    private int relationNo;

    @ManyToOne
    @JoinColumn(name = "RELATION_USER")
    private MemberData relationUser;

    @ManyToOne
    @JoinColumn(name = "FOLLOW_USER")
    private MemberData followUser;

    public RelationData() {
    }

    public RelationData(int relationNo, MemberData relationUser, MemberData followUser) {
        this.relationNo = relationNo;
        this.relationUser = relationUser;
        this.followUser = followUser;
    }

    public int getRelationNo() {
        return relationNo;
    }

    public void setRelationNo(int relationNo) {
        this.relationNo = relationNo;
    }

    public MemberData getRelationUser() {
        return relationUser;
    }

    public void setRelationUser(MemberData relationUser) {
        this.relationUser = relationUser;
    }

    public MemberData getFollowUser() {
        return followUser;
    }

    public void setFollowUser(MemberData followUser) {
        this.followUser = followUser;
    }

    @Override
    public String toString() {
        return "Relation{" +
                "relationNo=" + relationNo +
                ", relationUser=" + relationUser +
                ", followerUser=" + followUser +
                '}';
    }
}
