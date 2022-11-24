package com.sixman.roomus.rooms.query.service;

import com.sixman.roomus.rooms.command.domain.exception.NotFoundRoomException;
import com.sixman.roomus.rooms.query.dto.RoomCommentResponseDTO;
import com.sixman.roomus.rooms.query.dto.RoomDetailsResponseDTO;
import com.sixman.roomus.rooms.query.dto.RoomSummaryResponseDTO;
import com.sixman.roomus.rooms.query.infra.RoomMemberService;
import com.sixman.roomus.rooms.query.model.RoomCommentData;
import com.sixman.roomus.rooms.query.model.RoomData;
import com.sixman.roomus.rooms.query.repository.RoomCommentDataRepository;
import com.sixman.roomus.rooms.query.repository.RoomDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomViewService {

    private final RoomDataRepository roomDataRepository;
    private final RoomCommentDataRepository roomCommentDataRepository;
    private final RoomMemberService roomMemberService;

    public List<RoomSummaryResponseDTO> findRoomList(int memberNo) {
        List<RoomData> foundRoomList = roomDataRepository.findAllByMemberNoAndIsDeleteOrderByRoomNo(memberNo, false);
        List<RoomSummaryResponseDTO> roomResponseDTOList = new ArrayList<>();
        for (RoomData roomData : foundRoomList) {
            System.out.println("roomData.getRoomCommentDataList() = " + roomData.getRoomCommentDataList());
            System.out.println("roomResponseDTOList = " + roomResponseDTOList);
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
                    roomData.isDelete(),
                    roomData.getScreenShotUrl(),
                    roomData.getRoomLikesMemberDataList().size(),
                    roomData.getRoomCommentDataList().size()
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
                foundRoom.getRoomName(),
                foundRoom.getMemberNo(),
                foundRoom.getCategory(),
                foundRoom.getDescription(),
                foundRoom.getXsize(),
                foundRoom.getYsize(),
                foundRoom.getZsize(),
                foundRoom.getScreenShotUrl(),
                foundRoom.getFurnitureArrangementList(),
                foundRoom.getRoomLikesMemberDataList()
        );
        return roomDetailsResponseDTO;
    }

    public List<RoomCommentResponseDTO> findRoomCommentList(int roomNo) {
        List<RoomCommentData> foundRoomCommentList = roomCommentDataRepository.findAllByRoomNo(roomNo);
        System.out.println("foundRoomCommentList = " + foundRoomCommentList);
        List<RoomCommentResponseDTO> roomCommentResponseDTOList = new ArrayList<>();

        for (RoomCommentData roomCommentData : foundRoomCommentList) {
            String memberId = roomMemberService.getMemberId(roomCommentData.getMemberNo());
            RoomCommentResponseDTO roomCommentResponseDTO = new RoomCommentResponseDTO(
                    roomCommentData.getCommentNo(),
                    memberId,
                    roomCommentData.getComment(),
                    roomCommentData.isDelete(),
                    roomCommentData.getDeleteDate(),
                    roomCommentData.getLastModifiedDate(),
                    roomCommentData.getCreateDate()
            );
            roomCommentResponseDTOList.add(roomCommentResponseDTO);
        }
        return roomCommentResponseDTOList;


    }
}
