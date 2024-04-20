package com.volt.bootcampcrud01.services;

import com.volt.bootcampcrud01.dtos.CategoryDTO;
import com.volt.bootcampcrud01.dtos.ProductDTO;
import com.volt.bootcampcrud01.entities.Category;
import com.volt.bootcampcrud01.entities.Product;
import com.volt.bootcampcrud01.repositories.CategoryRepository;
import com.volt.bootcampcrud01.repositories.ProductRepository;
import com.volt.bootcampcrud01.services.exceptions.DataBaseException;
import com.volt.bootcampcrud01.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;

@Service
public class ProductService implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;
    public Page<ProductDTO> findAllPaged(PageRequest pageRequest){
        Page<Product> list = repository.findAll(pageRequest);
        return list.map(ProductDTO::new);
    }
    @Transactional
    public ProductDTO findById(Long id){
        Optional<Product> optional = repository.findById(id);
        Product entity = optional.orElseThrow(() -> new ResourceNotFoundException("Entity not found!"));

        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        copyDtotoEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try{
            Product entity = repository.getReferenceById(id);
            copyDtotoEntity(dto, entity);
            entity = repository.save(entity);
            return new ProductDTO(entity);

        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id doesn't exist." + id);
        }
    }
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Id doesn't exist. " + id);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException("Integrity Violation!");
        }
    }
    private void copyDtotoEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgURL(dto.getImgURL());
        entity.setDate(dto.getDate());

        entity.getCategories().clear();
        for(CategoryDTO categoryDTO: dto.getCategories()){
            Category category = categoryRepository.getReferenceById(categoryDTO.getId());
            entity.getCategories().add(category);
        }
    }


}