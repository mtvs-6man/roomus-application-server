package com.sixman.roomus.contents.command.application.service;


import com.sixman.roomus.contents.command.application.dto.RegisterRoomRequestDTO;
import com.sixman.roomus.contents.domain.model.Room;
import com.sixman.roomus.contents.domain.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public int registRoom(RegisterRoomRequestDTO registerRoom) {
        Room room = new Room(
                null,
                registerRoom.getRoomName(),
                registerRoom.isAccess(),
                registerRoom.getCategory(),
                registerRoom.getDescription(),
                registerRoom.getXsize(),
                registerRoom.getYsize(),
                registerRoom.getZsize(),
                registerRoom.getDoor(),
                new Date(),
                new Date(),
                new ArrayList<>()
        );
        roomRepository.save(room);
        return room.getRoomNo();
    }
}
