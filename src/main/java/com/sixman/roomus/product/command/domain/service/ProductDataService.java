package com.sixman.roomus.product.command.domain.service;

import com.sixman.roomus.product.command.domain.model.Product;
import com.sixman.roomus.product.command.domain.model.ProductLikesMember;

public interface ProductDataService {
    void throwProductSaveMessageToViewRepository(Product product);
    // insert, update를 위한 비동기 메소드 (MSA로 전환시 비동기 API 호출로 변경), 또한 저장중 오류 발생시 데이터를 다시 정합하는 과정이 필요

    void throwProductDeleteMessageToViewRepository(int productId);
    // delete를 위한 비동기 메소드

    void throwLikesSaveMessageToViewRepository(ProductLikesMember productLikesMember);
    void throwLikesDeleteMessageToViewRepository(ProductLikesMember productLikesMember);






}
