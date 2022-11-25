package com.sixman.roomus.rooms.command.domain.model;

import com.sixman.roomus.rooms.command.domain.model.vo.Color;
import com.sixman.roomus.rooms.command.domain.model.vo.Vector3;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "TBL_ROOM_LIGHTING")
@SequenceGenerator(
        name = "ROOM_LIGHTING_SEQUENCE",
        sequenceName = "ROOM_LIGHTING_NO_SEQ",
        initialValue = 1,
        allocationSize = 1
)
public class RoomLighting {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROOM_LIGHTING_SEQUENCE")
    private Integer roomLightingNo;

    @ManyToOne
    @JoinColumn(name = "ROOM_NO")
    private Room room;

    @Column(name="is_Spot")
    private boolean isSpot;

    @Column(name = "INNER_ANGLE")
    private float innerAngle;

    @Column(name = "OUTER_ANGLE")
    private float outerAngle;


    @AttributeOverrides({
            @AttributeOverride(name = "r", column = @Column(name = "LIGHT_COLOR_R")),
            @AttributeOverride(name = "g", column = @Column(name = "LIGHT_COLOR_G")),
            @AttributeOverride(name = "b", column = @Column(name = "LIGHT_COLOR_B")),
            @AttributeOverride(name = "a", column = @Column(name = "LIGHT_COLOR_A")),
    })
    private Color lightColor;


    @Column(name = "INTENSITY")
    private float intensity;

    @Column(name = "RANGE")
    private float range;

    @AttributeOverrides({
            @AttributeOverride(name = "x", column = @Column(name = "POSITION_X")),
            @AttributeOverride(name = "y", column = @Column(name = "POSITION_Y")),
            @AttributeOverride(name = "z", column = @Column(name = "POSITION_Z")),
    })
    private Vector3 position;

    @AttributeOverrides({
            @AttributeOverride(name = "x", column = @Column(name = "EULER_ANGLE_X")),
            @AttributeOverride(name = "y", column = @Column(name = "EULER_ANGLE_Y")),
            @AttributeOverride(name = "z", column = @Column(name = "EULER_ANGLE_Z")),
    })
    private Vector3 eulerAngle;

    @AttributeOverrides({
            @AttributeOverride(name = "x", column = @Column(name = "LOCAL_SCALE_X")),
            @AttributeOverride(name = "y", column = @Column(name = "LOCAL_SCALE_Y")),
            @AttributeOverride(name = "z", column = @Column(name = "LOCAL_SCALE_Z")),
    })
    private Vector3 localScale;






}


