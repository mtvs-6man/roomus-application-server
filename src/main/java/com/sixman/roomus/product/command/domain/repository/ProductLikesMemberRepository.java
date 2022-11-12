package com.sixman.roomus.product.command.domain.repository;

import com.sixman.roomus.product.command.domain.model.ProductLikesMember;
import com.sixman.roomus.product.command.domain.model.ProductLikesMemberPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLikesMemberRepository extends JpaRepository<ProductLikesMember, ProductLikesMemberPK> {
}
