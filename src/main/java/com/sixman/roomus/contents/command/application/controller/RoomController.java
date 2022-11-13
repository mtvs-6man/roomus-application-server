package com.sixman.roomus.contents.command.application.controller;

import com.sixman.roomus.commons.dto.ResponseDTO;
import com.sixman.roomus.contents.command.application.dto.AssignmentInfoDTO;
import com.sixman.roomus.contents.command.application.dto.RegisterRoomRequestDTO;
import com.sixman.roomus.contents.command.application.dto.UpdateFunitureInfoDTO;
import com.sixman.roomus.contents.command.application.dto.UpdateRoomDTO;
import com.sixman.roomus.contents.command.application.service.RoomService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping(value = {"", "/"}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseDTO> registerRoom(@RequestBody RegisterRoomRequestDTO registerRoom) {
        // 0. 회원 정보 수집
        int memberNo = 1; // 후에 token에서 받는 것으로 수정해야 합니다.
        // 1. 유효성 검사
        // 2. 서비스 호출
        int registedRoomNo = roomService.registRoom(memberNo, registerRoom);
        // 3. 응답
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "방 생성에 성공했습니다.", registedRoomNo));
    }

    @PatchMapping(value = "/{id}/furniture", consumes = {MediaType.APPLICATION_JSON_VALUE})
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
                                                  @RequestBody UpdateRoomDTO updateRoomDTO
    ) {
        // 0. 회원 정보 수집
        int memberNo = 1;
        // 1. 유효성 검사
        updateRoomDTO.setRoomNo(roomNo);
        // 2. 서비스 호출
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
}
