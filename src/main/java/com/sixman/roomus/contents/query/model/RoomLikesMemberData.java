package com.sixman.roomus.contents.query.model;

import com.sixman.roomus.contents.command.domain.model.RoomLikesMemberPK;
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
public class RoomLikesMemberData {

    @EmbeddedId
    private RoomLikesMemberDataPK roomLikesMemberPK;

}
