package com.sixman.roomus.rooms.query.model;

import com.sixman.roomus.rooms.command.domain.exception.NotRoomOwnerException;
import com.sixman.roomus.rooms.command.domain.model.vo.RoomFilter;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "TBL_ROOM")
public class RoomData {
    @Id
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

    @OneToMany(mappedBy = "roomData")
    @NonNull
    private List<FurnitureArrangementData> furnitureArrangementList;

    @OneToMany(mappedBy = "roomLikesMemberPK.roomData")
    @NonNull
    private List<RoomLikesMemberData> roomLikesMemberDataList;

    @OneToMany(mappedBy = "roomData")
    @NonNull
    private List<RoomCommentData> roomCommentDataList;

    @OneToMany(mappedBy = "roomData")
    @NonNull
    private List<RoomLightingData> roomLightingDataList;

    public boolean isRoomOwner(int memberNo) {
        if (this.memberNo != memberNo) {
            throw new NotRoomOwnerException("방에 대한 권한이 없습니다.");
        }
        return true;
    }
}
