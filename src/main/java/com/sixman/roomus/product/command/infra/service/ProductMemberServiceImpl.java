package com.sixman.roomus.product.command.infra.service;

import com.sixman.roomus.commons.util.SecurityContextUtil;
import com.sixman.roomus.product.command.domain.model.Product;
import com.sixman.roomus.product.command.domain.service.ProductMemberService;
import com.sixman.roomus.product.query.model.ProductData;
import com.sixman.roomus.product.query.repository.ProductDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductMemberServiceImpl implements ProductMemberService {

    private final SecurityContextUtil securityContextUtil;

    @Override
    public Integer getMemberNo() {
        return securityContextUtil.getMemberNo();
    }

}
