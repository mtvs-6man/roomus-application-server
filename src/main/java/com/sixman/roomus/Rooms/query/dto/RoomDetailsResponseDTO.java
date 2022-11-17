package com.sixman.roomus.Rooms.query.dto;

import com.sixman.roomus.Rooms.query.model.FurnitureArrangementData;
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
    private List<FurnitureArrangementData> furnitureArrangementList;
}
