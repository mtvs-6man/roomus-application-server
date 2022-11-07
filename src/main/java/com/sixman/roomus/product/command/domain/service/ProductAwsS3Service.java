package com.sixman.roomus.product.command.domain.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductAwsS3Service {

    String fileUpload(String category, MultipartFile file) throws IOException;

}
