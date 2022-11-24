package com.sixman.roomus.product.command.application.service;

import com.sixman.roomus.commons.model.Money;
import com.sixman.roomus.product.command.application.dto.*;
import com.sixman.roomus.product.command.application.exception.NullCommentException;
import com.sixman.roomus.product.command.application.exception.NullProductException;
import com.sixman.roomus.product.command.domain.model.Product;
import com.sixman.roomus.product.command.domain.model.ProductComment;
import com.sixman.roomus.product.command.domain.model.ProductLikesMember;
import com.sixman.roomus.product.command.domain.model.vo.ProductLikesMemberPK;
import com.sixman.roomus.product.command.domain.model.vo.ProductScale;
import com.sixman.roomus.product.command.domain.repository.ProductCommentRepository;
import com.sixman.roomus.product.command.domain.repository.ProductLikesMemberRepository;
import com.sixman.roomus.product.command.domain.repository.ProductRepository;
import com.sixman.roomus.product.command.domain.service.ProductCallAPI;
import com.sixman.roomus.product.command.domain.service.ProductStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductStorageService productStorageService;
    private final ProductLikesMemberRepository productLikesMemberRepository;
    private final ProductCallAPI productCallAPI;
    private final ProductCommentRepository productCommentRepository;

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
                null,
                false
//                new ArrayList<>()
        );

        // 도메인 서비스 혹은 레파지토리 호출
        productRepository.save(product);
        AiRequestDTO aiRequestDTO = new AiRequestDTO(product.getProductNo().toString(), product.getScreenShotUrl());
        try {
            productCallAPI.callUploadFurniture(aiRequestDTO);
        } catch (Exception e) {
            System.out.println("오류 발생");
        }

        // 삽입 결과 반환
        return product.getProductNo();
    }

    @Transactional
    public boolean updateProduct(ProductUpdateRequestDTO requestProduct,
                                 int memberNo
                                ) throws IOException {
        Optional<Product> foundProductOpt = productRepository.findByProductNoAndIsDelete(requestProduct.getProductNo(), false);
        if (foundProductOpt.isEmpty()) {
            throw new NullProductException("수정할 상품이 존재하지 않습니다.");
        }
        Product product = foundProductOpt.get();
        // 방 소유 확인
        product.isProductOwner(memberNo);

        if (requestProduct.getFurnitName() != null) {
            product.setFunitureName(requestProduct.getFurnitName());
        }
        if (requestProduct.getLocation() != null) {
            product.setLocation(requestProduct.getLocation());
        }
        if (requestProduct.getCategory() != null) {
            product.setCategory(requestProduct.getCategory());
        }
        if (requestProduct.getInformation() != null) {
            product.setInformation(requestProduct.getInformation());

        }
        ProductScale productScale = product.getProductScale();
        Float xsize = (requestProduct.getXsize() != null) ? requestProduct.getXsize() : productScale.getXSize();
        Float ysize = (requestProduct.getYsize() != null) ? requestProduct.getYsize() : productScale.getYSize();
        Float zsize = (requestProduct.getZsize() != null) ? requestProduct.getZsize() : productScale.getZSize();
        product.setProductScale(new ProductScale(xsize, ysize, zsize));
        if (requestProduct.getPrice() != null) {
            product.setPrice(new Money(requestProduct.getPrice()));
        }
//        if (screenShot != null) {
//            String url = productStorageService.fileSaveToStorage(screenShot);
//            product.setScreenShotUrl(url);
//        }

        product.setLastModifiedDate(new java.util.Date());
//        productDataService.throwProductSaveMessageToViewRepository(product); // 쿼리용 DB또한 수정
        return true;
    }

    @Transactional
    public boolean deleteProduct(int productNo, int memberNo) {
        Optional<Product> foundProductOpt = productRepository.findByProductNoAndIsDelete(productNo, false);

        if (foundProductOpt.isEmpty()) {
            throw new NullProductException("상품이 존재하지 않습니다.");
        }
        Product foundProduct = foundProductOpt.get();
        // 회원의 상품인지 확인
        foundProduct.isProductOwner(memberNo);

        foundProduct.setDelete(true);
        foundProduct.setDeleteDate(new Date());

        AiRequestDTO aiRequestDTO = new AiRequestDTO(foundProduct.getProductNo().toString(), "");
        try {
            productCallAPI.callDeleteFurniture(aiRequestDTO);
        } catch (Exception e) {
            System.out.println("오류 발생");
        }

        return true;
    }


    @Transactional
    public void likeProducts(int productNo, int memberNo) {
        // 1. 좋아요를 이미 누른 상태인지 확인하기 위해서 productLikesMemberPK 생성 (VO)
        Optional<Product> foundProductOpt = productRepository.findByProductNoAndIsDelete(productNo, false);
        if (foundProductOpt.isEmpty()) {
            throw new NullProductException("존재하지 않는 상품입니다.");
        }
        Product product = foundProductOpt.get();
        ProductLikesMemberPK productLikesMemberPK = new ProductLikesMemberPK(product, memberNo);
        Optional<ProductLikesMember> productLikesMemberQpt = productLikesMemberRepository.findById(productLikesMemberPK);

        if (productLikesMemberQpt.isEmpty()) { // 2-1. 좋아요를 누른 상태가 아닌 경우 좋아요 추가
            ProductLikesMember productLikesMember = new ProductLikesMember(new ProductLikesMemberPK(product, memberNo));
            productLikesMemberRepository.save(productLikesMember);
        } else { // 2-2. 좋아요를 누른 상태인 경우 exception
            throw new DuplicateKeyException("이미 좋아요를 눌렀습니다.");
        }
    }

    @Transactional
    public void unlikeProducts(Integer productNo, int memberNo) {
        // 1. 좋아요를 이미 누른 상태인지 확인하기 위해서 productLikesMemberPK 생성 (VO)
        Optional<Product> foundProductOpt = productRepository.findByProductNoAndIsDelete(productNo, false);
        if (foundProductOpt.isEmpty()) {
            throw new NullProductException("존재하지 않는 상품입니다.");
        }
        Product product = foundProductOpt.get();
        ProductLikesMemberPK productLikesMemberPK = new ProductLikesMemberPK(product, memberNo);
        Optional<ProductLikesMember> productLikesMemberQpt = productLikesMemberRepository.findById(productLikesMemberPK);

        if (productLikesMemberQpt.isPresent()) { // 2-1. 좋아요를 누른 상태인 경우 좋아요 삭제
            productLikesMemberRepository.delete(productLikesMemberQpt.get());
        } else { // 2-2. 좋아요를 누르지 않은 상태인 경우 exception
            throw new DuplicateKeyException("좋아요가 눌러져 있지 않습니다.");
        }

    }

    @Transactional
    public int commentProducts(int productNo, int memberNo, CommentSaveRequestDTO commentReqeust) {
        Optional<Product> foundProductOpt = productRepository.findByProductNoAndIsDelete(productNo, false);
        if (foundProductOpt.isEmpty()) {
            throw new NullProductException("존재하지 않는 상품입니다.");
        }
        Product foundProduct = foundProductOpt.get();
        ProductComment insertComment = new ProductComment(
                memberNo,
                foundProduct,
                commentReqeust.getComment(),
                false,
                new Date(),
                new Date()
        );
        productCommentRepository.save(insertComment);
        return insertComment.getProductCommentNo();
    }

    @Transactional
    public void deleteComment(int productNo, int memberNo, CommentUpdateRequestDTO commentRequestDTO) {
        Optional<Product> foundProductOpt = productRepository.findByProductNoAndIsDelete(productNo, false);
        if (foundProductOpt.isEmpty()) {
            throw new NullProductException("존재하지 않는 상품입니다.");
        }
        Product foundProduct = foundProductOpt.get();
        Optional<ProductComment> foundCommentOpt = productCommentRepository.findByProductCommentNoAndIsDelete(commentRequestDTO.getCommentNo(), false);
        if (foundCommentOpt.isEmpty()) {
            throw new NullCommentException("존재하지 않는 상품입니다.");
        }
        ProductComment productComment = foundCommentOpt.get();
        productComment.setDelete(true);
        productComment.setDeleteDate(new Date());
    }

    @Transactional
    public void updateComment(int productNo, int memberNo, CommentUpdateRequestDTO commentRequestDTO) {
        Optional<Product> foundProductOpt = productRepository.findByProductNoAndIsDelete(productNo, false);
        if (foundProductOpt.isEmpty()) {
            throw new NullProductException("존재하지 않는 상품입니다.");
        }
        Product foundProduct = foundProductOpt.get();
        Optional<ProductComment> foundCommentOpt = productCommentRepository.findByProductCommentNoAndIsDelete(commentRequestDTO.getCommentNo(), false);
        if (foundCommentOpt.isEmpty()) {
            throw new NullCommentException("존재하지 않는 상품입니다.");
        }
        ProductComment productComment = foundCommentOpt.get();
        productComment.setComment(commentRequestDTO.getComment());
        productComment.setLastModifiedDate(new Date());
    }
}
