package com.sixman.roomus.product.query.service;

import com.sixman.roomus.product.query.dto.AiResponseDTO;
import com.sixman.roomus.product.query.dto.DistanceDTO;
import feign.Headers;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@FeignClient(name = "ProductDataCallAPI", url = "${ai.server}")
public interface ProductDataCallAPI {

    @PostMapping(value = "/find_sim_image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Headers("Content-Type: multipart/form-data")
    AiResponseDTO callFindSimImageAPI(@RequestPart MultipartFile file);
}
