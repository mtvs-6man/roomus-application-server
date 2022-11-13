package com.sixman.roomus.contents.command.application.service;


import com.sixman.roomus.contents.command.application.dto.AssignmentInfoDTO;
import com.sixman.roomus.contents.command.application.dto.RegisterRoomRequestDTO;
import com.sixman.roomus.contents.command.application.dto.UpdateRoomDTO;
import com.sixman.roomus.contents.domain.exception.NotFoundRoomException;
import com.sixman.roomus.contents.domain.exception.NotRoomOwnerException;
import com.sixman.roomus.contents.domain.model.FurnitureArrangement;
import com.sixman.roomus.contents.domain.model.Room;
import com.sixman.roomus.contents.domain.repository.FurnitureArrangementRepository;
import com.sixman.roomus.contents.domain.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final FurnitureArrangementRepository furnitureArrangementRepository;

    @Transactional
    public int registRoom(int memberNo, RegisterRoomRequestDTO registerRoom) {
        Room room = new Room(
                memberNo,
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
                false,
                new Date()
        );

        roomRepository.save(room);
        return room.getRoomNo();
    }

    @Transactional
    public boolean saveFurnitureAssignInfo(int roomNo,int memberNo, List<AssignmentInfoDTO> assignmentInfoList){
        Optional<Room> foundRoomOpt = roomRepository.findById(roomNo);
        if (foundRoomOpt.isEmpty()) {
            throw new NotFoundRoomException("방을 찾을 수 없습니다.");
        }
        Room foundRoom = foundRoomOpt.get();
        // 해당 방의 소유자 인지 확인.
        if (!foundRoom.isRoomOwner(memberNo)) {
            throw new NotRoomOwnerException("해당 방의 사용자가 아닙니다.");
        }


        // 기존에 존재하던 해당 방의 가구 배치 정보 삭제
        furnitureArrangementRepository.deleteAllByRoom(foundRoom);

        // 새로운 배치정보 입력
        for (AssignmentInfoDTO assignmentInfo : assignmentInfoList) {
            FurnitureArrangement furnitureArrangement = new FurnitureArrangement(
                    assignmentInfo.getIdx(),
                    foundRoom,
                    assignmentInfo.getPosition(),
                    assignmentInfo.getEulerAngle(),
                    assignmentInfo.getLocalScale()
            );
            System.out.println("furnitureArrangement = " + furnitureArrangement);
            furnitureArrangementRepository.save(furnitureArrangement);
        }
        return true;
    }

    @Transactional
    public boolean updateRoom(int memberNo, UpdateRoomDTO updateRoomDTO) {
        Optional<Room> foundRoomOpt = roomRepository.findById(updateRoomDTO.getRoomNo());
        if (foundRoomOpt.isEmpty()) {
            throw new NotFoundRoomException("방이 존재하지 않습니다.");
        }
        Room foundRoom = foundRoomOpt.get();
        if (!foundRoom.isRoomOwner(memberNo)) {
            throw new NotRoomOwnerException("해당 방의 사용자가 아닙니다.");
        }
        // 방 이름 변경
        String roomName = updateRoomDTO.getRoomName();
        if (roomName != null) {
            System.out.println(roomName);
            foundRoom.setRoomName(roomName);
        }
        // 방 공개 여부 변경
        Boolean access = updateRoomDTO.getAccess();
        if (access != null) {
            foundRoom.setAccess(access);
        }
        // 방 카테고리 변경
        String category = updateRoomDTO.getCategory();
        if (category != null) {
            foundRoom.setCategory(category);
        }
        // 방 상세 설명 변경
        String description = updateRoomDTO.getDescription();
        if (description != null) {
            foundRoom.setDescription(description);
        }
        return true;
    }

    @Transactional
    public boolean deleteRoom(int memberNo, int roomNo) {
        Optional<Room> foundRoomOpt = roomRepository.findById(roomNo);
        if (foundRoomOpt.isEmpty()) {
            throw new NotFoundRoomException("방이 존재하지 않습니다.");
        }
        Room foundRoom = foundRoomOpt.get();
        if (!foundRoom.isRoomOwner(memberNo)) {
            throw new NotRoomOwnerException("해당 방의 사용자가 아닙니다.");
        }
        foundRoom.setDelete(true);
        foundRoom.setDeletedDate(new Date());
        return true;
    }
}
