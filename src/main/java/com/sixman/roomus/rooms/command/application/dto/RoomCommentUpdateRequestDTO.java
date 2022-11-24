package com.sixman.roomus.rooms.command.application.dto;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoomCommentUpdateRequestDTO {
    private Integer commentNo;
    private String comment;
}
