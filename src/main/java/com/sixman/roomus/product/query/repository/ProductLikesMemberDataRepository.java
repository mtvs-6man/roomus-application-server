package com.sixman.roomus.product.query.repository;

import com.sixman.roomus.product.query.model.ProductLikesMemberData;
import com.sixman.roomus.product.query.model.ProductLikesMemberDataPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductLikesMemberDataRepository extends JpaRepository<ProductLikesMemberData, ProductLikesMemberDataPK> {

    @Query("select a " +
            " from ProductLikesMemberData a " +
            "where a.productLikesMemberPk.memberNo = :memberNo "
            )
    List<ProductLikesMemberData> findMyLikesList(int memberNo);


}
