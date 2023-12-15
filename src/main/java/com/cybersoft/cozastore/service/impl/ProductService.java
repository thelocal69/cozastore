package com.cybersoft.cozastore.service.impl;

import com.cybersoft.cozastore.converter.ProductConverter;
import com.cybersoft.cozastore.dto.ProductDTO;
import com.cybersoft.cozastore.entity.CategoryEntity;
import com.cybersoft.cozastore.entity.ColorEntity;
import com.cybersoft.cozastore.entity.ProductEntity;
import com.cybersoft.cozastore.entity.SizeEntity;
import com.cybersoft.cozastore.payload.OutputResponse;
import com.cybersoft.cozastore.repository.CategoryRepository;
import com.cybersoft.cozastore.repository.ColorRepository;
import com.cybersoft.cozastore.repository.ProductRepository;
import com.cybersoft.cozastore.repository.SizeRepository;
import com.cybersoft.cozastore.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;
    private final CategoryRepository categoryRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;

    @Value("root.folder")
    private final String rootFolder;

    @Autowired
    public ProductService( final ProductRepository productRepository, @Value("root.folder") final String rootFolder,
                           final ProductConverter productConverter,
                           final CategoryRepository categoryRepository,
                           final ColorRepository colorRepository,
                           final SizeRepository sizeRepository) {
        this.productRepository = productRepository;
        this.rootFolder = rootFolder;
        this.productConverter = productConverter;
        this.categoryRepository = categoryRepository;
        this.colorRepository = colorRepository;
        this.sizeRepository = sizeRepository;
    }

    @Override
    public List<ProductDTO> getAll() {
        List<ProductEntity> productEntityList = productRepository.findAll();
        return productConverter.toProductDTOList(productEntityList);
    }

    @Override
    public List<ProductDTO> getProductByName(String name) {
        List<ProductEntity> productEntityList = productRepository.findAllByNameContainingIgnoreCase(name);
        if (productEntityList.isEmpty()){
            return null;
        }
        return productConverter.toProductDTOList(productEntityList);
    }

    @Override
    public OutputResponse getPageProduct(int page, String sortBy, String sortField) {
        int limit = 6;
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.fromString(sortBy),sortField));
        List<ProductDTO> productDTOList = productConverter.toProductDTOList(
                productRepository.findAll(pageable).getContent()
        );
        int totalItem = (int) productRepository.count();
        int totalPage = (int) Math.ceil((double) totalItem / limit);
        return new OutputResponse(
                page,
                totalPage,
                totalItem,
                productDTOList
        );
    }

    @Override
    public Boolean insert(String name,
                             MultipartFile file,
                             Double price,
                             int quantity,
                             int idColor,
                             int idSize,
                             int idCategory) throws IOException {
        String pathImage = rootFolder +"/"+ file.getOriginalFilename();
        Path path = Paths.get(rootFolder);
        Path pathImageCopy = Paths.get(pathImage);
        if (!(Files.exists(path))){
            Files.createDirectories(path);
        }
        Files.copy(file.getInputStream(), pathImageCopy, StandardCopyOption.REPLACE_EXISTING);
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(name);
        productEntity.setImage(file.getOriginalFilename());
        productEntity.setPrice(price);
        productEntity.setQuantity(quantity);
        ColorEntity color = new ColorEntity();
        productEntity.setColor(color);
        SizeEntity size = new SizeEntity();
        productEntity.setSize(size);
        CategoryEntity categoryEntity= new CategoryEntity();
        productEntity.setCategory(categoryEntity);
        if (productRepository.save(productEntity) != null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public ProductDTO upsert(ProductDTO productDTO) {
        ProductEntity productEntity;
        if (productDTO.getId() == 0){
            ProductEntity product = productConverter.toProductEntity(productDTO);
            productEntity = productRepository.save(product);
        }else {
            ProductEntity oldProduct = productRepository.findOneById(productDTO.getId());
            productEntity = productConverter.toProductEntity(productDTO, oldProduct);
        }
        CategoryEntity categoryEntity = categoryRepository.findOneByName(productDTO.getCategoryName());
        ColorEntity color = colorRepository.findOneByName(productDTO.getColorName());
        SizeEntity size = sizeRepository.findOneByName(productDTO.getSizeName());
        productEntity.setCategory(categoryEntity);
        productEntity.setColor(color);
        productEntity.setSize(size);
        return productConverter.toProductDTO(productRepository.save(productEntity));
    }

    @Override
    public String delete(ProductDTO productDTO) {
        boolean exist = productRepository.existsById(productDTO.getId());
        if (exist){
            this.productRepository.deleteById(productDTO.getId());
            return "Delete id: "+productDTO.getId()+" successfully !";
        }
        return "Data not found and cannot delete !";
    }
}
