package com.sixman.roomus.product.command.infra.service;

import com.sixman.roomus.commons.service.AwsS3Service;
import com.sixman.roomus.product.command.domain.service.ProductStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductStorageServiceImpl implements ProductStorageService {

    private final AwsS3Service awsS3Service;

    @Override
    public String fileSaveToStorage(MultipartFile file) throws IOException {
        return awsS3Service.fileUpload("product", file);
    }
}
