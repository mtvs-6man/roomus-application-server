package com.sixman.roomus.contents.domain.repository;

import com.sixman.roomus.contents.domain.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}
