package com.sixman.roomus.product.command.application.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDTO {
    private Integer commentNo;
    private String comment;
}
