package com.volt.bootcampcrud01.services;

import com.volt.bootcampcrud01.dtos.CategoryDTO;
import com.volt.bootcampcrud01.entities.Category;
import com.volt.bootcampcrud01.repositories.CategoryRepository;
import com.volt.bootcampcrud01.services.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;
    public List<CategoryDTO> findAll(){
        List<Category> list = repository.findAll();
        return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
    }

    public CategoryDTO findById(Long id){
        Optional<Category> optional = repository.findById(id);
        Category entity = optional.orElseThrow(() -> new EntityNotFoundException("Entity not found!"));

        return new CategoryDTO(entity);
    }
}
