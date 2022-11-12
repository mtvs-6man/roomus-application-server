package com.sixman.roomus.contents.command.application.controller;

import com.sixman.roomus.commons.dto.ResponseDTO;
import com.sixman.roomus.contents.command.application.dto.RegisterRoomRequestDTO;
import com.sixman.roomus.contents.command.application.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping(value = {"", "/"}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseDTO> registerRoom(@RequestBody RegisterRoomRequestDTO registerRoom){
        // 1. 유효성 검사
        // 2. 서비스 호출
        int registedRoomNo = roomService.registRoom(registerRoom);
        // 3. 응답

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "방 생성에 성공했습니다.", null));

    }
}
