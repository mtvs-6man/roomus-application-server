package com.sixman.roomus.rooms.query.controller;

import com.sixman.roomus.commons.dto.ResponseDTO;
import com.sixman.roomus.commons.util.SecurityContextUtil;
import com.sixman.roomus.rooms.query.dto.RoomCommentResponseDTO;
import com.sixman.roomus.rooms.query.dto.RoomDetailsResponseDTO;
import com.sixman.roomus.rooms.query.dto.RoomSummaryResponseDTO;
import com.sixman.roomus.rooms.query.service.RoomViewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/rooms")
@RequiredArgsConstructor
@Tag(name = "방 조회", description = "방 관련 조회 API")
public class RoomViewController {

    private final RoomViewService roomViewService;
    private final SecurityContextUtil securityContextUtil;

    @GetMapping("/my")
    @Operation(summary = "나의 방 목록 조회", description = "내가 만든 방들의 목록을 조회합니다.")
    public ResponseEntity<ResponseDTO> getMyRoomList() {

        // 회원 인증
        int memberNo = securityContextUtil.getMemberNo();

        // 서비스 호출
        List<RoomSummaryResponseDTO> roomList = roomViewService.findMyRoomList(memberNo);
        // 응답
        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "방 목록 조회", roomList));
    }

    @GetMapping("/my/{roomNo}")
    @Operation(summary = "나의 방 상세 조회", description = "내가 만든 방을 상세하게 조회합니다.")
    public ResponseEntity<ResponseDTO> getMyRoomDetails(@PathVariable int roomNo) {
        // 회원 인증
        int memberNo = securityContextUtil.getMemberNo();

        // 서비스 호출
        RoomDetailsResponseDTO result = roomViewService.findMyRoomDetails(memberNo, roomNo);
        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK, "방 상세정보 조회에 성공했습니다.", result));
    }

    @GetMapping("/{roomNo}/comments")
    @Operation(summary = "해당 방의 코멘트 조회", description = "해당 방의 코멘트를 조회합니다.")
    public ResponseEntity<ResponseDTO> getRoomComments(@PathVariable int roomNo) {

        List<RoomCommentResponseDTO> result = roomViewService.findRoomCommentList(roomNo);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "방 댓글 조회에 성공했습니다.", result));
    }

    @GetMapping("")
    @Operation(summary = "방 조건 조회", description = "모든 방의 리스트를 조건 조회합니다. criteria에는 검색조건, value는 검색 값이 들어갑니다.\n" +
            "현재 criteria는 none(전체검색), memberNo, category이 가능합니다. ")
    public ResponseEntity<ResponseDTO> getRoomList(
            @RequestParam(name = "criteria", required = false) String criteria,
            @RequestParam(name = "value", required = false) String value
            ) {
        List<RoomSummaryResponseDTO> roomList= roomViewService.findRoomList(criteria, value);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "방 조회를 완료했습니다.", roomList));
    }


    @GetMapping("/mylikes")
    @Operation(summary = "좋아요 누른 방 조회", description = "사용자가 좋아요를 누른 방 리스트를 조회합니다.")
    public ResponseEntity<ResponseDTO> getMyLikesRoomList() {
        // 사용자 인증
        int memberNo = securityContextUtil.getMemberNo();
        List<RoomSummaryResponseDTO> roomList = roomViewService.findMyLikesRoomList(memberNo);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "방 조회를 완료했습니다.", roomList));
    }

    @GetMapping("/{roomNo}")
    @Operation(summary = "특정 방 조회",description = "특정 방을 상세 조회할 수 있습니다.")
    public ResponseEntity<ResponseDTO> getRoomDetails(@PathVariable int roomNo) {
        RoomDetailsResponseDTO response = roomViewService.findRoomDetails(roomNo);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "방 상세조회 성공", response));

    }

}
