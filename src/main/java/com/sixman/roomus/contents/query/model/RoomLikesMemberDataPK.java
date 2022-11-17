package com.sixman.roomus.contents.query.model;


import com.sixman.roomus.contents.command.domain.model.Room;
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
public class RoomLikesMemberDataPK implements Serializable {

    @Column(name = "MEMBER_NO")
    private int memberNo;

    @Override
    public int hashCode() {
        return Objects.hash(memberNo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RoomLikesMemberDataPK roomLikesMemberPK = (RoomLikesMemberDataPK) obj;
        return this.memberNo == roomLikesMemberPK.getMemberNo();
    }
}
