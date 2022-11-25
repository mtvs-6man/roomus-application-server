package com.sixman.roomus.product.query.service;

import com.sixman.roomus.product.query.dto.DistanceDTO;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(name = "ProductDataCallAPI", url = "${ai.server}")
public interface ProductDataCallAPI {

    @PostMapping(value = "/find_sim_image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    List<DistanceDTO> callFindSimImageAPI(MultipartFile image);
}
