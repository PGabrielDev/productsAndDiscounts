package com.gabriel.producsdiscounts.services;

import com.gabriel.producsdiscounts.response.DiscountResponse;
import com.gabriel.producsdiscounts.response.ProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ProductService {

    private final WebClient webClient;

    public ProductService(final WebClient.Builder webClient) {
        this.webClient = webClient.baseUrl("https://653c7a2bd5d6790f5ec80428.mockapi.io/api/v1/").build();
    }

    public Flux<ProductResponse> findAll(String parameter) {
        final var uri =  (parameter == null || parameter.isEmpty()) ? "produtos" : "produtos?" + parameter;
        final var t = this.webClient.get().uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(ProductResponse.class);
        return t;
    }


}
