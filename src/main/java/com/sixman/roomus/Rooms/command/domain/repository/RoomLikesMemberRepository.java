package com.sixman.roomus.Rooms.command.domain.repository;

import com.sixman.roomus.Rooms.command.domain.model.RoomLikesMember;
import com.sixman.roomus.Rooms.command.domain.model.RoomLikesMemberPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomLikesMemberRepository extends JpaRepository<RoomLikesMember, RoomLikesMemberPK> {
}
