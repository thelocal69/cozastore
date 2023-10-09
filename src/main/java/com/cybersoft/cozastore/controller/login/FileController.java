package com.cybersoft.cozastore.controller.login;

import com.cybersoft.cozastore.payload.BaseResponse;
import com.cybersoft.cozastore.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("file")
public class FileController {

    private final IFileService fileService;

    @Autowired
    public FileController(final IFileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileName) throws IOException {
        byte[] fileData = fileService.downloadFile(fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse> uploadFile(@RequestBody MultipartFile file) throws IOException{
        String message = fileService.uploadFile(file);
        return ResponseEntity.status(HttpStatus.OK).body(
                new BaseResponse(
                        "OK",
                        "upload complete",
                        message
                )
        );
    }
}
