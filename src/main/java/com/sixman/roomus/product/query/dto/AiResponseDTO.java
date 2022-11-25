package com.sixman.roomus.product.query.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AiResponseDTO {
    List<DistanceDTO> datas;

}
