package com.sixman.roomus.product.command.domain.service;

import com.sixman.roomus.product.command.application.dto.AiRequestDTO;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name ="ProductCallAPI", url ="${ai.server}")
public interface ProductCallAPI {

    @GetMapping("")
    String callIndex();

    @PostMapping(value = "/upload_furniture")
    @Headers("Content-Type: application/json")
    String callUploadFurniture(AiRequestDTO aiRequestDTO);

}
