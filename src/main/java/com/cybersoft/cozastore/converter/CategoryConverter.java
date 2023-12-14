package com.cybersoft.cozastore.converter;

import com.cybersoft.cozastore.dto.CategoryDTO;
import com.cybersoft.cozastore.entity.CategoryEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryConverter {
    public CategoryDTO toCategoryDTO(CategoryEntity categoryEntity){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(categoryEntity.getId());
        categoryDTO.setName(categoryEntity.getName());
        categoryDTO.setCreateDate(categoryEntity.getCreateDate());
        return categoryDTO;
    }

    public List<CategoryDTO> toCategoryDTOList(List<CategoryEntity> categoryEntityList){
        return categoryEntityList.stream().map(this::toCategoryDTO).collect(Collectors.toList());
    }
}
