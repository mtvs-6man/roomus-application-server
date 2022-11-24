package com.sixman.roomus.rooms.command.domain.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@SequenceGenerator(
        name = "ROOM_COMMENT_SEQUENCE",
        sequenceName = "ROOM_COMMENT_NO_SEQ"
)
@Entity
@Table(name = "TBL_ROOM_COMMENT")
public class RoomComment {

    @Id
    @Column(name = "COMMENT_NO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROOM_COMMENT_SEQUENCE")
    private int commentNo;

    @Column(name = "MEMBER_NO")
    @NonNull
    private int memberNo;

    @ManyToOne
    @JoinColumn(name = "ROOM_NO")
    @NonNull
    private Room room;

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
