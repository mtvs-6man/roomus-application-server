package com.sixman.roomus.product.command.domain.service;

import com.sixman.roomus.product.command.domain.model.Product;

public interface ProductDataService {
    void throwProductSaveMessageToViewRepository(Product product); // 후에 비동기로 변경해야 합니다.

}
