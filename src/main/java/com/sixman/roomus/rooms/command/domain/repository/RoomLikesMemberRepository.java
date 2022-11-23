package com.sixman.roomus.rooms.command.domain.repository;

import com.sixman.roomus.rooms.command.domain.model.RoomLikesMember;
import com.sixman.roomus.rooms.command.domain.model.vo.RoomLikesMemberPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomLikesMemberRepository extends JpaRepository<RoomLikesMember, RoomLikesMemberPK> {
}
