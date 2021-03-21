package com.geekbrains.geekspring.wsdproducts;

import com.geekbrains.geekspring.repositories.ProductRepository;
import com.geekbrains.geekspring.wsdproducts.generation.GetProductsResponse;
import com.geekbrains.geekspring.wsdproducts.generation.GetProductsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ProductsEndpoint {
    private static final String NAMESPACE_URI = "http:www.geekbrains.com/geekspring/wsdproducts/generation";
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductsRequest")
    @ResponsePayload
    public GetProductsResponse getProductsResponse (@RequestPayload GetProductsRequest request){
        GetProductsResponse response = new GetProductsResponse();
//        response.setProduct(productRepository.findById(request.getId()).get());
        response.setProductEntities(productRepository.findById(request.getId()).get());
        return response;
    }
}
