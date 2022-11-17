package com.sixman.roomus.Rooms.command.domain.model;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "TBL_FURNITURE_ARRANGEMENT")
@SequenceGenerator(
        name = "FURNITURE_ARRANGEMENT_SEQUENCE",
        sequenceName = "FURNITURE_ARRANGEMENT_NO_SEQ",
        initialValue = 1,
        allocationSize = 1
)
public class FurnitureArrangement {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FURNITURE_ARRANGEMENT_SEQUENCE")
    @Column(name = "FUNITURE_ARRANGEMENT_NO")
    private Integer funitureArrangementNo;

    @ManyToOne
    @JoinColumn(name = "ROOM_NO")
    private Room room;

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
