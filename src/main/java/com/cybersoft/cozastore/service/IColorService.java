package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.dto.ColorDTO;

import java.util.List;

public interface IColorService {
    List<ColorDTO> getAllColor();
    List<String> getAllColorName();
}
