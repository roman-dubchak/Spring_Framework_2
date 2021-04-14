package com.geekbrains.geekspring;

import com.geekbrains.geekspring.controllers.AdminController;
import com.geekbrains.geekspring.controllers.CartController;
import com.geekbrains.geekspring.controllers.ProductController;
import com.geekbrains.geekspring.controllers.ShopController;
import com.geekbrains.geekspring.entities.Category;
import com.geekbrains.geekspring.entities.Product;
import com.geekbrains.geekspring.entities.SystemUser;
import com.geekbrains.geekspring.entities.User;
import com.geekbrains.geekspring.repositories.ProductRepository;
import com.geekbrains.geekspring.repositories.UserRepository;
import com.geekbrains.geekspring.services.OrderService;
import com.geekbrains.geekspring.services.ProductService;
import com.geekbrains.geekspring.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void doProductFailTest(){
        Product product = new Product();
        product.setTitle("Apple");
        product.setPrice(120.0);
        product.setFullDescription("very-very well");
        product.setShortDescription("very well");
        product.setVendorCode("Apple");
        product.setCategory(new Category());

        Mockito.doReturn(null)
                .when(productRepository)
                .findOneByTitle("Apple");

        boolean isExistsProduct =  productService.isProductWithTitleExists(product.getTitle());
        Assert.assertFalse(isExistsProduct);
    }

}
