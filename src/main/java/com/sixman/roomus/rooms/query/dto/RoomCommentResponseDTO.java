package com.sixman.roomus.rooms.query.dto;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoomCommentResponseDTO {
    private int commentNo;
    private String memberId;
    private String comment;
    private boolean isDelete;
    private Date deleteDate;
    private Date lastModifiedDate;
    private Date createDate;
}
