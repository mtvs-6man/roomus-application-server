package com.sixman.roomus.rooms.command.application.controller;

import com.sixman.roomus.commons.dto.ResponseDTO;
import com.sixman.roomus.rooms.command.application.dto.AssignmentInfoDTO;
import com.sixman.roomus.rooms.command.application.dto.RegisterRoomRequestDTO;
import com.sixman.roomus.rooms.command.application.dto.UpdateFunitureInfoDTO;
import com.sixman.roomus.rooms.command.application.dto.UpdateRoomDTO;
import com.sixman.roomus.rooms.command.application.service.RoomService;
import com.sixman.roomus.commons.exception.ContentTypeNotAllowedException;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(description = "방 생성을 위해서 사용하는 API입니다.", summary = "방 생성")
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
    @Operation(description = "방에 배치한 가구 정보를 변경하는 API입니다.", summary = "방의 가구 배치 정보 변경")
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
    @Operation(description = "방의 정보를 변경하는 API입니다.", summary = "방 정보 변경")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> updateRoom(@PathVariable(name = "id") int roomNo,
                                                  @RequestBody UpdateRoomDTO updateRoomDTO
//                                                  @RequestPart MultipartFile screenShot
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

    @Operation(description = "방 삭제 API입니다.", summary = "방 정보 삭제")
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

    @Operation(description = "방에 좋아요를 추가하는 API입니다.", summary = "방 좋아요")
    @PostMapping(value = "/{roomNo}/likes")
    public ResponseEntity<ResponseDTO> likeRooms(@PathVariable Integer roomNo) {
        // 0. 유효성 검사

        // 1. 현재 로그인한 유저 판별
        int memberNo = 1;
        // 2. 서비스 호출
        roomService.likeProducts(roomNo, memberNo);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "좋아요 추가 완료.", null));
    }

    @Operation(description = "방에 좋아요를 취소하는 API입니다.", summary = "방 좋아요 취소")
    @DeleteMapping(value = "/{roomNo}/likes")
    public ResponseEntity<ResponseDTO> unlikeProducts(@PathVariable Integer roomNo) {
        // 0. 유효성 검사

        // 1. 현재 로그인한 유저 판별
        int memberNo = 1;
        // 2. 서비스 호출
        roomService.unlikeRooms(roomNo, memberNo);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "좋아요 삭제 완료", null));

    }

    @Operation(description = "방의 이미지를 수정하는 API입니다.", summary = "방 이미지 수정")
    @PostMapping(value = "/{roomNo}/screenShot", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseDTO> updateImage(@PathVariable int roomNo,
                                                   @RequestPart MultipartFile screenShot
    ) throws ContentTypeNotAllowedException, IOException {
        int memberNo = 1;
        // 1. 유효성 검사
        String contentType = screenShot.getContentType();
        if (!contentType.split("/")[0].equals("image")) {
            throw new ContentTypeNotAllowedException("지원하지 않는 타입입니다.");
        }
        roomService.updateScreenShot(roomNo, memberNo, screenShot);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "이미지 수정이 완료되었습니다.", roomNo));
    }
}
