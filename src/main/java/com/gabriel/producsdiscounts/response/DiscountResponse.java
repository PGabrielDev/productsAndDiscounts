package com.gabriel.producsdiscounts.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;
import lombok.Getter;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Builder
@Getter
public class DiscountResponse {

    private String id;
    private String cupom;
    private float desconto;
}
