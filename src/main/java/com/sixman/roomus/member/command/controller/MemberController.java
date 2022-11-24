package com.sixman.roomus.member.command.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sixman.roomus.member.Dto.JoinDto;
import com.sixman.roomus.member.Dto.LoginDto;
import com.sixman.roomus.member.command.domain.model.Member;
import com.sixman.roomus.member.command.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Tag(name = "사용자 관리")
@RequestMapping("/")
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @Operation(description = "회원가입")
    @PostMapping("/guest/signup")
    public ResponseEntity<String> userJoin(@Valid @RequestBody JoinDto joinDto) {

        String result = memberService.memberSinup(joinDto);

        switch (result) {
            case "중복":
                return ResponseEntity.ok("중복된 아이디 입니다.");
            case "실패":
                return ResponseEntity.status(500).body("회원 가입에 실패하였습니다.");
        }

        return ResponseEntity.ok().body(result);
    }

    @Operation(description = "판매자 가입")
    @PutMapping("/member/rankUp")
    public ResponseEntity<String> memberRankUp(@RequestHeader("Authorization") String token) throws JsonProcessingException {
        if(token.isBlank()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유효하지 않은 토큰 값 입니다.");
        }

        String result = memberService.memberRanke(token);


        return ResponseEntity.ok().body(result);
    }

    @Operation(description = "비밀번호 확인")
    @PutMapping("/member/passcheck")
    public ResponseEntity<Boolean> passCheck(@RequestHeader("Authorization") String token, @RequestParam String confirmPass) throws JsonProcessingException {

        boolean checkValue;

        if(token.equals(null)){
            checkValue= false;
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(checkValue);
        }
        if (confirmPass.equals(null)){
            checkValue = false;
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(checkValue);
        }

        checkValue = memberService.passConfirm(token, confirmPass);
        return ResponseEntity.ok().body(checkValue);
    }

    @Operation(description = "비밀번호 변경")
    @PatchMapping("/member/passChange")
    public ResponseEntity<String> passChange(@RequestHeader("Authorization") String token, @RequestParam String changePass) throws JsonProcessingException {
        if(token.isBlank()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("token 정보가 유효하지 않습니다.");
        }
        if(changePass.isBlank()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호가 잘못되었습니다.");
        }

        Member member = memberService.chagePass(token,changePass);

        return ResponseEntity.ok().body(member.getMemberId()+" 님의 비밀번호가 변경되었습니다 \n 다시 로그인해주세요");
    }

}

