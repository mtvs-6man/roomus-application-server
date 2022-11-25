package com.sixman.roomus.rooms.command.domain.repository;

import com.sixman.roomus.rooms.command.domain.model.Room;
import com.sixman.roomus.rooms.command.domain.model.RoomLighting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomLightingRepository extends JpaRepository<RoomLighting, Integer> {
    void deleteAllByRoom(Room room);
}
