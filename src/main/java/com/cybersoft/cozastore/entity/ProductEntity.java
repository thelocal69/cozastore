package com.cybersoft.cozastore.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "image")
    private String image;
    @Column(name = "price")
    private Double price;
    @Column(name = "description")
    private String description;
    @Column(name = "quanity")
    private int quantity;
    @Column(name = "create_date")
    private Date createDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_category")
    private CategoryEntity category;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_size")
    private SizeEntity size;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_color")
    private ColorEntity color;


}
