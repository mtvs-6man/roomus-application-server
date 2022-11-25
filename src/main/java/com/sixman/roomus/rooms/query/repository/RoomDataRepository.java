package com.sixman.roomus.rooms.query.repository;

import com.sixman.roomus.rooms.query.model.RoomData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoomDataRepository extends JpaRepository<RoomData, Integer> {

    @Query("select a " +
            " from RoomData a  " +
            "where a.isDelete = false " +
            "  and a.memberNo = :memberNo " +
            "order by a.roomNo")
    List<RoomData> findRoomListByMemberNo(int memberNo);

    List<RoomData> findAllByMemberNoAndIsDeleteOrderByRoomNo(int memberNo, boolean isDelete);

    Optional<RoomData> findByRoomNoAndIsDelete(int roomNo, boolean isDelete);

    List<RoomData> findAllByIsDeleteAndAccessOrderByRoomNo(boolean isDelete, boolean access);
    List<RoomData> findAllByMemberNoAndIsDeleteAndAccessOrderByRoomNo(int memberNo, boolean isDelete, boolean access);
    List<RoomData> findAllByCategoryAndIsDeleteAndAccessOrderByRoomNo(String category, boolean isDelete, boolean access);

    @Query("select a " +
           "  from RoomData a " +
            " join fetch a.roomLikesMemberDataList b " +
            "where b.roomLikesMemberPK.memberNo = :memberNo " +
            "  and a.isDelete = false " +
            "order by a.roomNo")
    List<RoomData> findMyLikesList(int memberNo);

}
