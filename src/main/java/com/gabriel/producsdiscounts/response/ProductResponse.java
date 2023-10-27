package com.gabriel.producsdiscounts.response;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Builder
public class ProductResponse {
    private String id;
    private String name;
    private String price;
}

