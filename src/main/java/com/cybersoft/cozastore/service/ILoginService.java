package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.dto.LoginDTO;
import com.cybersoft.cozastore.dto.UserDTO;


public interface ILoginService {
    UserDTO upsert(UserDTO userDTO);
    String loginAdmin(LoginDTO loginDTO);
}
