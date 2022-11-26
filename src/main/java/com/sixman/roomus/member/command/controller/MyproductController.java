package com.sixman.roomus.member.command.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sixman.roomus.member.command.domain.model.MyProduct;
import com.sixman.roomus.member.command.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/myproduct")
public class MyproductController {

    private MemberService memberService;
    public MyproductController(MemberService memberService){
        this.memberService = memberService;
    }

    @PostMapping("")
    public ResponseEntity<String> insertMyProduct(@RequestHeader("Authorization") String token, @RequestParam("productNo") int productNo) throws JsonProcessingException {

        String result = memberService.insertProduct(token, productNo);

        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delMyproduct(@RequestHeader("Authorization")String token, @RequestParam("productNo") int productNo) throws JsonProcessingException {

        String result = memberService.delMyProduct(token, productNo);

        return ResponseEntity.ok().body(result);
    }
}
