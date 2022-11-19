package com.sixman.roomus.rooms.query.dto;

import com.sixman.roomus.rooms.query.model.FurnitureArrangementData;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomDetailsResponseDTO {
    private int roomNo;
    private String roomName;
    private int memberNo;
    private String category;
    private String description;
    private float xsize;
    private float ysize;
    private float zsize;
    private String screenShotUrl;
    private List<FurnitureArrangementData> datas;
}
