package com.sixman.roomus.product.query.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.sixman.roomus.product.query.dto.ProductDetailsResponseDTO;
import com.sixman.roomus.product.query.dto.ProductSummaryResponseDTO;
import com.sixman.roomus.product.query.model.ProductData;
import com.sixman.roomus.product.query.model.ProductLikesMemberData;
import com.sixman.roomus.product.query.repository.ProductDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductViewService {

    private final ProductDataRepository productDataRepository;

    public ProductDetailsResponseDTO findProductByProductId(int id) {
        Optional<ProductData> productDataOptional = productDataRepository.findById(id);
        if (productDataOptional.isEmpty()) {
            throw new NotFoundException("product를 찾을 수 없습니다.");
        }
        ProductData foundProduct = productDataOptional.get();
        ProductDetailsResponseDTO productResponseDTO = new ProductDetailsResponseDTO(
                foundProduct.getProductNo(),
                foundProduct.getFunitureName(),
                foundProduct.isLocation(),
                foundProduct.getCategory(),
                foundProduct.getInformation(),
                foundProduct.getProductScale().getXSize(),
                foundProduct.getProductScale().getYSize(),
                foundProduct.getProductScale().getZSize(),
                foundProduct.getPrice().getValue(),
                foundProduct.getFileUrl()
        );
        System.out.println("productResponseDTO = " + productResponseDTO);
        return productResponseDTO;
    }

    public List<ProductSummaryResponseDTO> findProductList() {
        List<ProductData> productDataList = productDataRepository.findAll(Sort.by(Sort.Direction.ASC, "productNo"));
        System.out.println("productDataList = " + productDataList);
        List<ProductSummaryResponseDTO> productSummaryResponseDTO = new ArrayList<>();
        for (ProductData productData : productDataList) {
            ProductSummaryResponseDTO productListResponseDTO = new ProductSummaryResponseDTO();
            productListResponseDTO.setNo(productData.getProductNo());
            productListResponseDTO.setCategory(productData.getCategory());
            productListResponseDTO.setScreenShotUrl(productData.getScreenShotUrl());
            // 좋아요 수 구하기
            List<ProductLikesMemberData> productLikesMember = productData.getProductLikesMember();
            productListResponseDTO.setLikes(productLikesMember.size());
            productSummaryResponseDTO.add(productListResponseDTO);
        }
        return productSummaryResponseDTO;
    }

}
