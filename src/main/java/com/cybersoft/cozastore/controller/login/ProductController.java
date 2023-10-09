package com.cybersoft.cozastore.controller.login;

import com.cybersoft.cozastore.dto.ProductDTO;
import com.cybersoft.cozastore.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController( final IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<?> getProduct(){
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> insertProduct(@RequestParam String name,
                                           @RequestParam MultipartFile file,
                                           @RequestParam Double price,
                                           @RequestParam int quantity,
                                           @RequestParam int idColor,
                                           @RequestParam int idSize,
                                           @RequestParam int idCategory) throws IOException {


        return new ResponseEntity<>(productService.insert(name, file, price, quantity, idColor, idSize, idCategory), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<?> updateProduct(){
        return new ResponseEntity<>("update product", HttpStatus.OK);
    }
}
