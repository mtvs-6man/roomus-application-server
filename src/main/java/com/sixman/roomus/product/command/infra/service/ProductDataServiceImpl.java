package com.sixman.roomus.product.command.infra.service;

import com.sixman.roomus.product.command.domain.model.Product;
import com.sixman.roomus.product.command.domain.service.ProductDataService;
import com.sixman.roomus.product.query.model.ProductData;
import com.sixman.roomus.product.query.repository.ProductDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDataServiceImpl implements ProductDataService {

    private final ProductDataRepository productDataRepository;

    @Override
    public void throwProductSaveMessageToViewRepository(Product product) {
        ProductData productData = new ProductData(
                product.getProductNo(),
                product.getMemberNo(),
                product.getFunitureName(),
                product.isLocation(),
                product.getCategory(),
                product.getInformation(),
                product.getProductScale(),
                product.getPrice(),
                product.getCreatedDate(),
                product.getLastModifiedDate(),
                product.getFileUrl(),
                product.getScreenShotUrl()
        );
        productDataRepository.save(productData);
    }

}
