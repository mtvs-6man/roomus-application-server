package com.sixman.roomus.contents.query.service;

import com.sixman.roomus.contents.command.domain.exception.NotFoundRoomException;
import com.sixman.roomus.contents.query.dto.RoomDetailsResponseDTO;
import com.sixman.roomus.contents.query.dto.RoomSummaryResponseDTO;
import com.sixman.roomus.contents.query.model.RoomData;
import com.sixman.roomus.contents.query.repository.RoomDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomViewService {

    private final RoomDataRepository roomDataRepository;

    public List<RoomSummaryResponseDTO> findRoomList(int memberNo) {
        List<RoomData> foundRoomList = roomDataRepository.findAllByMemberNoAndIsDelete(memberNo);
        List<RoomSummaryResponseDTO> roomResponseDTOList = new ArrayList<>();
        for (RoomData roomData : foundRoomList) {
            RoomSummaryResponseDTO roomResponseDTO = new RoomSummaryResponseDTO(
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
                    roomData.isDelete()
//                    roomData.getFurnitureArrangementList()
            );
            roomResponseDTOList.add(roomResponseDTO);
        }
        return roomResponseDTOList;
    }

    public RoomDetailsResponseDTO findRoomDetails(int memberNo, int roomNo) {
        Optional<RoomData> foundRoomOpt = roomDataRepository.findByRoomNoAndIsDelete(roomNo);
        if (foundRoomOpt.isEmpty()) {
            throw new NotFoundRoomException("방을 찾을 수 없습니다.");
        }
        RoomData foundRoom = foundRoomOpt.get();
        foundRoom.isRoomOwner(memberNo);
        RoomDetailsResponseDTO roomDetailsResponseDTO = new RoomDetailsResponseDTO(
                foundRoom.getRoomNo(),
                foundRoom.getMemberNo(),
                foundRoom.getXsize(),
                foundRoom.getYsize(),
                foundRoom.getZsize(),
                foundRoom.getFurnitureArrangementList()
        );
        return roomDetailsResponseDTO;
    }
}
