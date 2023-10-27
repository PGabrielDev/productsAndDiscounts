package com.gabriel.producsdiscounts.api.controller;

import com.gabriel.producsdiscounts.api.ProductsAPI;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ProductController  implements ProductsAPI {
    @Override
    public Flux<?> findAllProducts(String name, String cupom) {
        return null;
    }
}
