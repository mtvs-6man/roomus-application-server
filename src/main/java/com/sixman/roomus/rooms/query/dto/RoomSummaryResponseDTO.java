package com.sixman.roomus.rooms.query.dto;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomSummaryResponseDTO {
    private int roomNo;
    private int memberNo;
    private String RoomName;
    private boolean access;
    private String category;
    private String description;
    private float xsize;
    private float ysize;
    private float zsize;
    private int door;
    private Date createdDate;
    private Date lastModifiedDate;
    private Date deletedDate;
    private boolean isDelete;
    private String screenShotUrl;
    private int likes;
//    private List<FurnitureArrangementData> furnitureArrangementList;

}
