package com.revature.app.services;

import java.util.List;

import com.revature.app.daos.ProductDAO;
import com.revature.app.models.Product;

import lombok.AllArgsConstructor;

/**
 * The ProductService class handles the buisness logic behind actions regarding
 * products
 */
@AllArgsConstructor
public class ProductService {
    private final ProductDAO productDAO;

    // findAllProducts returns a List of all the products through the productDAO
    // findAll method
    public List<Product> findAllProducts() {
        return productDAO.findAll();
    }

    // findProductsByName returns a List of all the products that share a name
    // through the productDAO findByName method
    public List<Product> findProductsByName(String name) {
        return productDAO.findByName(name);
    }

    // findAllProductCategories returns a List of all the categories of products
    // through the productDAO findAllProductCategories method
    public List<String> findAllProductCategories() {
        return productDAO.findAllProductCategories();
    }

    // findProductsByCategory returns a List of all the products that share a
    // category through the productDAO findByCategory method
    public List<Product> findProductsByCategory(String category) {
        return productDAO.findByCategory(category);
    }

    // findProductsByPrice returns a List of all the products whose price lies
    // between the range of two double values through the productDAO
    // findByPriceRange method
    public List<Product> findProductsByPriceRange(double lowerLimit, double upperLimit) {
        return productDAO.findByPriceRange(lowerLimit, upperLimit);
    }

    // findProductById returns a product whose id matches the value of the id
    // parameter through
    // the productDAO findById method
    public Product findProductById(String id) {
        return productDAO.findById(id);
    }
}
