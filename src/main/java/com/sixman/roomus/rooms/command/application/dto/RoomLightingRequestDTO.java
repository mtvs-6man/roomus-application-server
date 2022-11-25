package com.sixman.roomus.rooms.command.application.dto;

import com.sixman.roomus.rooms.command.domain.model.vo.Color;
import com.sixman.roomus.rooms.command.domain.model.vo.Vector3;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RoomLightingRequestDTO {
    private boolean isSpot;
    private float innerAngle;
    private float outerAngle;
    private Color color;
    private float intensity;
    private float range;
    private Vector3 position;
    private Vector3 eulerAngle;
    private Vector3 localScale;

}
