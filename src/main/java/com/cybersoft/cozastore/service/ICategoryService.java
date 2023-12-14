package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.dto.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> getAllCategory();
}
