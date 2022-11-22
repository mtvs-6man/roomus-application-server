package com.sixman.roomus.rooms.command.domain.repository;

import com.sixman.roomus.rooms.command.domain.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    Optional<Room> findByRoomNoAndIsDelete(int no, boolean isDelete);
}
