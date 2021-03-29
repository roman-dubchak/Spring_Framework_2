package com.geekbrains.geekspring.wsdproducts;

import com.geekbrains.geekspring.repositories.ProductRepository;
import com.geekbrains.geekspring.wsdproducts.generation.GetProductsRequest;
import com.geekbrains.geekspring.wsdproducts.generation.GetProductsResponse;
import com.geekbrains.geekspring.wsdproducts.generation.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.time.LocalDate;

@Endpoint
public class ProductsEndpoint {
    private static final String NAMESPACE_URI = "http://www.geekbrains.com/geekspring/wsdproducts/generation";
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductsRequest")
    @ResponsePayload
    public GetProductsResponse getProductsResponse (@RequestPayload GetProductsRequest request){
        GetProductsResponse response = new GetProductsResponse();
        com.geekbrains.geekspring.entities.Product productEntities = new com.geekbrains.geekspring.entities.Product();

        productEntities = productRepository.findById(request.getId()).get();
        System.out.println("request.getId() " + request.getId());
        Product product = new Product();
        product.setId(productEntities.getId());
        product.setPrice(productEntities.getPrice());
        product.setVendorCode(productEntities.getVendorCode());
        product.setTitle(productEntities.getTitle());
        product.setCategoryId(productEntities.getCategory().getId());
        product.setShortDescription(productEntities.getShortDescription());
        product.setFullDescription(productEntities.getFullDescription());
        product.setCreatedAt(LocalDate.from(productEntities.getCreatedAt()));
        product.setUpdatedAt(LocalDate.from(productEntities.getUpdatedAt()));
        response.setProduct(product);

        return response;
    }

}
