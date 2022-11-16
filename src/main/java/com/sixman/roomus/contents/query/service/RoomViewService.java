package com.sixman.roomus.contents.query.service;

import com.sixman.roomus.contents.query.dto.RoomResponseDTO;
import com.sixman.roomus.contents.query.model.RoomData;
import com.sixman.roomus.contents.query.repository.RoomDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomViewService {

    private final RoomDataRepository roomDataRepository;

    public List<RoomResponseDTO> findRoomList(int memberNo) {
        List<RoomData> foundRoomList = roomDataRepository.findAllByMemberNo(memberNo);
        List<RoomResponseDTO> roomResponseDTOList = new ArrayList<>();
        for (RoomData roomData : foundRoomList) {
            RoomResponseDTO roomResponseDTO = new RoomResponseDTO(
                    roomData.getRoomNo(),
                    roomData.getMemberNo(),
                    roomData.getRoomName(),
                    roomData.isAccess(),
                    roomData.getCategory(),
                    roomData.getDescription(),
                    roomData.getXsize(),
                    roomData.getYsize(),
                    roomData.getZsize(),
                    roomData.getDoor(),
                    roomData.getCreatedDate(),
                    roomData.getLastModifiedDate(),
                    roomData.getDeletedDate(),
                    roomData.isDelete(),
                    roomData.getFurnitureArrangementList()
            );
            roomResponseDTOList.add(roomResponseDTO);
        }
        return roomResponseDTOList;
    }
}
