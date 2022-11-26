package com.sixman.roomus.rooms.query.dto;

import com.sixman.roomus.rooms.command.domain.model.vo.Color;
import com.sixman.roomus.rooms.command.domain.model.vo.Vector3;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomLightingResponseDTO {
    private int roomLightingNo;
    private int roomNo;
    private boolean isSpot;
    private float innerAngle;
    private float outerAngle;
    private Color lightColor;
    private float intensity;
    private float range;
    private Vector3 position;
    private Vector3 eulerAngle;
    private Vector3 localScale;
}
