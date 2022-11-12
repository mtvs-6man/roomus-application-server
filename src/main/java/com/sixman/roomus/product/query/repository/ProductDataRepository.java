package com.sixman.roomus.product.query.repository;


import com.sixman.roomus.product.query.model.ProductData;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductDataRepository extends JpaRepository<ProductData, Integer> {

}
