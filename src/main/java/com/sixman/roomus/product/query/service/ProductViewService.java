package com.sixman.roomus.product.query.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.sixman.roomus.product.query.dto.AiResponseDTO;
import com.sixman.roomus.product.query.dto.DistanceDTO;
import com.sixman.roomus.product.query.dto.ProductDetailsResponseDTO;
import com.sixman.roomus.product.query.dto.ProductSummaryResponseDTO;
import com.sixman.roomus.product.query.model.ProductCommentData;
import com.sixman.roomus.product.query.model.ProductData;
import com.sixman.roomus.product.query.model.ProductLikesMemberData;
import com.sixman.roomus.product.query.repository.ProductDataRepository;
import com.sixman.roomus.product.query.repository.ProductLikesMemberDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductViewService {

    private final ProductDataRepository productDataRepository;
    private final ProductLikesMemberDataRepository productLikesMemberDataRepository;
    private final ProductDataCallAPI productDataCallAPI;

    public ProductDetailsResponseDTO findProductByProductId(int productNo) {
        Optional<ProductData> productDataOptional = productDataRepository.findProductDetails(productNo);
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
                foundProduct.getFileUrl(),
                foundProduct.getProductCommentData()
        );
        System.out.println("productResponseDTO = " + productResponseDTO);
        return productResponseDTO;
    }

    public List<ProductSummaryResponseDTO> findProductList() {
        List<ProductData> productDataList = productDataRepository.findAllByIsDelete();
//        System.out.println("productDataList = " + productDataList);
        List<ProductSummaryResponseDTO> productSummaryResponseDTO = new ArrayList<>();
        for (ProductData productData : productDataList) {
            // 좋아요 수 구하기
            List<ProductLikesMemberData> productLikesMember = productData.getProductLikesMember();
            // 코멘트 수 구하기
            List<ProductCommentData> productCommentData = productData.getProductCommentData();
            // DTO로 변환
            ProductSummaryResponseDTO productListResponseDTO = new ProductSummaryResponseDTO(
                    productData.getProductNo(),
                    productData.getFunitureName(),
                    productData.getScreenShotUrl(),
                    productData.getCategory(),
                    productLikesMember.size(),
                    productCommentData.size()

            );
            // 응답 리스트에 추가
            productSummaryResponseDTO.add(productListResponseDTO);
        }
        return productSummaryResponseDTO;
    }

    public List<ProductSummaryResponseDTO> findLikesProducts(int memberNo) {
        List<ProductData> myLikesList = productDataRepository.findMyLikesList(memberNo);
        List<ProductSummaryResponseDTO> response = new ArrayList<>();
        for (ProductData productData : myLikesList) {
            ProductSummaryResponseDTO productSummaryResponseDTO = new ProductSummaryResponseDTO(
                    productData.getProductNo(),
                    productData.getFunitureName(),
                    productData.getScreenShotUrl(),
                    productData.getCategory(),
                    productData.getProductLikesMember().size(),
                    productData.getProductCommentData().size()
            );
            response.add(productSummaryResponseDTO);
        }
        return response;
    }

    public List<ProductSummaryResponseDTO> imageSearch(MultipartFile image) throws IOException {
        AiResponseDTO datas = productDataCallAPI.callFindSimImageAPI(image);
        List<DistanceDTO> distanceDTOList = datas.getDatas();
        List<Integer> idList = new ArrayList<>();
        for (DistanceDTO distanceDTO : distanceDTOList) {
            idList.add(distanceDTO.getId());
        }
        List<ProductData> foundProductList = productDataRepository.findByProductNoInAndIsDelete(idList, false);
        List<ProductSummaryResponseDTO> responseDTOList = new ArrayList<>();
        for (ProductData productData : foundProductList) {
            ProductSummaryResponseDTO productSummaryResponseDTO = new ProductSummaryResponseDTO(
                    productData.getProductNo(),
                    productData.getFunitureName(),
                    productData.getScreenShotUrl(),
                    productData.getCategory(),
                    productData.getProductLikesMember().size(),
                    productData.getProductCommentData().size()
            );
            responseDTOList.add(productSummaryResponseDTO);
        }

        return responseDTOList;
    }
}
