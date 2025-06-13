package com.github.timebetov.identityservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "identity")
@Getter @Setter
public class IdentityContactInfoDto {

    private String message;
    private Map<String, String> contactDetails;
}
