package com.revature.app.services;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import java.math.BigDecimal;
import com.revature.app.models.Product;
import com.revature.app.daos.ProductDAO;
import java.util.ArrayList;

/**
 * This is the ProductServiceTest class, it contains
 * the methods necessary to test ProductService methods
 */
public class ProductServiceTest {
    /**
     * Here we mock our ProductDAO and ProductService
     * in order to test their associated methods
     */
    @Mock
    private ProductDAO productDAO;
    private ProductService productService;

    /**
     * setUp creates the environment for tests to be run
     */
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(productDAO);
    }

    /**
     * testGetAllProducts tests the ability to return a list of products to display
     */

    @Test
    public void testGetAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        Product p1 = new Product("1", "grapes", "fruit", BigDecimal.valueOf(28.00), "a fruit");
        Product p2 = new Product("2", "shoe", "footwear", BigDecimal.valueOf(50.50), "basketball shoes");
        products.add(p1);
        products.add(p2);
        when(productService.findAllProducts()).thenReturn(products);
        List<Product> returnedProducts = productService.findAllProducts();
        if (returnedProducts == null) {
            fail();
        } else {
            assertTrue(returnedProducts.contains(p1));
            assertTrue(returnedProducts.contains(p2));
        }
    }

    /**
     * testGetProductsByName tests the ability of the app to retrieve
     * a list of products based off of the name of the products
     */
    @Test
    public void testGetProductsByName() {
        ArrayList<Product> products = new ArrayList<>();
        Product p1 = new Product("1", "grapes", "fruit", BigDecimal.valueOf(28.00), "a fruit");
        products.add(p1);
        when(productService.findProductsByName("grapes")).thenReturn(products);
        List<Product> returnedProducts = productService.findProductsByName("grapes");
        if (returnedProducts == null) {
            fail();
        } else {
            assertTrue(returnedProducts.contains(p1));
        }
    }

    /**
     * testGetProductsByCategory tests the ability of the app to
     * retrieve a list that contains all of the products that
     * share the same category.
     */
    @Test
    public void testGetProductsByCategory() {
        ArrayList<Product> products = new ArrayList<>();
        Product p1 = new Product("1", "grapes", "fruit", BigDecimal.valueOf(28.00), "a fruit");
        products.add(p1);
        when(productService.findProductsByCategory("fruit")).thenReturn(products);
        List<Product> returnedProducts = productService.findProductsByCategory("fruit");
        if (returnedProducts == null) {
            fail();
        } else {
            assertTrue(returnedProducts.contains(p1));
        }
    }

    /**
     * testGetAllProductCategories tests the ability of the app to
     * return a list that contains all of the categories in the product
     * list.
     */
    @Test
    public void testGetAllProductCategories() {
        ArrayList<String> categories = new ArrayList<>();
        String category1 = "fruit";
        String category2 = "sports";
        categories.add(category1);
        categories.add(category2);
        when(productService.findAllProductCategories()).thenReturn(categories);
        List<String> returnedCategories = productService.findAllProductCategories();
        if (returnedCategories == null) {
            fail();
        } else {
            assertTrue(returnedCategories.contains(category1));
            assertTrue(returnedCategories.contains(category2));
        }
    }

    /**
     * testGetProductById tests the app's ability to comb through the database
     * to find the product that matches the given product id
     */
    @Test
    public void testGetProductById() {
        Product p1 = new Product("1", "grapes", "fruit", BigDecimal.valueOf(28.00), "a fruit");
        when(productService.findProductById("1")).thenReturn(p1);

        Product product = productService.findProductById("1");
        assertTrue(product.equals(p1));
    }

    /**
     * testPriceRange tests the ability of the program to take two BigDecimal values
     * and return all
     * of the products whose price lies inbetween those values through the
     * findProductsByPriceRange
     * productService method.
     */
    @Test
    public void testPriceRange() {
        ArrayList<Product> products = new ArrayList<>();
        Product p1 = new Product("1", "grapes", "fruit", BigDecimal.valueOf(25.50), "a fruit");
        products.add(p1);
        when(productService.findProductsByPriceRange(10.00, 30.00)).thenReturn(products);
        List<Product> returnedProducts = productService.findProductsByPriceRange(10.00, 30.00);
        if (returnedProducts == null) {
            fail();
        } else {
            assertTrue(returnedProducts.contains(p1));
        }
    }
}
