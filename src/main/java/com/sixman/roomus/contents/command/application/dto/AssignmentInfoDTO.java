package com.sixman.roomus.contents.command.application.dto;

import com.sixman.roomus.contents.command.domain.model.Vector3;
import lombok.Data;

@Data
public class AssignmentInfoDTO {
    private int idx;
    private Vector3 position;
    private Vector3 eulerAngle;
    private Vector3 localScale;
}
