package com.cybersoft.cozastore.service.impl;

import com.cybersoft.cozastore.entity.CategoryEntity;
import com.cybersoft.cozastore.entity.ColorEntity;
import com.cybersoft.cozastore.entity.ProductEntity;
import com.cybersoft.cozastore.entity.SizeEntity;
import com.cybersoft.cozastore.repository.ProductRepository;
import com.cybersoft.cozastore.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    @Value("root.folder")
    private final String rootFolder;

    @Autowired
    public ProductService( final ProductRepository productRepository, @Value("root.folder") final String rootFolder) {
        this.productRepository = productRepository;
        this.rootFolder = rootFolder;
    }

    @Override
    public Boolean insert(String name,
                             MultipartFile file,
                             Double price,
                             int quantity,
                             int idColor,
                             int idSize,
                             int idCategory) throws IOException {
        String pathImage = rootFolder +"/"+ file.getOriginalFilename();
        Path path = Paths.get(rootFolder);
        Path pathImageCopy = Paths.get(pathImage);
        if (!(Files.exists(path))){
            Files.createDirectories(path);
        }
        Files.copy(file.getInputStream(), pathImageCopy, StandardCopyOption.REPLACE_EXISTING);
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(name);
        productEntity.setImage(file.getOriginalFilename());
        productEntity.setPrice(price);
        productEntity.setQuantity(quantity);
        ColorEntity color = new ColorEntity();
        productEntity.setColor(color);
        SizeEntity size = new SizeEntity();
        productEntity.setSize(size);
        CategoryEntity categoryEntity= new CategoryEntity();
        productEntity.setCategory(categoryEntity);
        if (productRepository.save(productEntity) != null){
            return true;
        }else {
            return false;
        }
    }
}
