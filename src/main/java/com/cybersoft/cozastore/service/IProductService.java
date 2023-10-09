package com.cybersoft.cozastore.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IProductService {
    Boolean insert(String name,
                   MultipartFile file,
                   Double price,
                   int quantity,
                   int idColor,
                   int idSize,
                   int idCategory) throws IOException;
}
