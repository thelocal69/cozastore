package com.cybersoft.cozastore.service.impl;

import com.cybersoft.cozastore.entity.FileEntity;
import com.cybersoft.cozastore.repository.FileRepository;
import com.cybersoft.cozastore.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService implements IFileService {

    private final FileRepository fileRepository;

    @Autowired
    public FileService(final FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public String uploadFile(MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();
        String typeFile = file.getContentType();
        byte[] data = file.getBytes();
        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(fileName);
        fileEntity.setType(typeFile);
        fileEntity.setData(data);
        FileEntity result = fileRepository.save(fileEntity);
        return (result != null) ? "Upload file "+fileName+" thanh cong" : "khong thanh cong";
    }

    @Override
    public byte[] downloadFile(String fileName) throws IOException {
        FileEntity file = fileRepository.findOneByName(fileName);
        return file.getData();
    }
}
