package com.sixman.roomus.rooms.query.controller;

import com.sixman.roomus.commons.dto.ResponseDTO;
import com.sixman.roomus.commons.util.SecurityContextUtil;
import com.sixman.roomus.rooms.query.dto.RoomDetailsResponseDTO;
import com.sixman.roomus.rooms.query.dto.RoomSummaryResponseDTO;
import com.sixman.roomus.rooms.query.service.RoomViewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/rooms")
@RequiredArgsConstructor
@Tag(name = "방 조회", description = "방에 관련된 조회에 관련된 API")
public class RoomViewController {

    private final RoomViewService roomViewService;
    private final SecurityContextUtil securityContextUtil;

    @GetMapping("")
    @Operation(summary = "나의 방 목록 조회", description = "내가 만든 방들의 목록을 조회합니다.")
    public ResponseEntity<ResponseDTO> getMyRoomList() {

        // 회원 인증
        int memberNo = securityContextUtil.getMemberNo();

        // 서비스 호출
        List<RoomSummaryResponseDTO> roomList = roomViewService.findRoomList(memberNo);
        // 응답
        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "방 목록 조회", roomList));
    }
    @GetMapping("/{roomNo}")
    @Operation(summary = "나의 방 상세 조회", description = "내가 만든 방을 상세하게 조회합니다.")
    public ResponseEntity<ResponseDTO> getMyRoomDetails(@PathVariable int roomNo){
        // 회원 인증
        int memberNo = securityContextUtil.getMemberNo();

        // 서비스 호출
        RoomDetailsResponseDTO result = roomViewService.findRoomDetails(memberNo, roomNo);
        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "방 상세정보 조회에 성공했습니다.", result));
    }
}
