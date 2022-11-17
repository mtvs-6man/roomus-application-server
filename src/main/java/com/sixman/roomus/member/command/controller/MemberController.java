package com.sixman.roomus.member.command.controller;

import com.sixman.roomus.commons.vo.DateSet;
import com.sixman.roomus.member.Dto.JoinDto;
import com.sixman.roomus.member.Dto.LoginDto;
import com.sixman.roomus.member.command.domain.model.Member;
import com.sixman.roomus.member.command.domain.model.Role;
import com.sixman.roomus.member.command.repository.MemberRepository;
import com.sixman.roomus.member.command.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

@RestController
@Tag(name = "UserController")
@RequestMapping("/")
public class MemberController {

    private MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @Operation(description = "회원가입")
    @PostMapping("/guest/signup")
    public ResponseEntity<String> userJoin(@Valid @RequestBody JoinDto joinDto){

        String result = memberService.memberSinup(joinDto);

        switch (result){
            case "중복" : return ResponseEntity.ok("중복된 아이디 입니다.");
            case "실패" : return ResponseEntity.status(500).body("회원 가입에 실패하였습니다.");
        }

        return ResponseEntity.ok().body(result);
    }
    @Operation(description = "판매자 가입")
    @PostMapping("/member/rankUp")
    public ResponseEntity<String> memberRankUp(@Valid @RequestBody LoginDto loginDto){

        return null;
    }

    @Operation(description = "test")
    @GetMapping("/test")
    public String test(){

        return "<h1> test </h1>";
    }

}

