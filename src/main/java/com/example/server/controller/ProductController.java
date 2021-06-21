package com.example.server.controller;

import com.example.server.entity.Product;
import com.example.server.model.dto.ProductDTO;
import com.example.server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping()
    public ProductDTO create (@RequestBody Product product){
        return productService.save(product);
    }

    @GetMapping("/find-all")
    public List<ProductDTO> findAll (){
        return productService.findAll();
    }

    @GetMapping("/seeding")
    public String seeding (){
        return productService.seeding();
    }
}
