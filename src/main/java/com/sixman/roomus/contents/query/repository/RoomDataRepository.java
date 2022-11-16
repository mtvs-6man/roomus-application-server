package com.sixman.roomus.contents.query.repository;

import com.sixman.roomus.contents.query.model.RoomData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomDataRepository extends JpaRepository<RoomData, Integer> {
    @Query("select a from RoomData a join fetch a.furnitureArrangementList")
    List<RoomData> findAllByMemberNo(int memberNo);
}
