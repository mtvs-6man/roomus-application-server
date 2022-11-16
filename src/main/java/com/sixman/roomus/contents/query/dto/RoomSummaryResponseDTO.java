package com.sixman.roomus.contents.query.dto;

import com.sixman.roomus.contents.query.model.FurnitureArrangementData;
import lombok.*;

import java.util.Date;
import java.util.List;


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
//    private List<FurnitureArrangementData> furnitureArrangementList;

}
