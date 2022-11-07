package com.sixman.roomus.member.command.domain.model;

public class MemberNullError extends RuntimeException{
    public MemberNullError(String message) {
        super(message+"은 필수 값 입니다.");
    }
}
