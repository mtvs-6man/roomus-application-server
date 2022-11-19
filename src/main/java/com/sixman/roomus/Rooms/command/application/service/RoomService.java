package com.sixman.roomus.Rooms.command.application.service;


import com.sixman.roomus.Rooms.command.application.dto.AssignmentInfoDTO;
import com.sixman.roomus.Rooms.command.application.dto.RegisterRoomRequestDTO;
import com.sixman.roomus.Rooms.command.application.dto.UpdateRoomDTO;
import com.sixman.roomus.Rooms.command.domain.exception.NotFoundRoomException;
import com.sixman.roomus.Rooms.command.domain.exception.NotRoomOwnerException;
import com.sixman.roomus.Rooms.command.domain.model.FurnitureArrangement;
import com.sixman.roomus.Rooms.command.domain.model.Room;
import com.sixman.roomus.Rooms.command.domain.model.RoomLikesMember;
import com.sixman.roomus.Rooms.command.domain.model.RoomLikesMemberPK;
import com.sixman.roomus.Rooms.command.domain.repository.FurnitureArrangementRepository;
import com.sixman.roomus.Rooms.command.domain.repository.RoomLikesMemberRepository;
import com.sixman.roomus.Rooms.command.domain.repository.RoomRepository;
import com.sixman.roomus.Rooms.command.domain.service.RoomStorageService;
import com.sixman.roomus.product.command.application.exception.NullProductException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomLikesMemberRepository roomLikesMemberRepository;
    private final FurnitureArrangementRepository furnitureArrangementRepository;
    private final RoomStorageService roomStorageService;

    @Transactional
    public int registRoom(int memberNo, RegisterRoomRequestDTO registerRoom, MultipartFile screenShot) throws IOException {
        // 스크린샷 S3 스토리지에 저장
        String screenShotUrl = roomStorageService.fileSaveToStorage(screenShot);
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
                new Date(),
                screenShotUrl
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

    @Transactional
    public void likeProducts(Integer roomNo, int memberNo) {
        // 1. 좋아요를 이미 누른 상태인지 확인하기 위해서 productLikesMemberPK 생성 (VO)
        Optional<Room> foundRoomOpt = roomRepository.findById(roomNo);
        if (foundRoomOpt.isEmpty()) {
            throw new NotFoundRoomException("존재하지 않는 방입니다.");
        }
        Room foundRoom = foundRoomOpt.get();
        RoomLikesMemberPK roomLikesMemberPK = new RoomLikesMemberPK(foundRoom, memberNo);
        Optional<RoomLikesMember> roomLikesMemberQpt = roomLikesMemberRepository.findById(roomLikesMemberPK);

        if (roomLikesMemberQpt.isEmpty()) { // 2-1. 좋아요를 누른 상태가 아닌 경우 좋아요 추가
            RoomLikesMember roomLikesMember = new RoomLikesMember(new RoomLikesMemberPK(foundRoom, memberNo));
            roomLikesMemberRepository.save(roomLikesMember);
        } else { // 2-2. 좋아요를 누른 상태인 경우 exception
            throw new DuplicateKeyException("이미 좋아요를 눌렀습니다.");
        }
    }

    @Transactional
    public void unlikeRooms (Integer roomNo, int memberNo) {
        // 1. 좋아요를 이미 누른 상태인지 확인하기 위해서 productLikesMemberPK 생성 (VO)
        Optional<Room> productOpt = roomRepository.findById(roomNo);
        if (productOpt.isEmpty()) {
            throw new NullProductException("존재하지 않는 방입니다.");
        }
        Room foundRoom = productOpt.get();
        RoomLikesMemberPK roomLikesMemberPK = new RoomLikesMemberPK(foundRoom, memberNo);
        Optional<RoomLikesMember> roomLikesMemberQpt = roomLikesMemberRepository.findById(roomLikesMemberPK);

        if (roomLikesMemberQpt.isPresent()) { // 2-1. 좋아요를 누른 상태인 경우 좋아요 삭제
            roomLikesMemberRepository.delete(roomLikesMemberQpt.get());
        } else { // 2-2. 좋아요를 누르지 않은 상태인 경우 exception
            throw new DuplicateKeyException("좋아요가 눌러져 있지 않습니다.");
        }

    }
}
