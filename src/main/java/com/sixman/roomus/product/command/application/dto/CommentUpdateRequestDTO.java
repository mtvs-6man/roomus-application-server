package com.sixman.roomus.product.command.application.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentUpdateRequestDTO {
    private Integer commentNo;
    private String comment;
    private String password;
}
