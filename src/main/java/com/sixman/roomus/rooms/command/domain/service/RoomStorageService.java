package com.sixman.roomus.rooms.command.domain.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface RoomStorageService {

    String fileSaveToStorage(MultipartFile file) throws IOException;

}
