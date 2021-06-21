package com.example.server.model.mapper;

import com.example.server.entity.Product;
import com.example.server.model.dto.ProductDTO;

public class ProductMapper {
    public static ProductDTO productDTO (Product product) {
        ProductDTO tmp = new ProductDTO();
        tmp.setName(product.getName());
        tmp.setPrice(product.getPrice());
        return tmp;
    }
}