package com.sixman.roomus.commons.infra;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.sixman.roomus.commons.service.AwsS3Service;
import com.sixman.roomus.product.command.domain.exception.NullContentTypeException;
import lombok.RequiredArgsConstructor;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AwsS3ServiceImpl implements AwsS3Service {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3 amazonS3;

    // 파일 업로드
    @Override
    public String fileUpload(String domainName, MultipartFile uploadFile) throws IOException {
        String contentType = uploadFile.getContentType();
        if (contentType == null) {
            throw new NullContentTypeException("타입이 지정되어있지 않은 파일입니다.");
        }
        ObjectMetadata objectMetadata = new ObjectMetadata(); // 파일의 metaData 객체 생성(헤더에 담을 정보)
        objectMetadata.setContentLength(uploadFile.getInputStream().available()); // 파일의 크기(길이) 메타데이터에 저장
        objectMetadata.setContentType(contentType); // 파일의 타입 메타데이터에 저장 (ex. image/jpg, application/json ...)

        // 파일 경로, 이름 설정
        String[] contentTypeList = contentType.split("/");
        String exetention = contentTypeList[contentTypeList.length - 1];
        String filePath = getFilePath(domainName, exetention); // 'bucket이름/디렉토리명'의 형식으로 file이 저장될 path를 지정해준다.
        String fileName = getRandomFileName(exetention);
        // 파일 업로드
        amazonS3.putObject(new PutObjectRequest(filePath, fileName, uploadFile.getInputStream(), objectMetadata));
        return amazonS3.getUrl(filePath, fileName).toString();

    }

    private String getRandomFileName(String exetention) {
        return LocalDate.now().toString().concat(String.valueOf(UUID.randomUUID()).concat("." + exetention));
    }

    private String getFilePath(String domainName, String exetention) {
        return bucket
                + "/" + domainName
                + "/" + exetention;
    }
}
