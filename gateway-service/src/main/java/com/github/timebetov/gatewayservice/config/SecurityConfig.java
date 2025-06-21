package com.github.timebetov.gatewayservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverSecurity) {

        serverSecurity.authorizeExchange(exchanges -> exchanges
                .pathMatchers("/ecommerce/products/**").authenticated()
                .pathMatchers("/ecommerce/orders/**").authenticated()
                .pathMatchers("/ecommerce/basket/**").authenticated())
                .oauth2ResourceServer(serverSpec -> serverSpec
                        .jwt(Customizer.withDefaults()));

        serverSecurity.csrf(Customizer.withDefaults());
        return serverSecurity.build();
    }
}
