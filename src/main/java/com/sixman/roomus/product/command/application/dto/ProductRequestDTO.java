package com.sixman.roomus.product.command.application.dto;

import lombok.*;

@Data
public class ProductRequestDTO {
    private String furnitName;
    private boolean location;
    private String category;
    private String information;
    private float xSize;
    private float ySize;
    private float zSize;
    private Integer price;
}
