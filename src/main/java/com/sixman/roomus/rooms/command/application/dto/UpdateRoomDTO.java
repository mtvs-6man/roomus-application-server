package com.sixman.roomus.rooms.command.application.dto;

import lombok.Data;

@Data
public class UpdateRoomDTO {
    private int roomNo;
    private String roomName;
    private Boolean access;
    private String category; // 방 카테고리 정보
    private String description;
}
