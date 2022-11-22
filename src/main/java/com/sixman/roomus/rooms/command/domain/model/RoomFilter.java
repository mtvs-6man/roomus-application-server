package com.sixman.roomus.rooms.command.domain.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomFilter implements Serializable {

    @AttributeOverrides({
            @AttributeOverride(name = "x", column = @Column(name = "SHADOWVAL_X")),
            @AttributeOverride(name = "y", column = @Column(name = "SHADOWVAL_Y")),
            @AttributeOverride(name = "z", column = @Column(name = "SHADOWVAL_Z")),
            @AttributeOverride(name = "w", column = @Column(name = "SHADOWVAL_W"))
    })
    private Float4 shadowVal;

    @AttributeOverrides({
            @AttributeOverride(name = "x", column = @Column(name = "MIDTONE_VAL_X")),
            @AttributeOverride(name = "y", column = @Column(name = "MIDTONE_VAL_Y")),
            @AttributeOverride(name = "z", column = @Column(name = "MIDTONE_VAL_Z")),
            @AttributeOverride(name = "w", column = @Column(name = "MIDTONE_VAL_W"))
    })
    private Float4 midtoneVal;

    @AttributeOverrides({
            @AttributeOverride(name = "x", column = @Column(name = "HIGHLIGHT_VAL_X")),
            @AttributeOverride(name = "y", column = @Column(name = "HIGHLIGHT_VAL_Y")),
            @AttributeOverride(name = "z", column = @Column(name = "HIGHLIGHT_VAL_Z")),
            @AttributeOverride(name = "w", column = @Column(name = "HIGHLIGHT_VAL_W"))
    })
    private Float4 highlightVal;

    @Column(name = "CONTRAST")
    @ColumnDefault("0.0")
    private float contrast;

    @Column(name = "POST_EXPOSURE")
    @ColumnDefault("0.0")
    private float postExposure;

    @Column(name = "HUE_SHIFT")
    @ColumnDefault("0.0")
    private float hueShift;

    @Column(name = "SATURATION")
    @ColumnDefault("0.0")
    private float saturation;

    @Column(name = "TEMP")
    @ColumnDefault("0.0")
    private float temp;

    @Column(name = "TINT")
    @ColumnDefault("0.0")
    private float tint;

    @AttributeOverrides({
            @AttributeOverride(name = "r", column = @Column(name = "COLOR_R")),
            @AttributeOverride(name = "g", column = @Column(name = "COLOR_G")),
            @AttributeOverride(name = "b", column = @Column(name = "COLOR_B")),
            @AttributeOverride(name = "a", column = @Column(name = "COLOR_A"))
    })
    private Color color;
}


