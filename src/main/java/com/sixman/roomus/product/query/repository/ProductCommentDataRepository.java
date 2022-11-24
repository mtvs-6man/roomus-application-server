package com.sixman.roomus.product.query.repository;

import com.sixman.roomus.product.query.model.ProductCommentData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCommentDataRepository extends JpaRepository<ProductCommentData, Integer> {

}
