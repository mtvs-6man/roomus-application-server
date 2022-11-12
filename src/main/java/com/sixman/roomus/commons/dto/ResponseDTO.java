package com.sixman.roomus.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDTO {
    private HttpStatus statusCode;
    private String message;
    private Object data;
}
