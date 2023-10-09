package com.cybersoft.cozastore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private int id;
    @NotNull
    @NotBlank(message = "username not empty")
    private String userName;
    @NotNull
    @NotBlank(message = "password not empty")
    private String passWord;
    @NotNull
    @Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    @NotBlank(message = "email not empty")
    private String email;
    private int roleId;
    private Date createDate;
}
