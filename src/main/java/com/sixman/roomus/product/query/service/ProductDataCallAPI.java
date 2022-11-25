package com.sixman.roomus.product.query.service;

import com.sixman.roomus.product.query.dto.DistanceDTO;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(name = "ProductCallAPI", url = "${ai.server}")
public interface ProductDataCallAPI {

    @PostMapping("/find_sim_image")
    @Headers("Content-Type: " + MediaType.MULTIPART_FORM_DATA_VALUE)
    List<DistanceDTO> callFindSimImageAPI(MultipartFile image);
}
