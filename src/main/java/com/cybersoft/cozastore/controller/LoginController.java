/*
package com.cybersoft.cozastore.controller.login;

import com.cybersoft.cozastore.dto.UserDTO;
import com.cybersoft.cozastore.payload.BaseResponse;
import com.cybersoft.cozastore.service.ILoginService;
import com.cybersoft.cozastore.util.JWTHelper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JWTHelper jwtHelper;
    private final Gson gson;
    private final ILoginService loginService;

    @Autowired
    public LoginController(final AuthenticationManager authenticationManager,
                           final JWTHelper jwtHelper,
                           final Gson gson,
                           final ILoginService loginService) {
        this.authenticationManager = authenticationManager;
        this.jwtHelper = jwtHelper;
        this.gson = gson;
        this.loginService = loginService;
    }

    @PostMapping("/signin")
    public ResponseEntity<BaseResponse> signIn(@RequestParam String email, @RequestParam String password){
//        SecretKey key =  Keys.secretKeyFor(SignatureAlgorithm.HS256);
//        String secretString = Encoders.BASE64.encode(key.getEncoded());
        //System.out.println("test " + secretString);
        UsernamePasswordAuthenticationToken authen = new UsernamePasswordAuthenticationToken(
                email
                , password
        );
        authenticationManager.authenticate(authen);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<SimpleGrantedAuthority> roles = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
        String jsonRole = gson.toJson(roles);
        String token = jwtHelper.generateToken(jsonRole);
        return ResponseEntity.status(HttpStatus.OK).body(
                new BaseResponse(
                        "Success",
                        "Yes",
                        token
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
*/
