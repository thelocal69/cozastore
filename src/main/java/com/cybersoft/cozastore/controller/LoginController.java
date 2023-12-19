package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.dto.LoginDTO;
import com.cybersoft.cozastore.dto.UserDTO;
import com.cybersoft.cozastore.payload.BaseResponse;
import com.cybersoft.cozastore.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class LoginController {


    private final ILoginService loginService;

    @Autowired
    public LoginController(
                           final ILoginService loginService){
        this.loginService = loginService;
    }

    @PostMapping("/admin/signin")
    public ResponseEntity<BaseResponse> signIn(@RequestBody LoginDTO loginDTO){
//        SecretKey key =  Keys.secretKeyFor(SignatureAlgorithm.HS256);
//        String secretString = Encoders.BASE64.encode(key.getEncoded());
        //System.out.println("test " + secretString);

//        ResponseCookie responseCookie = ResponseCookie.from("jwt", token)
//                .httpOnly(true)
//                .secure(true)
//                .path("/")
//                .maxAge(60 * 60 * 1000)
//                .build();
        return ResponseEntity.status(HttpStatus.OK).body(
                new BaseResponse(
                        "Success",
                        "Login is successfully !",
                        this.loginService.loginAdmin(loginDTO)
                )
        );
    }

    @PostMapping("/signup")
    public ResponseEntity<BaseResponse> signup(@Valid @RequestBody UserDTO userDTO){
        userDTO.setRoleId(2);
    return ResponseEntity.status(HttpStatus.OK).body(
           new BaseResponse(
                   "Success",
                   "Ok",
                   loginService.upsert(userDTO)
           )
    );
    }
}
