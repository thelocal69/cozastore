package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.ResponseObject;
import com.cybersoft.cozastore.service.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/size")
public class SizeController {
    private final ISizeService sizeService;

    @Autowired
    public SizeController(ISizeService sizeService) {
        this.sizeService = sizeService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(
                        200,
                        "Get all size is completed !",
                        sizeService.getAllSize()
                )
        );
    }
}
