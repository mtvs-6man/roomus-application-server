package com.sixman.roomus.member.command.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.sixman.roomus.member.command.domain.model.Relation;
import com.sixman.roomus.member.command.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Relations")
public class FollowController {

    private MemberService memberService;

    public FollowController(MemberService memberService){
        this.memberService = memberService;
    }

    @Operation(description = "팔로우 신청")
    @PostMapping ("/follow")
    public ResponseEntity<String> follow(@RequestHeader("Authorization") String token, @RequestParam Integer userNo) throws JsonProcessingException {
        if(userNo.equals(null)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("데이터가 유효하지 않습니다.");

        String relations = memberService.followUser(token, userNo);

        return ResponseEntity.ok().body(relations);
    }

}


