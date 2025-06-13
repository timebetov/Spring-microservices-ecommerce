package com.github.timebetov.basketservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "basket")
@Getter @Setter
public class BasketContactInfoDto {

    private String message;
    private Map<String, String> contactDetails;
}
