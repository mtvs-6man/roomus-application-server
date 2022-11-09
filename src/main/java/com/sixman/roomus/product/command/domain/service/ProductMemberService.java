package com.sixman.roomus.product.command.domain.service;

import com.sixman.roomus.product.command.domain.model.Product;
import com.sixman.roomus.product.query.repository.ProductDataRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface ProductMemberService {
    Integer getMemberNo();

}
