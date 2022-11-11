package com.sixman.roomus.product.query.repository;

import com.sixman.roomus.product.command.domain.model.ProductLikesMember;
import com.sixman.roomus.product.command.domain.model.ProductLikesMemberPK;
import com.sixman.roomus.product.query.model.ProductLikesMemberData;
import com.sixman.roomus.product.query.model.ProductLikesMemberDataPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLikesMemberDataRepository extends JpaRepository<ProductLikesMemberData, ProductLikesMemberDataPK> {
}
