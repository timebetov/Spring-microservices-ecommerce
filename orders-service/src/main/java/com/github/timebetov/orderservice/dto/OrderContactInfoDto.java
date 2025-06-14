package com.github.timebetov.orderservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "order")
@Getter @Setter
public class OrderContactInfoDto {

    private String message;
    private Map<String, String> contactDetails;
}
