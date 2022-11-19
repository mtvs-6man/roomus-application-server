package com.sixman.roomus.rooms.command.domain.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomLikesMemberPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "ROOM_NO")
    private Room room;

    @Column(name = "MEMBER_NO")
    private int memberNo;

    @Override
    public int hashCode() {
        return Objects.hash(room.hashCode(), memberNo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RoomLikesMemberPK roomLikesMemberPK = (RoomLikesMemberPK) obj;
        return this.room == roomLikesMemberPK.getRoom() && this.memberNo == roomLikesMemberPK.getMemberNo();
    }
}
