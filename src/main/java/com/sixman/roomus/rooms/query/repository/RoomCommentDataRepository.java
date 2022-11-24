package com.sixman.roomus.rooms.query.repository;

import com.sixman.roomus.rooms.query.model.RoomCommentData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomCommentDataRepository extends JpaRepository<RoomCommentData, Integer> {
    List<RoomCommentData> findAllByRoomNo(int roomNo);
}
