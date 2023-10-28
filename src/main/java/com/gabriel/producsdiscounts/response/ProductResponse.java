package com.gabriel.producsdiscounts.response;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Builder;
import lombok.Getter;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Builder
@Getter
public class ProductResponse {
    private String id;
    private String name;
    private float price;
}

