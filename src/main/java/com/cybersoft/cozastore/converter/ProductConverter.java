package com.cybersoft.cozastore.converter;

import com.cybersoft.cozastore.dto.ProductDTO;
import com.cybersoft.cozastore.entity.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductConverter {
    public ProductDTO toProductDTO(ProductEntity productEntity){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productEntity.getId());
        productDTO.setName(productEntity.getName());
        productDTO.setImage(productEntity.getImage());
        productDTO.setQuantity(productEntity.getQuantity());
        productDTO.setDescription(productEntity.getDescription());
        productDTO.setSizeName(productEntity.getSize().getName());
        productDTO.setPrice(productEntity.getPrice());
        productDTO.setColorName(productEntity.getColor().getName());
        productDTO.setCategoryName(productEntity.getCategory().getName());
        productDTO.setCreateDate(productEntity.getCreateDate());
        return  productDTO;
    }

    public List<ProductDTO> toProductDTOList(List<ProductEntity> productEntityList){
        return productEntityList.stream().map(this::toProductDTO).collect(Collectors.toList());
    }

    public ProductEntity toProductEntity(ProductDTO productEntityproductDTO){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productEntityproductDTO.getId());
        productEntity.setName(productEntityproductDTO.getName());
        productEntity.setDescription(productEntityproductDTO.getDescription());
        productEntity.setImage(productEntityproductDTO.getImage());
        productEntity.setQuantity(productEntityproductDTO.getQuantity());
        productEntity.setPrice(productEntityproductDTO.getPrice());

        return  productEntity;
    }

    public ProductEntity toProductEntity(ProductDTO productDTO, ProductEntity productEntity){
        productEntity.setId(productDTO.getId());
        productEntity.setImage(productDTO.getImage());
        productEntity.setName(productDTO.getName());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setQuantity(productDTO.getQuantity());
        productEntity.setCreateDate(productDTO.getCreateDate());
        productEntity.setDescription(productDTO.getDescription());
        return  productEntity;
    }
}
