package com.sixman.roomus.rooms.command.infra;

import com.sixman.roomus.rooms.command.domain.service.RoomStorageService;
import com.sixman.roomus.commons.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class RoomStorageServiceImpl implements RoomStorageService {

    private final AwsS3Service awsS3Service;

    @Override
    public String fileSaveToStorage(MultipartFile file) throws IOException {
        return awsS3Service.fileUpload("room", file);
    }
}
