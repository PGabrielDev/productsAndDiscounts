package com.gabriel.producsdiscounts.services;

import com.gabriel.producsdiscounts.response.DiscountResponse;
import com.gabriel.producsdiscounts.response.ProductResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service

public class ProductService {

    private final WebClient webClient;

    public ProductService(final WebClient.Builder webClient) {
        this.webClient = webClient.baseUrl("http://localhost:3000/").build();
    }

    public Flux<ProductResponse> findAll() {
        return this.webClient.get().uri("produtos/")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(ProductResponse.class);
    }

    public Flux<ProductResponse> findByParameter(String parameter) {
        return this.webClient.get().uri("produtos?name=" + parameter)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(ProductResponse.class);
    }

}
