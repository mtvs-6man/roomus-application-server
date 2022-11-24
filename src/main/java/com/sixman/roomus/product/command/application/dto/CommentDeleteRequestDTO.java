package com.sixman.roomus.product.command.application.dto;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentDeleteRequestDTO {
    private Integer commentNo;
    private String password;
}
