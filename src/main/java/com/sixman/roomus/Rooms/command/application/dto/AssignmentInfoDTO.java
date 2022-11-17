package com.sixman.roomus.Rooms.command.application.dto;

import com.sixman.roomus.Rooms.command.domain.model.Vector3;
import lombok.Data;

@Data
public class AssignmentInfoDTO {
    private int idx;
    private Vector3 position;
    private Vector3 eulerAngle;
    private Vector3 localScale;
}
