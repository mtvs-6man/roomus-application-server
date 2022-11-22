package com.sixman.roomus.rooms.command.domain.model;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Float4 implements Serializable {
    private float x;
    private float y;
    private float z;
    private float w;
}
