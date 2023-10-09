package com.cybersoft.cozastore.converter;

import com.cybersoft.cozastore.dto.ProductDTO;
import com.cybersoft.cozastore.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public ProductDTO toProductDTO(ProductEntity productEntity){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productEntity.getId());
        productDTO.setName(productEntity.getName());
        productDTO.setImage(productEntity.getImage());
        productDTO.setQuantity(productEntity.getQuantity());
        productDTO.setIdSize(productEntity.getSize().getId());
        productDTO.setPrice(productEntity.getPrice());
        productDTO.setIdColor(productEntity.getColor().getId());
        productDTO.setIdCategory(productEntity.getCategory().getId());

        return  productDTO;
    }

    public ProductEntity toProductEntity(ProductDTO productEntityproductDTO){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productEntityproductDTO.getId());
        productEntity.setName(productEntityproductDTO.getName());
        productEntity.setImage(productEntityproductDTO.getImage());
        productEntity.setQuantity(productEntityproductDTO.getQuantity());
        productEntity.setPrice(productEntityproductDTO.getPrice());

        return  productEntity;
    }
}
