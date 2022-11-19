package com.sixman.roomus.Rooms.command.application.controller;

import com.sixman.roomus.commons.dto.ResponseDTO;
import com.sixman.roomus.Rooms.command.application.dto.AssignmentInfoDTO;
import com.sixman.roomus.Rooms.command.application.dto.RegisterRoomRequestDTO;
import com.sixman.roomus.Rooms.command.application.dto.UpdateFunitureInfoDTO;
import com.sixman.roomus.Rooms.command.application.dto.UpdateRoomDTO;
import com.sixman.roomus.Rooms.command.application.service.RoomService;
import com.sixman.roomus.commons.exception.ContentTypeNotAllowedException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/v1/rooms")
@RequiredArgsConstructor
@Tag(name = "방", description = "방에 관련된 조회를 제외한 API")
public class RoomController {

    private final RoomService roomService;

    @PostMapping(value = {""}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseDTO> registerRoom(RegisterRoomRequestDTO registerRoom,
                                                    @RequestPart MultipartFile screenShot
            ) throws IOException, ContentTypeNotAllowedException {
        // 0. 회원 정보 수집
        int memberNo = 1; // 후에 token에서 받는 것으로 수정해야 합니다.
        // 1. 유효성 검사
        String contentType = screenShot.getContentType();
        if (!contentType.split("/")[0].equals("image")) {
            throw new ContentTypeNotAllowedException("지원하지 않는 타입입니다.");
        }
        // 2. 서비스 호출
        int registedRoomNo = roomService.registRoom(memberNo, registerRoom, screenShot);
        // 3. 응답
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "방 생성에 성공했습니다.", registedRoomNo));
    }

    @PutMapping(value = "/{id}/furniture", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseDTO> saveFurnitureAssignInfo(@PathVariable(name = "id") int roomNo,
                                                               @RequestBody UpdateFunitureInfoDTO updateFunitureInfoDTO
    ) {
        List<AssignmentInfoDTO> datas = updateFunitureInfoDTO.getDatas();
        System.out.println("assignmentInfoList = " + datas);
        // 0. 회원정보 수집
        int memberNo = 1;
        // 1. 유효성 검사

        // 2. 서비스 호출
        boolean result = roomService.saveFurnitureAssignInfo(roomNo, memberNo, datas);
        // 3. 응답
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "가구 배치 정보 저장에 성공했습니다.", null));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseDTO> updateRoom(@PathVariable(name = "id") int roomNo,
                                                  UpdateRoomDTO updateRoomDTO,
                                                  @RequestPart MultipartFile screenShot
    ) {
        // 0. 회원 정보 수집
        int memberNo = 1;
        // 1. 유효성 검사
        updateRoomDTO.setRoomNo(roomNo);
        // ㅌ서비스 호출
        roomService.updateRoom(memberNo, updateRoomDTO);
        // 3. 응답
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "방 정보 수정 완료", null));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseDTO> deleteRoom(@PathVariable(name = "id") int roomNo) {
        // 회원 정보 꺼내기
        int memberNo = 1;
        // 유효성 검사

        // 서비스 호출
        roomService.deleteRoom(memberNo, roomNo);

        // 서비스 응답
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "방 정보 삭제 완료", null));
    }

    @PostMapping(value = "/{roomNo}/likes")
    public ResponseEntity<ResponseDTO> likeRooms(@PathVariable Integer roomNo) {
        // 0. 유효성 검사

        // 1. 현재 로그인한 유저 판별
        int memberNo = 1;
        // 2. 서비스 호출
        roomService.likeProducts(roomNo, memberNo);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "좋아요 추가 완료.", null));
    }

    @DeleteMapping(value = "/{roomNo}/likes")
    public ResponseEntity<ResponseDTO> unlikeProducts(@PathVariable Integer roomNo) {
        // 0. 유효성 검사

        // 1. 현재 로그인한 유저 판별
        int memberNo = 1;
        // 2. 서비스 호출
        roomService.unlikeRooms(roomNo, memberNo);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "좋아요 삭제 완료", null));

    }


}
