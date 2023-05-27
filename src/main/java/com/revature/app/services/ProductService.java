package com.revature.app.services;

import java.util.List;

import com.revature.app.daos.ProductDAO;
import com.revature.app.models.Product;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductService {
    private final ProductDAO productDAO;

    public List<Product> findProductsByName(String name) {
        return productDAO.findByName(name);
    }
}
