package com.revature.app.services;
import java.util.ArrayList;
import com.revature.app.daos.ProductDAO;
import com.revature.app.models.Product;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductService {
    private final ProductDAO productDAO;

    public ArrayList<Product> browseAllProducts() {
        return productDAO.findAll();
    }
}
