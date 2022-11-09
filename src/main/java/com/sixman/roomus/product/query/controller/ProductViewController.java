package com.sixman.roomus.product.query.controller;

import com.sixman.roomus.commons.dto.ResponseDTO;
import com.sixman.roomus.product.query.dto.ProductSummaryResponseDTO;
import com.sixman.roomus.product.query.dto.ProductDetailsResponseDTO;
import com.sixman.roomus.product.query.service.ProductViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/v1/products")
@RestController
@RequiredArgsConstructor
public class ProductViewController {

    private final ProductViewService productViewService;

    @GetMapping(value = {"", "/"})
    public ResponseEntity<ResponseDTO> findProductList() {
        List<ProductSummaryResponseDTO> productList = productViewService.findProductList();
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상풍 목록조회 성공", productList));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findProductByProductId(@PathVariable int id) {
        ProductDetailsResponseDTO productResponseDTO = productViewService.findProductByProductId(id);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "가구를 성공적으로 찾았습니다.", productResponseDTO));
    }

}
