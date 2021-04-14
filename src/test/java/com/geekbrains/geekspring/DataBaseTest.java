package com.geekbrains.geekspring;

import com.geekbrains.geekspring.entities.Category;
import com.geekbrains.geekspring.entities.Product;
import com.geekbrains.geekspring.repositories.ProductRepository;
import com.geekbrains.geekspring.services.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@PropertySource("/database.properties")
@TestPropertySource("/database.properties")
public class DataBaseTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    public void testProductSave_thenOK(){
        Product product = new Product();
        product.setPrice(120.0);
        product.setTitle("Apple iPhone 12");
        product.setFullDescription("very-very well");
        product.setShortDescription("very well");
        product.setVendorCode("Apple");
        product.setCategory(new Category());

        Product saveProduct = productRepository.save(product);
        System.out.println(saveProduct);
        Assert.assertNotNull(saveProduct);
    }

}

