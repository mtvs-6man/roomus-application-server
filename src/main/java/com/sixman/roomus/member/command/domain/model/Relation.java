package com.sixman.roomus.member.command.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "TBL_RELATION")
@SequenceGenerator(
        name = "RELATION_SEQUENCE",
        sequenceName = "RELATION_NO_SEQ",
        initialValue = 1,
        allocationSize = 1
)
public class Relation {

    @Id
    @Column(name = "RELATION_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RELATION_SEQUENCE")
    private int relationNo;

    @ManyToOne
    @JoinColumn(name = "RELATION_USER")
    private Member relationUser;

    @ManyToOne
    @JoinColumn(name = "FOLLOW_USER")
    private Member followUser;

    public Relation() {
    }

    public Relation(int relationNo, Member relationUser, Member followUser) {
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

    public Member getRelationUser() {
        return relationUser;
    }

    public void setRelationUser(Member relationUser) {
        this.relationUser = relationUser;
    }

    public Member getFollowUser() {
        return followUser;
    }

    public void setFollowUser(Member followUser) {
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
