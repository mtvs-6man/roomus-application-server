package com.sixman.roomus.product.command.domain.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductStorageService {

    String fileSaveToStorage(MultipartFile file) throws IOException;

}
