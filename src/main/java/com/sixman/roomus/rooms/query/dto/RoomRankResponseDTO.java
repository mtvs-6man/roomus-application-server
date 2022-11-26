package com.sixman.roomus.rooms.query.dto;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomRankResponseDTO {
    private int memberNo;
    private int roomNo;
    private String category;
    private String description;
    private float xsize;
    private float ysize;
    private float zsize;
    private Date createdDate;
    private Date lastModifiedDate;
    private String screenShotUrl;
    private int cntLikes;
    private int ranking;
}

