package com.sixman.roomus.product.command.application.dto;

import lombok.*;

@Data
public class ProductRequestDTO {
    private Integer memberNo;
    private String furnitName;
    private boolean location;
    private String category;
    private String information;
    private float xsize;
    private float ysize;
    private float zsize;
    private Integer price;
}
