package com.sixman.roomus.product.command.application.dto;

import lombok.Data;

@Data
public class ProductUpdateRequestDTO {
    private Integer productNo;
    private String furnitName;
    private Boolean location;
    private String category;
    private String information;
    private Float xsize;
    private Float ysize;
    private Float zsize;
    private Integer price;
}
