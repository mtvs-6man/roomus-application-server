package com.sixman.roomus.rooms.query.dto;

import com.sixman.roomus.rooms.command.domain.model.vo.Vector3;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FurnitureArrangementResponseDTO {
    private Integer furnitureArrangementNo;
    private int roomNo;
    private int idx;
    private Vector3 position;
    private Vector3 eulerAngle;
    private Vector3 localScale;
}
