package com.sixman.roomus.product.command.infra.service;

import com.sixman.roomus.commons.util.SecurityContextUtil;
import com.sixman.roomus.product.command.domain.service.ProductMemberService;
import lombok.RequiredArgsConstructor;
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
