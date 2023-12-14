package com.cybersoft.cozastore.converter;

import com.cybersoft.cozastore.dto.ColorDTO;
import com.cybersoft.cozastore.entity.ColorEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ColorConverter {
    public ColorDTO toColorDTO(ColorEntity colorEntity){
        ColorDTO colorDTO = new ColorDTO();
        colorDTO.setId(colorEntity.getId());
        colorDTO.setName(colorEntity.getName());
        colorDTO.setCreateDate(colorEntity.getCreateDate());
        return colorDTO;
    }

    public List<ColorDTO> toColorDTOList(List<ColorEntity> colorEntityList){
        return colorEntityList.stream().map(this::toColorDTO).collect(Collectors.toList());
    }
}
