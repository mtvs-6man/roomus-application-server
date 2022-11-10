package com.sixman.roomus.commons.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AwsS3Service {

    String fileUpload(String domainName, MultipartFile file) throws IOException;

}
