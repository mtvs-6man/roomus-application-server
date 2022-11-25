package com.sixman.roomus.product.query.controller;

import com.sixman.roomus.commons.dto.ResponseDTO;
import com.sixman.roomus.commons.exception.ContentTypeNotAllowedException;
import com.sixman.roomus.commons.util.SecurityContextUtil;
import com.sixman.roomus.product.query.dto.ProductDetailsResponseDTO;
import com.sixman.roomus.product.query.dto.ProductSummaryResponseDTO;
import com.sixman.roomus.product.query.model.ProductData;
import com.sixman.roomus.product.query.model.ProductLikesMemberData;
import com.sixman.roomus.product.query.service.ProductViewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/v1/products")
@RestController
@RequiredArgsConstructor
@Tag(name = "상품 조회", description = "상품 관련 조회 API")
public class ProductViewController {

    private final ProductViewService productViewService;
    private final SecurityContextUtil securityContextUtil;

    @GetMapping(value = {""})
    @Operation(summary = "상품 목록 조회", description = "상품의 전제 목록을 조회합니다.")
    public ResponseEntity<ResponseDTO> findProductList() {
        List<ProductSummaryResponseDTO> productList = productViewService.findProductList();
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상품 목록조회 성공", productList));
    }


    @GetMapping("/{roomNo}")
    @Operation(summary = "상품 상세 조회", description = "단일 상품을 상세하게 조회합니다.")
    public ResponseEntity<ResponseDTO> findProductByProductId(@PathVariable int roomNo) {
        ProductDetailsResponseDTO productResponseDTO = productViewService.findProductByProductId(roomNo);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상품을 성공적으로 찾았습니다.", productResponseDTO));
    }

    @GetMapping("/myLikes")
    @Operation(summary = "좋아요 기반 상품 조회", description = "사용자는 자신이 좋아요를 누른 상품들을 조회할 수 있습니다.")
    public ResponseEntity<ResponseDTO> findProductByLikes() {
        // 로그인 사용자 정보
        Integer memberNo = securityContextUtil.getMemberNo();
        List<ProductSummaryResponseDTO> productList = productViewService.findLikesProducts(memberNo);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상품을 성공적으로 찾았습니다.", productList));
    }

    @PostMapping("/image-search")
    @Operation(summary = "사진으로 상품을 검색하는 기능", description = "사용자는 자신의 실제 가구사진을 사용하여 비슷한 에셋을 검색할 수 있습니다.")
    public ResponseEntity<ResponseDTO> imageSearch(@RequestPart MultipartFile image) throws ContentTypeNotAllowedException {
        String contentType = image.getContentType();
        if (!contentType.split("/")[0].equals("image")) {
            throw new ContentTypeNotAllowedException("지원하지 않는 타입입니다.");
        }
        List<ProductSummaryResponseDTO> result = productViewService.imageSearch(image);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "이미지 검색을 완료했습니다.", result));
    }

}
