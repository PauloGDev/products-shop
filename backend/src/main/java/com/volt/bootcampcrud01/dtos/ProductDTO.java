package com.volt.bootcampcrud01.dtos;

import com.volt.bootcampcrud01.entities.Category;
import com.volt.bootcampcrud01.entities.Product;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class ProductDTO {
    private Long id;

    private String name;
    private String description;
    private Double price;
    private String imgURL;
    private Instant date;

    Set<CategoryDTO> categories = new HashSet<>();

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, String description, Double price, String imgURL, Instant date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgURL = imgURL;
        this.date = date;
    }
    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imgURL = product.getImgURL();
        this.date = product.getDate();
    }

    public ProductDTO(Product product, Set<Category> categories){
        this(product);
        categories.forEach(x -> this.categories.add(new CategoryDTO(x)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public Instant getDate() {
        return date;
    }

    public Instant setDate() {
        date = Instant.now();
        return null;
    }

    public Set<CategoryDTO> getCategories() {
        return categories;
    }

}
