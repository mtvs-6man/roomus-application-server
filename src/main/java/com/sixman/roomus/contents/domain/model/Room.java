package com.sixman.roomus.contents.domain.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "TBL_ROOM")
@SequenceGenerator(
        name = "ROOM_SEQUENCE",
        sequenceName = "ROOM_NO_SEQ",
        initialValue = 1,
        allocationSize = 1
)
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROOM_SEQUENCE")
    @Column(name = "ROOM_NO")
    private Integer roomNo;
    @Column(name = "ROOM_NAME")
    private String roomName;
    private boolean access;
    private String category;
    private String description;
    @Column(name = "X_SIZE")
    private float xsize;
    @Column(name = "Y_SIZE")
    private float ysize;
    @Column(name = "Z_SIZE")
    private float zsize;
    private Integer door;
    // 생성일
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    // 마지막 수정일
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @OneToMany
    @JoinColumn(name = "ROOM_NO")
    @ToString.Exclude
    private List<FurnitureArrangement> furnitureArrangementList;

}
