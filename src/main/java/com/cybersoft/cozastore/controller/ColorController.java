package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.ResponseObject;
import com.cybersoft.cozastore.service.IColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/color")
public class ColorController {
    private final IColorService colorService;

    @Autowired
    public ColorController(IColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(
                        200,
                        "Get all color is completed !",
                        colorService.getAllColor()
                )
        );
    }

    @GetMapping("/api/getName")
    public ResponseEntity<ResponseObject> getAllName(){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(
                        200,
                        "Get all color name is completed !",
                        colorService.getAllColorName()
                )
        );
    }
}
