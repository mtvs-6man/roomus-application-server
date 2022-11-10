package com.sixman.roomus.product.command.application.service;

import com.sixman.roomus.product.command.application.dto.ProductRequestDTO;
import com.sixman.roomus.product.command.domain.model.Product;
import com.sixman.roomus.product.command.domain.repository.ProductRepository;
import com.sixman.roomus.product.command.domain.service.ProductStorageService;
import com.sixman.roomus.product.command.domain.service.ProductDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductStorageService productStorageService;
    private final ProductDataService productDataService;


    @Transactional
    public Integer registProduct(ProductRequestDTO productRequestDTO, MultipartFile uploadFile, MultipartFile screenShot) throws IOException {
        // s3 데이터 추가
        String fbxFileName = productStorageService.fileSaveToStorage(uploadFile);
        String screenShotName = productStorageService.fileSaveToStorage(screenShot);
        // 엔티티로 변환
        Product product = new Product(
                productRequestDTO.getMemberNo(),
                productRequestDTO.getFurnitName(),
                productRequestDTO.isLocation(),
                productRequestDTO.getCategory(),
                productRequestDTO.getInformation(),
                productRequestDTO.getXsize(),
                productRequestDTO.getYsize(),
                productRequestDTO.getZsize(),
                productRequestDTO.getPrice(),
                new java.util.Date(),
                new java.util.Date(),
                fbxFileName,
                screenShotName);
        // 도메인 서비스 혹은 레파지토리 호출
        productRepository.save(product);
        productDataService.throwProductSaveMessageToViewRepository(product); // 쿼리용 DB에 저장

        // 삽입 결과 반환
        return product.getProductNo();
    }
}
