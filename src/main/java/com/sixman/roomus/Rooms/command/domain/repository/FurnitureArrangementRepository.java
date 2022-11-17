package com.sixman.roomus.Rooms.command.domain.repository;

import com.sixman.roomus.Rooms.command.domain.model.FurnitureArrangement;
import com.sixman.roomus.Rooms.command.domain.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FurnitureArrangementRepository extends JpaRepository<FurnitureArrangement, Integer> {
    void deleteAllByRoom(Room room);

}
