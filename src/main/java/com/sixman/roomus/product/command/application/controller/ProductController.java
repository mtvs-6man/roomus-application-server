package com.sixman.roomus.product.command.application.controller;

import com.sixman.roomus.commons.dto.ResponseDTO;
import com.sixman.roomus.product.command.application.dto.ProductRequestDTO;
import com.sixman.roomus.product.command.application.service.ProductService;
import com.sixman.roomus.product.command.domain.service.ProductMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor // 생성자 자동 주입
public class ProductController {
    private final ProductService productService;
    private final ProductMemberService productMemberService;

    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseDTO> insertProduct(ProductRequestDTO product,
                                                     @RequestPart(value = "zipFile") MultipartFile zipFile,
                                                     @RequestPart(value = "screenShot") MultipartFile screenShot
                                                    ) throws IOException {
        // 1. 로그인한 유저를 security 꺼내는 작업을 해야한다.
//        Integer memberNo = productMemberService.getMemberNo();
        product.setMemberNo(1);
        // 2. 서비스 호출
        Integer registProductNo = productService.registProduct(product, zipFile, screenShot);
        // 3. 호출결과 반환
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상품이 성공적으로 업로드 되었습니다.", registProductNo));
    }

}
