package com.sixman.roomus.product.command.application.controller;

import com.sixman.roomus.commons.dto.ResponseDTO;
import com.sixman.roomus.commons.util.SecurityContextUtil;
import com.sixman.roomus.product.command.application.dto.*;
import com.sixman.roomus.product.command.application.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "상품", description = "상품 관련 CUD API")
public class ProductController {
    private final ProductService productService;
    private final SecurityContextUtil securityContextUtil;

    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "상품 업로드", description = "판매자 권한을 가진 사용자는 자신의 상품을 업로드할 수 있습니다.")
    public ResponseEntity<ResponseDTO> insertProduct(ProductRequestDTO product,
                                                     @RequestPart(value = "zipFile") MultipartFile zipFile,
                                                     @RequestPart(value = "screenShot") MultipartFile screenShot
    ) throws IOException {
        // 1. 로그인한 유저를 security 꺼내는 작업을 해야한다.
        product.setMemberNo(securityContextUtil.getMemberNo());
        // 2. 서비스 호출
        Integer registProductNo = productService.registProduct(product, zipFile, screenShot);
        // 3. 호출결과 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(HttpStatus.CREATED, "상품이 성공적으로 업로드 되었습니다.", registProductNo));
    }

    @PutMapping(value = "/{productNo}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "상품 정보 수정", description = "판매자 권한을 가진 사용자는 자신의 상품을 수정할 수 있습니다.")
    public ResponseEntity<ResponseDTO> updateProduct(@PathVariable int productNo,
                                                     ProductUpdateRequestDTO product
//                                                  ,@RequestPart(value = "screenShot", required = false) MultipartFile screenShot
    ) throws IOException {

        // 0. 유효성 검사 필요

        // 1. 현재 로그인한 유저 판별
        int memberNo = securityContextUtil.getMemberNo();
        // 2. 서비스 호출
        product.setProductNo(productNo);
        boolean result = productService.updateProduct(product, memberNo);
        // 3. 호출 결과 반환
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상품을 성공적으로 수정하였습니다.", result));
    }

    @DeleteMapping(value = "/{productNo}")
    @Operation(summary = "상품 삭제", description = "판매자 권한을 가진 사용자는 자신의 상품을 삭제할 수 있습니다.")
    public ResponseEntity<ResponseDTO> deleteProduct(@PathVariable int productNo) {
        // 0. 유효성 검사
        // 1. 현재 로그인한 유저 판별
        Integer memberNo = securityContextUtil.getMemberNo();
        // 2. 서비스 호출
        boolean result = productService.deleteProduct(productNo, memberNo);
        // 4. 결과 반환
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상품을 성공적으로 삭제하였습니다.", result));
    }

    @PostMapping(value = "/{productNo}/likes")
    @Operation(summary = "상품 좋아요", description = "사용자는 다른사람 혹은 자신의 상품에 좋아요를 남길 수 있습니다.")
    public ResponseEntity<ResponseDTO> likeProducts(@PathVariable Integer productNo) {
        // 0. 유효성 검사

        // 1. 현재 로그인한 유저 판별
        int memberNo = securityContextUtil.getMemberNo();
        // 2. 서비스 호출
        productService.likeProducts(productNo, memberNo);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "좋아요 추가 완료.", null));
    }

    @DeleteMapping(value = "/{productNo}/likes")
    @Operation(summary = "상품 좋아요 취소", description = "사용자는 다른사람 혹은 자신의 상품에 남긴 좋아요를 취소할 수 있습니다.")
    public ResponseEntity<ResponseDTO> unlikeProducts(@PathVariable Integer productNo) {
        // 0. 유효성 검사

        // 1. 현재 로그인한 유저 판별
        int memberNo = securityContextUtil.getMemberNo();

        // 2. 서비스 호출
        productService.unlikeProducts(productNo, memberNo);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "좋아요 삭제 완료", null));

    }

    @PostMapping(value = "/{productNo}/comments")
    @Operation(summary = "상품 코멘트 남기기", description = "사용자는 다른사람 혹은 자신의 상품에 코멘트를 남길 수 있습니다.")
    public ResponseEntity<ResponseDTO> commentProducts(@PathVariable int productNo,
                                                       @RequestBody CommentSaveRequestDTO commentReqeust
    ) {
        // 로그인한 회원 정보
        int memberNo = securityContextUtil.getMemberNo();

        int insertedCommentNo = productService.commentProducts(productNo, memberNo, commentReqeust);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(HttpStatus.CREATED, "댓글 추가 완료", insertedCommentNo));
    }

    @DeleteMapping(value = "/{productNo}/comments")
    @Operation(summary = "상품 코멘트 삭제", description = "사용자는 다른사람 혹은 자신의 상품에 남긴 코멘트를 삭제할 수 있습니다.")
    public ResponseEntity<ResponseDTO> deleteComment(@PathVariable int productNo,
                                                     @RequestBody CommentDeleteRequestDTO commentRequestDTO) {

        // 로그인한 회원 정보
        int memberNo = securityContextUtil.getMemberNo();
        securityContextUtil.checkPassword(commentRequestDTO.getPassword());

        // 서비스 호출
        productService.deleteComment(productNo, memberNo, commentRequestDTO);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "댓글 삭제 완료", null));
    }

    @PutMapping(value = "/{productNo}/comments")
    @Operation(summary = "상품 코멘트 수정", description = "사용자는 다른사람 혹은 자신의 상품에 남긴 코멘트를 수정할 수 있습니다.")
    public ResponseEntity<ResponseDTO> updateComment(@PathVariable int productNo,
                                                     @RequestBody CommentUpdateRequestDTO commentRequestDTO) {
        // 로그인한 회원 정보
        int memberNo = securityContextUtil.getMemberNo();
        securityContextUtil.checkPassword(commentRequestDTO.getPassword());

        // 서비스 호출
        int result = productService.updateComment(productNo, memberNo, commentRequestDTO);
        // 응답
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "댓글 수정 완료", result));
    }


}
