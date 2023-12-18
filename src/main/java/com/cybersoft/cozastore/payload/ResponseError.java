package com.cybersoft.cozastore.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseError {
    private int statusCode;
    private String message;;
    private Timestamp timestamp;
    private String error;
    private String path;
}
