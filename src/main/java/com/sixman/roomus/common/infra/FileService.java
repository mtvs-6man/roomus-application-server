package com.sixman.roomus.common.infra;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.sixman.roomus.common.model.File;
import com.sixman.roomus.common.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final FileRepository fileRepository;
    private final AmazonS3 amazonS3;


    // 파일 업로드
    @Transactional
    public File upload(MultipartFile uploadFile) throws IOException {
        File file = new File();
        fileRepository.save(file);

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(uploadFile.getInputStream().available());

        String fileNoString = String.valueOf(file.getFileNo());
        amazonS3.putObject(bucket, fileNoString, uploadFile.getInputStream(), objectMetadata);
        URL url = amazonS3.getUrl(bucket, fileNoString);
        file.setPath(url);
//        fileRepository.save(file);
        return file;
    }


}
