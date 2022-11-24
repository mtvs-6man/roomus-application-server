package com.sixman.roomus.rooms.query.model;


import com.sixman.roomus.rooms.command.domain.model.vo.Vector3;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "TBL_FURNITURE_ARRANGEMENT")
public class FurnitureArrangementData {

    @Id
    @Column(name = "FUNITURE_ARRANGEMENT_NO")
    private Integer furnitureArrangementNo;

    @Column(name = "PRODUCT_NO")
    private int idx;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "x", column = @Column(name = "position_x")),
            @AttributeOverride(name = "y", column = @Column(name = "position_y")),
            @AttributeOverride(name = "z", column = @Column(name = "position_z")),

    })
    private Vector3 position;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "x", column = @Column(name = "eulerAngle_x")),
            @AttributeOverride(name = "y", column = @Column(name = "eulerAngle_y")),
            @AttributeOverride(name = "z", column = @Column(name = "eulerAngle_z")),

    })
    private Vector3 eulerAngle;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "x", column = @Column(name = "localScale_x")),
            @AttributeOverride(name = "y", column = @Column(name = "localScale_y")),
            @AttributeOverride(name = "z", column = @Column(name = "localScale_z")),

    })
    private Vector3 localScale;

}
