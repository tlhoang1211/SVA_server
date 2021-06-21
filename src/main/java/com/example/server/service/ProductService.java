package com.example.server.service;

import com.example.server.entity.Product;
import com.example.server.model.dto.ProductDTO;
import com.example.server.model.mapper.ProductMapper;
import com.example.server.repository.AccountRepository;
import com.example.server.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProductRepository productRepository;



    // save
    public ProductDTO save (Product product) {
        productRepository.save(product);
        ProductDTO rs = ProductMapper.productDTO(product);
        return rs;
    }

    // find all
    public List<ProductDTO> findAll(){
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product item : products) {
            productDTOS.add(ProductMapper.productDTO(item));
        }
        return productDTOS;
    }

    // seeding hard code
    public String seeding() {
        Product iphone = new Product(1,"iphone", 1000);
        Product samsung = new Product(2,"samsung", 9000);
        Product oppo = new Product(3,"oppo", 500);
        Product vsmart = new Product(4,"vsmart", 300);
        Product bphone = new Product(5,"bphone", 350);

        List<Product> products = new ArrayList<>();
        products.add(iphone);
        products.add(samsung);
        products.add(oppo);
        products.add(vsmart);
        products.add(bphone);

        productRepository.saveAll(products);
        return "OK";
    }

}

