package com.sixman.roomus.product.command.domain.service;

import com.sixman.roomus.product.command.application.dto.AiProductDeleteRequestDTO;
import com.sixman.roomus.product.command.application.dto.AiProductUploadRequestDTO;
import com.sixman.roomus.product.query.dto.DistanceDTO;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(name ="ProductCallAPI", url ="${ai.server}")
public interface ProductCallAPI {

    @GetMapping("")
    String callIndex();

    @PostMapping(value = "/upload_furniture")
    @Headers("Content-Type: application/json")
    String callUploadFurniture(AiProductUploadRequestDTO aiRequestDTO);

    @PostMapping(value = "/delete_furniture")
    @Headers("Content-Type: application/json")
    String callDeleteFurniture(AiProductDeleteRequestDTO aiRequestDTO);


}
