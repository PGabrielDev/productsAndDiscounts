package com.gabriel.producsdiscounts.api.controller;

import com.gabriel.producsdiscounts.api.ProductsAPI;
import com.gabriel.producsdiscounts.dtos.ProductWithDiscountDTO;
import com.gabriel.producsdiscounts.response.DiscountResponse;
import com.gabriel.producsdiscounts.response.ProductResponse;
import com.gabriel.producsdiscounts.services.DiscountService;
import com.gabriel.producsdiscounts.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class ProductController  implements ProductsAPI {
    private final String RequestInvalidCupoms = "Nemhum Cumpom Valido informado";
    private final DiscountService discountService;
    private final ProductService productService;

    @Override
    public Flux<?> findAllProducts(String name, String cupom) {
        final var products = productService.findAll(name);
        if (cupom == null || cupom.isBlank()) {
            final var response  = products.flatMap(product -> {
                final ProductWithDiscountDTO dto = ProductWithDiscountDTO
                        .builder()
                        .productId(product.getId())
                        .productName(product.getName())
                        .cupom(RequestInvalidCupoms)
                        .currentValue(product.getPrice())
                        .originValue(product.getPrice())
                        .discountValue(0)
                        .build();
                return Mono.just(dto);
            });
            return response;
        }
        final var discounts = discountService.findByParameter(cupom);
        return products.flatMap( product -> {
            final var response = discounts.map(discount -> {
                final var productPrice = product.getPrice();
                final var percentage = discount.getDesconto() / 100;
                final var discountValue = productPrice * percentage;
                final ProductWithDiscountDTO dto = ProductWithDiscountDTO
                        .builder()
                        .productId(product.getId())
                        .productName(product.getName())
                        .cupom(discount.getCupom())
                        .currentValue(productPrice - discountValue)
                        .originValue(productPrice)
                        .discountValue(discountValue)
                        .build();
                return Mono.just(dto);
            });
            return response;
        }).flatMap(mono -> mono);
    }
}
