package com.sixman.roomus.product.command.domain.repository;

import com.sixman.roomus.product.command.domain.model.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCommentRepository extends JpaRepository<ProductComment, Integer> {

    Optional<ProductComment> findByProductCommentNoAndIsDelete(int productCommentNo, boolean isDelete);
}
