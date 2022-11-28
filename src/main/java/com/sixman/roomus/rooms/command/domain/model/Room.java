package com.sixman.roomus.rooms.command.domain.model;

import com.sixman.roomus.commons.exception.ContentTypeNotAllowedException;
import com.sixman.roomus.rooms.command.domain.exception.NotRoomOwnerException;
import com.sixman.roomus.rooms.command.domain.model.vo.RoomFilter;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "TBL_ROOM")
@SequenceGenerator(
        name = "ROOM_SEQUENCE",
        sequenceName = "ROOM_NO_SEQ",
        initialValue = 1,
        allocationSize = 1
)
@DynamicInsert
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROOM_SEQUENCE")
    @Column(name = "ROOM_NO")
    private int roomNo;

    @Column(name = "MEMBER_NO")
    @NonNull
    private int memberNo;

    @Column(name = "ROOM_NAME")
    @NonNull
    private String roomName;

    @NonNull
    private boolean access;

    @NonNull
    private String category;

    @NonNull
    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;

    @Column(name = "X_SIZE")
    @NonNull
    private float xsize;

    @Column(name = "Y_SIZE")
    @NonNull
    private float ysize;

    @Column(name = "Z_SIZE")
    @NonNull
    private float zsize;

    @NonNull
    private int door;
    // 생성일
    @Temporal(TemporalType.TIMESTAMP)
    @NonNull
    private Date createdDate;
    // 마지막 수정일
    @Temporal(TemporalType.TIMESTAMP)
    @NonNull
    private Date lastModifiedDate;
    // 삭제 여부
    @NonNull
    private boolean isDelete;
    // 삭제 일자
    @Temporal(TemporalType.TIMESTAMP)
    @NonNull
    private Date deletedDate;

    @Column(name = "URL_SCREENSHOT")
    @NonNull
    private String ScreenShotUrl;

    @Embedded
    private RoomFilter roomFilter;


    // 방 소유자 확인
    public void isRoomOwner(int memberNo) {
        if (this.memberNo != memberNo) {
            throw new NotRoomOwnerException("방 소유자가 아닙니다.");
        }
    }

    // 스크린샷 확인
    public void isImageFile(String contentType) throws ContentTypeNotAllowedException {
        if (!contentType.split("/")[0].equals("image")) {
            throw new ContentTypeNotAllowedException("지원하지 않는 타입입니다.");
        }
    }
}
