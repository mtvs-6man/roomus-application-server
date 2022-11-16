package com.sixman.roomus.contents.query.repository;

import com.sixman.roomus.contents.query.model.RoomData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoomDataRepository extends JpaRepository<RoomData, Integer> {
    List<RoomData> findAllByMemberNoAndIsDelete(int memberNo, boolean isDelete);

    @Query("select a from RoomData a join fetch a.furnitureArrangementList where a.isDelete = false")
    Optional<RoomData> findByRoomNoAndIsDelete(int roomNo);
}
