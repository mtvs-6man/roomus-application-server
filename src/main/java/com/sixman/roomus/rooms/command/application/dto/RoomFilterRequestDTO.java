package com.sixman.roomus.rooms.command.application.dto;

import com.sixman.roomus.rooms.command.domain.model.vo.Color;
import com.sixman.roomus.rooms.command.domain.model.vo.Float4;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RoomFilterRequestDTO {
    private Float4 shadowVal;
    private Float4 midtoneVal;
    private Float4 highlightVal;
    private float contrast;
    private float postExposure;
    private float hueShift;
    private float saturation;
    private float temp;
    private float tint;
    private Color colorFilter;
}