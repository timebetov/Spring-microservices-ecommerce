package com.github.timebetov.productservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "products")
@Getter @Setter
public class ProductsContactInfoDto {

    private String message;
    private Map<String, String> contactDetails;
}
