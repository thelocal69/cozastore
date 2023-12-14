package com.cybersoft.cozastore.service.impl;

import com.cybersoft.cozastore.converter.CategoryConverter;
import com.cybersoft.cozastore.dto.CategoryDTO;
import com.cybersoft.cozastore.repository.CategoryRepository;
import com.cybersoft.cozastore.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    @Autowired
    public CategoryService(final CategoryRepository categoryRepository,
                           final CategoryConverter categoryConverter) {
        this.categoryRepository = categoryRepository;
        this.categoryConverter = categoryConverter;
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        return categoryConverter.toCategoryDTOList(categoryRepository.findAll());
    }
}
