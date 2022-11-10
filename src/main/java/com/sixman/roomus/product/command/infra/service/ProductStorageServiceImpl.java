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

    private final Map<String, String> filePathMap = Map.of(
            "image/png", "/screenshot",
            "application/zip", "/fbx-file"
    );
//    private final String PRODUCT_ZIP_DIRECTORY = "/product/zip";
//    private final String PRODUCT_SCREENSHOT_DIRECTORY = "/product/screenshot";

    @Override
    public String fileSaveToStorage(MultipartFile file) throws IOException {
        return awsS3Service.fileUpload("product", file);
    }

    private String getFilePath(String contentType){
        System.out.println("contentType = " + contentType);
        return filePathMap.get(contentType);
    }
}
