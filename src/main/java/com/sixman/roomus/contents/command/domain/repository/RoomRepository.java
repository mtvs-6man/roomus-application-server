package com.sixman.roomus.contents.command.domain.repository;

import com.sixman.roomus.contents.command.domain.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}
