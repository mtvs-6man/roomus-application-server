package com.sixman.roomus.rooms.query.service;

import com.sixman.roomus.rooms.command.domain.exception.NotFoundRoomException;
import com.sixman.roomus.rooms.query.dto.*;
import com.sixman.roomus.rooms.query.infra.RoomMemberService;
import com.sixman.roomus.rooms.query.model.*;
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

    public List<RoomSummaryResponseDTO> findMyRoomList(int memberNo) {
        List<RoomData> foundRoomList = roomDataRepository.findAllByMemberNoAndIsDeleteOrderByRoomNo(memberNo, false);
        List<RoomSummaryResponseDTO> roomResponseDTOList = new ArrayList<>();
        for (RoomData roomData : foundRoomList) {
//            System.out.println("roomData.getRoomCommentDataList() = " + roomData.getRoomCommentDataList());
//            System.out.println("roomResponseDTOList = " + roomResponseDTOList);
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
                    roomData.getScreenShotUrl(),
                    roomData.getRoomLikesMemberDataList().size(),
                    roomData.getRoomCommentDataList().size()
            );
            roomResponseDTOList.add(roomResponseDTO);
        }
        return roomResponseDTOList;
    }

    public RoomDetailsResponseDTO findMyRoomDetails(int memberNo, int roomNo) {
        Optional<RoomData> foundRoomOpt = roomDataRepository.findByRoomNoAndIsDelete(roomNo, false);
        if (foundRoomOpt.isEmpty()) {
            throw new NotFoundRoomException("방을 찾을 수 없습니다.");
        }
        RoomData foundRoom = foundRoomOpt.get();
        foundRoom.isRoomOwner(memberNo);

        // 가구 정보 DTO 변환
        List<FurnitureArrangementData> furnitureArrangementList = foundRoom.getFurnitureArrangementList();
        List<FurnitureArrangementResponseDTO> furnitureArrangementResponseDTOList = new ArrayList<>();
        for (FurnitureArrangementData furnitureArrangementData : furnitureArrangementList) {
            FurnitureArrangementResponseDTO furnitureArrangementResponseDTO = new FurnitureArrangementResponseDTO(
                    furnitureArrangementData.getFurnitureArrangementNo(),
                    furnitureArrangementData.getRoomData().getRoomNo(),
                    furnitureArrangementData.getIdx(),
                    furnitureArrangementData.getPosition(),
                    furnitureArrangementData.getEulerAngle(),
                    furnitureArrangementData.getLocalScale()
            );
            furnitureArrangementResponseDTOList.add(furnitureArrangementResponseDTO);
        }
        // 좋아요 정보 DTO 변환
        List<RoomLikesMemberData> roomLikesMemberDataList = foundRoom.getRoomLikesMemberDataList();
        ArrayList<RoomLikesMemberResponseDTO> roomLikesMemberResponseDTOList = new ArrayList<>();
        for (RoomLikesMemberData roomLikesMemberData : roomLikesMemberDataList) {
            int likedMemberNo = roomLikesMemberData.getRoomLikesMemberPK().getMemberNo();
            String memberId = roomMemberService.getMemberId(likedMemberNo);
            RoomLikesMemberResponseDTO roomLikesMemberResponseDTO = new RoomLikesMemberResponseDTO(memberId);
            roomLikesMemberResponseDTOList.add(roomLikesMemberResponseDTO);
        }
        // 조명 정보 DTO 변환
        List<RoomLightingData> roomLightingDataList = foundRoom.getRoomLightingDataList();
        ArrayList<RoomLightingResponseDTO> roomLightingResponseDTOList = new ArrayList<>();
        for (RoomLightingData roomLightingData : roomLightingDataList) {
            RoomLightingResponseDTO roomLightingResponseDTO = new RoomLightingResponseDTO(
                    roomLightingData.getRoomLightingNo(),
                    roomLightingData.getRoomData().getRoomNo(),
                    roomLightingData.isSpot(),
                    roomLightingData.getInnerAngle(),
                    roomLightingData.getOuterAngle(),
                    roomLightingData.getLightColor(),
                    roomLightingData.getIntensity(),
                    roomLightingData.getRange(),
                    roomLightingData.getPosition(),
                    roomLightingData.getEulerAngle(),
                    roomLightingData.getLocalScale()
            );
            roomLightingResponseDTOList.add(roomLightingResponseDTO);
        }

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
                foundRoom.getRoomFilter(),
                furnitureArrangementResponseDTOList,
                roomLikesMemberResponseDTOList,
                roomLightingResponseDTOList
        );
        return roomDetailsResponseDTO;
    }

    public List<RoomCommentResponseDTO> findRoomCommentList(int roomNo) {
        Optional<RoomData> foundRoomOpt = roomDataRepository.findByRoomNoAndIsDelete(roomNo, false);
        if (foundRoomOpt.isEmpty()) {
            throw new NotFoundRoomException("방을 찾을 수 없습니다.");
        }
        RoomData foundRoom = foundRoomOpt.get();
        List<RoomCommentData> foundRoomCommentList = roomCommentDataRepository.findAllByRoomData(foundRoom);

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

    public List<RoomSummaryResponseDTO> findRoomList(String criteria, String value) {
        List<RoomData> foundRoomList = new ArrayList<>();
        if("".equals(criteria) || criteria == null) {
            foundRoomList = roomDataRepository.findAllByIsDeleteAndAccessOrderByRoomNo(false, true);
        } else if (criteria.equals("memberNo")) {
            foundRoomList = roomDataRepository.findAllByMemberNoAndIsDeleteAndAccessOrderByRoomNo(Integer.parseInt(value), false, true);
        } else if (criteria.equals("category")) {
            foundRoomList = roomDataRepository.findAllByCategoryAndIsDeleteAndAccessOrderByRoomNo(value, false, true);
        }

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
                    roomData.getScreenShotUrl(),
                    roomData.getRoomLikesMemberDataList().size(),
                    roomData.getRoomCommentDataList().size()
            );
            roomResponseDTOList.add(roomResponseDTO);
        }
        return roomResponseDTOList;

    }

    public List<RoomSummaryResponseDTO> findMyLikesRoomList(int memberNo) {
        List<RoomData> foundRoomList = roomDataRepository.findMyLikesList(memberNo);
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
                    roomData.getScreenShotUrl(),
                    roomData.getRoomLikesMemberDataList().size(),
                    roomData.getRoomCommentDataList().size()
            );
            roomResponseDTOList.add(roomResponseDTO);
        }
        return roomResponseDTOList;

    }

    public RoomDetailsResponseDTO findRoomDetails(int roomNo) {
        Optional<RoomData> foundRoomOpt = roomDataRepository.findByRoomNoAndIsDeleteAndAccess(roomNo, false, true);
        if (foundRoomOpt.isEmpty()) {
            throw new NotFoundRoomException("방을 찾을 수 없습니다.");
        }
        RoomData foundRoom = foundRoomOpt.get();

        // 가구 정보 DTO 변환
        List<FurnitureArrangementData> furnitureArrangementList = foundRoom.getFurnitureArrangementList();
        List<FurnitureArrangementResponseDTO> furnitureArrangementResponseDTOList = new ArrayList<>();
        for (FurnitureArrangementData furnitureArrangementData : furnitureArrangementList) {
            FurnitureArrangementResponseDTO furnitureArrangementResponseDTO = new FurnitureArrangementResponseDTO(
                    furnitureArrangementData.getFurnitureArrangementNo(),
                    furnitureArrangementData.getRoomData().getRoomNo(),
                    furnitureArrangementData.getIdx(),
                    furnitureArrangementData.getPosition(),
                    furnitureArrangementData.getEulerAngle(),
                    furnitureArrangementData.getLocalScale()
            );
            furnitureArrangementResponseDTOList.add(furnitureArrangementResponseDTO);
        }
        // 좋아요 정보 DTO 변환
        List<RoomLikesMemberData> roomLikesMemberDataList = foundRoom.getRoomLikesMemberDataList();
        ArrayList<RoomLikesMemberResponseDTO> roomLikesMemberResponseDTOList = new ArrayList<>();
        for (RoomLikesMemberData roomLikesMemberData : roomLikesMemberDataList) {
            int likedMemberNo = roomLikesMemberData.getRoomLikesMemberPK().getMemberNo();
            String memberId = roomMemberService.getMemberId(likedMemberNo);
            RoomLikesMemberResponseDTO roomLikesMemberResponseDTO = new RoomLikesMemberResponseDTO(memberId);
            roomLikesMemberResponseDTOList.add(roomLikesMemberResponseDTO);
        }
        // 조명 정보 DTO 변환
        List<RoomLightingData> roomLightingDataList = foundRoom.getRoomLightingDataList();
        ArrayList<RoomLightingResponseDTO> roomLightingResponseDTOList = new ArrayList<>();
        for (RoomLightingData roomLightingData : roomLightingDataList) {
            RoomLightingResponseDTO roomLightingResponseDTO = new RoomLightingResponseDTO(
                    roomLightingData.getRoomLightingNo(),
                    roomLightingData.getRoomData().getRoomNo(),
                    roomLightingData.isSpot(),
                    roomLightingData.getInnerAngle(),
                    roomLightingData.getOuterAngle(),
                    roomLightingData.getLightColor(),
                    roomLightingData.getIntensity(),
                    roomLightingData.getRange(),
                    roomLightingData.getPosition(),
                    roomLightingData.getEulerAngle(),
                    roomLightingData.getLocalScale()
            );
            roomLightingResponseDTOList.add(roomLightingResponseDTO);
        }

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
                foundRoom.getRoomFilter(),
                furnitureArrangementResponseDTOList,
                roomLikesMemberResponseDTOList,
                roomLightingResponseDTOList
        );
        return roomDetailsResponseDTO;
    }

    public List<RoomSummaryResponseDTO> findMyRoomListOrderByLikes(Integer memberNo) {
        List<RoomData> foundRoomList = roomDataRepository.findAllByMemberNoAndIsDeleteAndAccessOrderByRoomNo(memberNo, false, true);
        foundRoomList.sort((t1, t2) -> {
            int left = t1.getRoomLikesMemberDataList().size();
            int right = t2.getRoomLikesMemberDataList().size();
            return right - left;
        });
        List<RoomSummaryResponseDTO> roomResponseDTOList = new ArrayList<>();
        int count = 0;
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
                    roomData.getScreenShotUrl(),
                    roomData.getRoomLikesMemberDataList().size(),
                    roomData.getRoomCommentDataList().size()
            );
            roomResponseDTOList.add(roomResponseDTO);
            if (++count >= 5) {
                break;
            }
        }
        return roomResponseDTOList;
    }
}
