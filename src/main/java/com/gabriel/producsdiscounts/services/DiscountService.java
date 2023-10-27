package com.gabriel.producsdiscounts.services;

import com.gabriel.producsdiscounts.response.DiscountResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DiscountService {
    private final WebClient webClient;

    public DiscountService(final WebClient.Builder webClient) {
        this.webClient = webClient.baseUrl("http://localhost:3000/").build();
    }

    public Flux<DiscountResponse> findAll() {
        final var discounts = webClient.get().uri("descontos/")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(DiscountResponse.class);
        return discounts;
    }

    public Mono<DiscountResponse> findByParameter(String parameter) {
        final var discounts = webClient.get().uri("descontos?" + parameter)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(DiscountResponse.class);

        final var discountMono = discounts.collectList().flatMap(discount -> {
            if (discount.isEmpty()) {
                return Mono.just(
                        DiscountResponse
                                .builder()
                                .build()
                );
            }
            final var firstDiscount = discount.get(0);
            return Mono.just(firstDiscount);
        });
        return discountMono;
    }

}
