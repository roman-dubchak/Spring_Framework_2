package com.geekbrains.geekspring;

import com.geekbrains.geekspring.repositories.ProductRepository;
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

    @Test
    public void testProductSave(){

    }

}

