package com.sixman.roomus.rooms.command.domain.model;

import com.sixman.roomus.rooms.command.domain.model.vo.RoomLikesMemberPK;
import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TBL_ROOM_LIKES_MEMBER")
public class RoomLikesMember {

    @EmbeddedId
    private RoomLikesMemberPK roomLikesMemberPK;

}
