package com.revature.app.services;

import java.util.List;

import com.revature.app.daos.ProductDAO;
import com.revature.app.models.Product;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductService {
    private final ProductDAO productDAO;

    public List<Product> findAllProducts() {
        return productDAO.findAll();
    }

    public List<Product> findProductsByName(String name) {
        return productDAO.findByName(name);
    }

    public List<String> findAllProductCategories() {
        return productDAO.findAllProductCategories();
    }

    public List<Product> findProductsByCategory(String category) {
        return productDAO.findByCategory(category);
    }

    public List<Product> findProductsByPriceRange(double lowerLimit, double upperLimit) {
        return productDAO.findByPriceRange(lowerLimit, upperLimit);
    }

    public Product findProductById(String id) {
        return productDAO.findById(id);
    }
}
