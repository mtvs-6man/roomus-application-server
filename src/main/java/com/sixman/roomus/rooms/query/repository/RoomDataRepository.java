package com.sixman.roomus.rooms.query.repository;

import com.sixman.roomus.rooms.query.model.RoomData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoomDataRepository extends JpaRepository<RoomData, Integer> {

    @Query("select a " +
            " from RoomData a  " +
            " left join fetch a.roomLikesMemberData " +
            "where a.isDelete = false " +
            "  and a.memberNo = :memberNo " +
            "order by a.roomNo")
    List<RoomData> findAllByMemberNoAndIsDelete(int memberNo);

    @Query("select a " +
            " from RoomData a " +
            " left join fetch a.furnitureArrangementList b " +
            " left join a.roomLikesMemberData " +
            "where a.isDelete = false " +
            "  and a.roomNo = :roomNo ")
    Optional<RoomData> findByRoomNoAndIsDelete(int roomNo);
}
