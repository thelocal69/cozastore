package com.cybersoft.cozastore.service.impl;

import com.cybersoft.cozastore.converter.ColorConverter;
import com.cybersoft.cozastore.dto.ColorDTO;
import com.cybersoft.cozastore.repository.ColorRepository;
import com.cybersoft.cozastore.service.IColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorService implements IColorService {

    private final ColorRepository colorRepository;
    private final ColorConverter colorConverter;

    @Autowired
    public ColorService( final ColorRepository colorRepository, final ColorConverter colorConverter) {
        this.colorRepository = colorRepository;
        this.colorConverter = colorConverter;
    }


    @Override
    public List<ColorDTO> getAllColor() {
        return colorConverter.toColorDTOList(colorRepository.findAll());
    }
}
