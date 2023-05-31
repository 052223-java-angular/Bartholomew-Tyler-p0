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

public class ProductServiceTest {
    @Mock
    private ProductDAO productDAO;
    private ProductService productService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // productDAO = new ProductDAO();
        // productService = new ProductService(mockProductDAO);
        // mockProductDAO = Mockito.mock(ProductDAO.class);
        productService = new ProductService(productDAO);
    }
    // Tests the ability to return a list of products to display
    @Test
    public void testGetAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        Product p1 = new Product("1", "grapes", "fruit", BigDecimal.valueOf(28.00), "a fruit");
        Product p2 = new Product("2", "shoe", "footwear", BigDecimal.valueOf(50.50), "basketball shoes");
        products.add(p1);
        products.add(p2);
        when(productService.findAllProducts()).thenReturn(products);
        List<Product> returnedProducts = productService.findAllProducts();
        if(returnedProducts == null){
            fail();
        }else{
            assertTrue(returnedProducts.contains(p1));
            assertTrue(returnedProducts.contains(p2));
        }
    }
    //Test if a product can be found by name
    @Test
    public void testGetProductsByName() {
        ArrayList<Product> products = new ArrayList<>();
        Product p1 = new Product("1", "grapes", "fruit", BigDecimal.valueOf(28.00), "a fruit");
        products.add(p1);
        when(productService.findProductsByName("grapes")).thenReturn(products);
        List<Product> returnedProducts = productService.findProductsByName("grapes");
        if(returnedProducts == null){
            fail();
        }else{
            assertTrue(returnedProducts.contains(p1));
        }
    }

    @Test
    public void testGetProductsByCategory() {
        ArrayList<Product> products = new ArrayList<>();
        Product p1 = new Product("1", "grapes", "fruit", BigDecimal.valueOf(28.00), "a fruit");
        products.add(p1);
        when(productService.findProductsByCategory("fruit")).thenReturn(products);
        List<Product> returnedProducts = productService.findProductsByCategory("fruit");
        if(returnedProducts == null){
            fail();
        }else{
            assertTrue(returnedProducts.contains(p1));
        }
    }

    @Test 
    public void testGetAllProductCategories() {
        ArrayList<String> categories = new ArrayList<>();
        String category1 = "fruit";
        String category2 = "sports";
        categories.add(category1);
        categories.add(category2);
        when(productService.findAllProductCategories()).thenReturn(categories);
        List<String> returnedCategories = productService.findAllProductCategories();
        if(returnedCategories == null){
            fail();
        }else{
            assertTrue(returnedCategories.contains(category1));
            assertTrue(returnedCategories.contains(category2));
        }
    }

    @Test
    public void testGetProductById() {
        Product p1 = new Product("1", "grapes", "fruit", BigDecimal.valueOf(28.00), "a fruit");
        when(productService.findProductById("1")).thenReturn(p1);

        Product product = productService.findProductById("1");
        assertTrue(product.equals(p1));
    }

    @Test
    public void testPriceRange() {
        ArrayList<Product> products = new ArrayList<>();
        Product p1 = new Product("1", "grapes", "fruit", BigDecimal.valueOf(25.50), "a fruit");
        products.add(p1);
        when(productService.findProductsByPriceRange(10.00, 30.00)).thenReturn(products);
        List<Product> returnedProducts = productService.findProductsByPriceRange(10.00, 30.00);
        if(returnedProducts == null){
            fail();
        }else{
            assertTrue(returnedProducts.contains(p1));
        }
    }
}
