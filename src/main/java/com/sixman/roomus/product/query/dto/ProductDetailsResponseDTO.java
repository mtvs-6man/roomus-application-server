package com.sixman.roomus.product.query.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDetailsResponseDTO {
    private int no;
    private String furnitName;
    private boolean location;
    private String category;
    private String information;
    private float xsize;
    private float ysize;
    private float zsize;
    private Integer price;
    private String fileUrl;
}
