package com.sixman.roomus.contents.query.dto;

import com.sixman.roomus.contents.query.model.FurnitureArrangementData;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomDetailsResponseDTO {
    private int roomNo;
    private int memberNo;
    private float xsize;
    private float ysize;
    private float zsize;
    private List<FurnitureArrangementData> furnitureArrangementList;
}
