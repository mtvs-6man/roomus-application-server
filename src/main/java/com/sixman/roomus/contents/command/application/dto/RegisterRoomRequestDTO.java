package com.sixman.roomus.contents.command.application.dto;


import com.sixman.roomus.contents.domain.model.Vector3;
import lombok.Data;

import java.util.List;

@Data
public class RegisterRoomRequestDTO {
    private String roomName;
    private boolean access;
    private String category; // 방 카테고리 정보
    private String description;
    private float xsize; // 가로 길이
    private float ysize; // 세로 길이
    private float zsize; // 높이 길이
    private int door; // 문의 위치
}
