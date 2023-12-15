package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.dto.ProductDTO;
import com.cybersoft.cozastore.payload.OutputResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProductService {
    List<ProductDTO> getAll();
    List<ProductDTO> getProductByName(String name);
    OutputResponse getPageProduct(int page, String sortBy, String sortField);

    Boolean insert(String name,
                   MultipartFile file,
                   Double price,
                   int quantity,
                   int idColor,
                   int idSize,
                   int idCategory) throws IOException;

    ProductDTO upsert(ProductDTO productDTO);
    String delete(ProductDTO productDTO);
}
