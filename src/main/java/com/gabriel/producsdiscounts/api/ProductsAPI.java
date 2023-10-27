package com.gabriel.producsdiscounts.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;

@RequestMapping("products")
@Tag(name = "products", description = "tudo relacionado a produtos e discontos")
public interface ProductsAPI {


    @GetMapping
    @Operation(description = "Pega todos os produtos é possivel passar um parametro")
    Flux<?> findAllProducts(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "cupom", required = false) String cupom);

}
