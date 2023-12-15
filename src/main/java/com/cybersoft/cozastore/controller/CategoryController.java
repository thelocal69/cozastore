package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.ResponseObject;
import com.cybersoft.cozastore.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(
                        200,
                        "Get all category is complete !",
                        categoryService.getAllCategory()
                )
        );
    }

    @GetMapping("/api/getName")
    public ResponseEntity<ResponseObject> getAllName(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(
                        200,
                        "Get all category name is complete !",
                        categoryService.getAllCategoryName()
                )
        );
    }
}
