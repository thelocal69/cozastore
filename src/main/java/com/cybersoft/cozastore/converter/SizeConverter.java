package com.cybersoft.cozastore.converter;

import com.cybersoft.cozastore.dto.SizeDTO;
import com.cybersoft.cozastore.entity.SizeEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SizeConverter {
    public SizeDTO toSizeDTO(SizeEntity sizeEntity){
        SizeDTO sizeDTO = new SizeDTO();
        sizeDTO.setId(sizeEntity.getId());
        sizeDTO.setName(sizeEntity.getName());
        sizeDTO.setCreateDate(sizeEntity.getCreateDate());
        return sizeDTO;
    }

    public List<SizeDTO> toSizeDTOList(List<SizeEntity> sizeEntityList){
        return sizeEntityList.stream().map(this::toSizeDTO).collect(Collectors.toList());
    }
}
