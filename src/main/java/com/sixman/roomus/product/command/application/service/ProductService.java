package com.sixman.roomus.product.command.application.service;

import com.sixman.roomus.product.command.application.dto.ProductRequestDTO;
import com.sixman.roomus.product.command.domain.model.Product;
import com.sixman.roomus.product.command.domain.repository.ProductRepository;
import com.sixman.roomus.product.command.domain.service.ProductAwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductAwsS3Service fileService;

    private final String PRODUCT_ZIP_FILE = "/product/zip";
    private final String PRODUCT_SCREENSHOT_FILE = "/product/screenshot";

    @Transactional
    public Integer registProduct(ProductRequestDTO productRequestDTO, MultipartFile uploadFile, MultipartFile screenShot) throws IOException {
        // s3 데이터 추가
        String fbxFileName = fileService.fileUpload(PRODUCT_ZIP_FILE, uploadFile);
        String screenShotName = fileService.fileUpload(PRODUCT_SCREENSHOT_FILE, screenShot);
        // 엔티티로 변환
        Product product = new Product(
                productRequestDTO.getFurnitName(),
                productRequestDTO.isLocation(),
                productRequestDTO.getCategory(),
                productRequestDTO.getInformation(),
                productRequestDTO.getXSize(),
                productRequestDTO.getYSize(),
                productRequestDTO.getZSize(),
                productRequestDTO.getPrice(),
                new java.util.Date(),
                new java.util.Date(),
                fbxFileName,
                screenShotName);
        // 도메인 서비스 혹은 레파지토리 호출
        System.out.println("productRepository = " + productRepository);
        productRepository.save(product);
        // 삽입 결과 반환
        return product.getNo();
    }
}
