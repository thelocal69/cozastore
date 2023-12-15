package com.cybersoft.cozastore.service.impl;

import com.cybersoft.cozastore.converter.SizeConverter;
import com.cybersoft.cozastore.dto.SizeDTO;
import com.cybersoft.cozastore.repository.SizeRepository;
import com.cybersoft.cozastore.service.ISizeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeService implements ISizeService {
    private final SizeRepository sizeRepository;
    private final SizeConverter sizeConverter;

    public SizeService(final SizeRepository sizeRepository, final SizeConverter sizeConverter) {
        this.sizeRepository = sizeRepository;
        this.sizeConverter = sizeConverter;
    }


    @Override
    public List<SizeDTO> getAllSize() {
        return sizeConverter.toSizeDTOList(sizeRepository.findAll());
    }

    @Override
    public List<String> getAllSizeName() {
        return sizeRepository.getAllSizeName();
    }
}
