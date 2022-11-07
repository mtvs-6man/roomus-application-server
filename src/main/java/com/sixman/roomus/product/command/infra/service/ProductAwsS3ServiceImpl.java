package com.sixman.roomus.product.command.infra.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.sixman.roomus.product.command.domain.exception.NullContentTypeException;
import com.sixman.roomus.product.command.domain.service.ProductAwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductAwsS3ServiceImpl implements ProductAwsS3Service {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;


    // 파일 업로드
    @Override
    @Transactional
    public String fileUpload(String category, MultipartFile uploadFile) throws IOException {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(uploadFile.getInputStream().available());

        String fullBucket = bucket.concat(category);
        String contentType = uploadFile.getContentType();
        if (contentType == null) {
            throw new NullContentTypeException("타입이 지정되어있지 않은 파일입니다.");
        }
        objectMetadata.setContentType(contentType);
        String[] contentTypeList = contentType.split("/");
        String fileNameString = "p_".concat(String.valueOf(getRandomUuid())).concat("." + contentTypeList[contentTypeList.length - 1]);
        amazonS3.putObject(new PutObjectRequest(fullBucket, fileNameString, uploadFile.getInputStream(), objectMetadata));

        return amazonS3.getUrl(fullBucket, fileNameString).toString();

    }

    private static UUID getRandomUuid() {
        return UUID.randomUUID();
    }
//    @Override
//    public byte[] fileDownload(URL url) throws IOException {
//        System.out.println(url.toString());
//        S3Object downloadS3Object = amazonS3.getObject(new GetObjectRequest(bucket, url.toString()));
//        S3ObjectInputStream s3ObjectInputStream = downloadS3Object.getObjectContent();
//        return IOUtils.toByteArray(s3ObjectInputStream);
//    }
//    @Override
//    public byte[] fileDownload(String fileName) {
//        System.out.println("fileName = " + fileName);
//        amazonS3.getObject(new GetObjectRequest())
//    }
}
