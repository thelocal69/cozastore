package com.cybersoft.cozastore.controller.login;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @GetMapping("")
    public ResponseEntity<?> getCart(){
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
