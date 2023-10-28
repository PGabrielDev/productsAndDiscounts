package com.gabriel.producsdiscounts.dtos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;

@Builder
@JsonSerialize
@Getter
public class ProductWithDiscountDTO {
    private String productId;
    private String productName;
    private float originValue;
    private float currentValue;
    private float discountValue;
    private String cupom;
}

