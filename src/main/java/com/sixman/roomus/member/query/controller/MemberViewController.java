package com.sixman.roomus.member.query.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sixman.roomus.member.query.dto.UserSerchDTO;
import com.sixman.roomus.member.query.service.MemberViewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "회원 조회")
@RestController
@RequestMapping("/v1/member")
public class MemberViewController {

    private MemberViewService memberViewService;

    public MemberViewController(MemberViewService memberViewService) {
        this.memberViewService = memberViewService;
    }

    @Operation(description = "회원 검색")
    @GetMapping("")
    public ResponseEntity<?> serchUser(@RequestHeader("Authorization") String token, @RequestParam String userName) throws JsonProcessingException {
        if(token.isBlank()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("로그인 후 사용이 가능합니다.");

        if(userName.isBlank()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("사용자의 이름을 입력해주세요");
        List<UserSerchDTO> userList = memberViewService.findUser(token, userName);

        if (userList.isEmpty()) {
            return ResponseEntity.ok().body(userName +"회원은 존재하지 않습니다");
        }

        return ResponseEntity.ok().body(userList);
    }
}
