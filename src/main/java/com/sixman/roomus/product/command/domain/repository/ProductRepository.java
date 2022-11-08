package com.sixman.roomus.product.command.domain.repository;


import com.sixman.roomus.product.command.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {

}
