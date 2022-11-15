package com.sixman.roomus.contents.query.repository;

import com.sixman.roomus.contents.command.domain.model.FurnitureArrangement;
import com.sixman.roomus.contents.command.domain.model.Room;
import com.sixman.roomus.contents.query.model.FurnitureArrangementData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FurnitureArrangementDataRepository extends JpaRepository<FurnitureArrangementData, Integer> {
    void deleteAllByRoom(Room room);

}
