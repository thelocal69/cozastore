package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.dto.ProductDTO;
import com.cybersoft.cozastore.payload.OutputResponse;
import com.cybersoft.cozastore.payload.ResponseObject;
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

    @GetMapping("/api/getAll")
    public ResponseEntity<ResponseObject> getProduct(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(
                        200,
                        "Get all succesfully",
                        this.productService.getAll()
                )
        );
    }

    @GetMapping("")
    public ResponseEntity<OutputResponse> getAllProduct(@RequestParam int page, @RequestParam String sortBy, String sortField){
        return new ResponseEntity<>(
                productService.getPageProduct(page, sortBy, sortField), HttpStatus.OK
        );
    }

    @GetMapping("/api/productName")
    public ResponseEntity<ResponseObject> getProductByName(@RequestParam String name){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(
                        200,
                        "Get product is completed !",
                        productService.getProductByName(name)
                )
        );
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

    @PostMapping("/api/insert")
    public ResponseEntity<ResponseObject> insert(@RequestBody ProductDTO productDTO){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(
                        200,
                        "Insert successfully",
                        this.productService.upsert(productDTO)
                )
        );
    }

    @PutMapping("/api/update/id/{id}")
    public ResponseEntity<ResponseObject> update(@RequestBody ProductDTO productDTO, @PathVariable int id){
        productDTO.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(
                        200,
                        "Update successfully",
                        this.productService.upsert(productDTO)
                )
        );
    }

    @DeleteMapping("/api/delete/id/{id}")
    public ResponseEntity<ResponseObject> delete(@RequestBody ProductDTO productDTO, @PathVariable int id){
        productDTO.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(
                        200,
                        "Delete is complete",
                        this.productService.delete(productDTO)
                )
        );
    }
}
