package com.sixman.roomus.contents.query.repository;

import com.sixman.roomus.contents.query.model.RoomData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomDataRepository extends JpaRepository<RoomData, Integer> {
    List<RoomData> findAllByMemberNo(int memberNo);
}
