package com.sixman.roomus.member.command.repository;

import com.sixman.roomus.member.command.domain.model.Member;
import com.sixman.roomus.member.command.domain.model.MyProduct;
import com.sixman.roomus.product.command.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MyproductRepository extends JpaRepository<MyProduct, Integer> {

    MyProduct findByMemberNoAndProductNo(Member member, Product product);
}
