package com.cybersoft.cozastore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private int id;
    private String name;
    private String image;
    private Double price;
    private String description;
    private int quantity;
    private Date createDate;
    private String categoryName;
    private String sizeName;
    private String colorName;
}
