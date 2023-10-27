package com.gabriel.producsdiscounts.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Builder
public class DiscountResponse {

    private String id;
    private String cupom;
    private String desconto;
}
