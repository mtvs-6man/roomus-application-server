package com.sixman.roomus.rooms.query.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "TBL_ROOM_COMMENT")
public class RoomCommentData {

    @Id
    @Column(name = "COMMENT_NO")
    private int commentNo;

    @Column(name = "ROOM_NO")
    private int roomNo;

    @Column(name = "MEMBER_NO")
    @NonNull
    private int memberNo;

    @Column(name = "COMMENT")
    @NonNull
    private String comment;

    @Column(name = "IS_DELETE")
    @NonNull
    private boolean isDelete;

    @Column(name = "DELETE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deleteDate;

    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NonNull
    private Date lastModifiedDate;

    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NonNull
    private Date createDate;

}


