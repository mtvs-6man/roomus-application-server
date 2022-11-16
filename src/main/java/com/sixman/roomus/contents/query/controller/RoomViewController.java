package com.sixman.roomus.contents.query.controller;

import com.sixman.roomus.commons.dto.ResponseDTO;
import com.sixman.roomus.contents.query.dto.RoomResponseDTO;
import com.sixman.roomus.contents.query.service.RoomViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/rooms")
@RequiredArgsConstructor
public class RoomViewController {

    private final RoomViewService roomViewService;

    @GetMapping("")
    public ResponseEntity<ResponseDTO> getRoomList() {

        // 회원 인증
        int memberNo = 1;

        // 서비스 호출
        List<RoomResponseDTO> roomList = roomViewService.findRoomList(memberNo);
        // 응답
        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "방 목록 조회", roomList));
    }
}
