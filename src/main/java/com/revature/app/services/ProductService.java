package com.revature.app.services;

import com.revature.app.daos.ProductDAO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductService {
    private final ProductDAO productDAO;
}
