package com.sixman.roomus.product.command.application.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.sixman.roomus.commons.model.Money;
import com.sixman.roomus.product.command.application.dto.ProductRequestDTO;
import com.sixman.roomus.product.command.application.dto.ProductUpdateRequestDTO;
import com.sixman.roomus.product.command.application.exception.NullProductException;
import com.sixman.roomus.product.command.domain.model.Product;
import com.sixman.roomus.product.command.domain.model.ProductLikesMember;
import com.sixman.roomus.product.command.domain.model.ProductLikesMemberPK;
import com.sixman.roomus.product.command.domain.model.ProductScale;
import com.sixman.roomus.product.command.domain.repository.ProductRepository;
import com.sixman.roomus.product.command.domain.service.ProductStorageService;
import com.sixman.roomus.product.command.domain.service.ProductDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


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
                null,
                productRequestDTO.getMemberNo(),
                productRequestDTO.getFurnitName(),
                productRequestDTO.isLocation(),
                productRequestDTO.getCategory(),
                productRequestDTO.getInformation(),
                new ProductScale(productRequestDTO.getXsize(),
                                 productRequestDTO.getYsize(),
                                 productRequestDTO.getZsize()),
                new Money(productRequestDTO.getPrice()),
                new java.util.Date(),
                new java.util.Date(),
                fbxFileName,
                screenShotName,
                false,
                new ArrayList<>()
        );

        // 도메인 서비스 혹은 레파지토리 호출
        productRepository.save(product);
        System.out.println("product = " + product);
        productDataService.throwProductSaveMessageToViewRepository(product); // 쿼리용 DB에 저장

        // 삽입 결과 반환
        return product.getProductNo();
    }

    @Transactional
    public boolean updateProduct(ProductUpdateRequestDTO requestProduct
//                                , MultipartFile screenShot
                                ) throws IOException {
        Optional<Product> productOptional = productRepository.findById(requestProduct.getProductNo());
        if (productOptional.isEmpty()) {
            throw new NullProductException("수정할 상품이 존재하지 않습니다.");
        }
        Product product = productOptional.get();
        if(requestProduct.getFurnitName() != null) {
            product.setFunitureName(requestProduct.getFurnitName());
        }
        if(requestProduct.getLocation() != null) {
            product.setLocation(requestProduct.getLocation());
        }
        if(requestProduct.getCategory() != null) {
            product.setCategory(requestProduct.getCategory());
        }
        if(requestProduct.getInformation().equals("")) {
            product.setInformation(requestProduct.getInformation());

        }
        ProductScale productScale = product.getProductScale();
        Float xsize = (requestProduct.getXsize() != null) ? requestProduct.getXsize() : productScale.getXSize();
        Float ysize = (requestProduct.getYsize() != null) ? requestProduct.getYsize() : productScale.getYSize();
        Float zsize = (requestProduct.getZsize() != null) ? requestProduct.getZsize() : productScale.getZSize();
        product.setProductScale(new ProductScale(xsize, ysize, zsize));
        if(requestProduct.getPrice() != null) {
            product.setPrice(new Money(requestProduct.getPrice()));
        }
//        if (screenShot != null) {
//            String url = productStorageService.fileSaveToStorage(screenShot);
//            product.setScreenShotUrl(url);
//        }

        product.setLastModifiedDate(new java.util.Date());
        productDataService.throwProductSaveMessageToViewRepository(product); // 쿼리용 DB또한 수정
        return true;
    }

    @Transactional
    public boolean deleteProduct(int productNo) {
        Optional<Product> foundProduct = productRepository.findById(productNo);
        if (foundProduct.isEmpty()) {
            throw new NullProductException("상품이 존재하지 않습니다.");
        }
        Product product = foundProduct.get();
        product.setDelete(true);
        productDataService.throwProductDeleteMessageToViewRepository(productNo);
        return true;
    }
}
