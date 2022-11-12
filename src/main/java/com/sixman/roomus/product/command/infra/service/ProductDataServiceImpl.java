package com.sixman.roomus.product.command.infra.service;

import com.sixman.roomus.product.command.application.exception.NullProductException;
import com.sixman.roomus.product.command.domain.model.Product;
import com.sixman.roomus.product.command.domain.model.ProductLikesMember;
import com.sixman.roomus.product.command.domain.service.ProductDataService;
import com.sixman.roomus.product.query.model.ProductData;
import com.sixman.roomus.product.query.model.ProductLikesMemberData;
import com.sixman.roomus.product.query.model.ProductLikesMemberDataPK;
import com.sixman.roomus.product.query.repository.ProductDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductDataServiceImpl implements ProductDataService {

    private final ProductDataRepository productDataRepository;

    @Override
    @Async
    public void throwProductSaveMessageToViewRepository(Product product) {

        ProductData productData = new ProductData(
                product.getProductNo(),
                product.getMemberNo(),
                product.getFunitureName(),
                product.getLocation(),
                product.getCategory(),
                product.getInformation(),
                product.getProductScale(),
                product.getPrice(),
                product.getCreatedDate(),
                product.getLastModifiedDate(),
                product.getFileUrl(),
                product.getScreenShotUrl(),
                product.isDelete(),
                null
        );
        productDataRepository.save(productData);
    }

    @Override
    @Async
    public void throwProductDeleteMessageToViewRepository(int productNo) {
        Optional<ProductData> foundProduct = productDataRepository.findById(productNo);
        if (foundProduct.isEmpty()) {
            throw new NullProductException("상품이 존재하지 않습니다.");
        }
        ProductData productData = foundProduct.get();
        productData.setDelete(true);
    }

    @Override
    public void throwLikesSaveMessageToViewRepository(ProductLikesMember productLikesMember) {
        ProductLikesMemberDataPK productLikesMemberDataPK = new ProductLikesMemberDataPK();

    }

    @Override
    public void throwLikesDeleteMessageToViewRepository(ProductLikesMember productLikesMember) {

    }

}
