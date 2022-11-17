package com.sixman.roomus.product.query.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductSummaryResponseDTO {
    private int no;
    private String furnitName;
    private String screenShotUrl;
    private String category;
    private int likes;
}
