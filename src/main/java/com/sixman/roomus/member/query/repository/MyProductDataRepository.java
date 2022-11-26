package com.sixman.roomus.member.query.repository;

import com.sixman.roomus.member.query.dto.MyProductDTO;
import com.sixman.roomus.member.query.model.MemberData;
import com.sixman.roomus.member.query.model.MyProductData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MyProductDataRepository extends JpaRepository<MyProductData, Integer> {

    List<MyProductData> findByMemberNo(MemberData memberData);

    @Query(name = "SelectMyProduct", nativeQuery = true)
    List<MyProductDTO> findMyproductList(@Param(value = "memberNo")Integer memberNo);

}
