package com.sixman.roomus.product.query.repository;


import com.sixman.roomus.product.query.model.ProductData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ProductDataRepository extends JpaRepository<ProductData, Integer> {

    @Query("select a " +
            " from ProductData a " +
            "where a.isDelete = false " +
            "  and a.productNo = :productNo")
    Optional<ProductData> findProductDetails(int productNo);

    @Query("select a " +
            " from ProductData a " +
            " left join fetch a.ProductLikesMember " +
            "where a.isDelete = false " +
            "order by a.productNo asc")
    List<ProductData> findAllByIsDelete();


}
