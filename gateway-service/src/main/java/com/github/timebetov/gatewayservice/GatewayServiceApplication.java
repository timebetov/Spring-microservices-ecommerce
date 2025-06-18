package com.github.timebetov.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.time.Duration;

@SpringBootApplication
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

	@Bean
	public RouteLocator ecommerceRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(p -> p
						.path("/ecommerce/products/**")
						.filters(f -> f
								.rewritePath("/ecommerce/products/(?<segment>.*)", "/${segment}")
								.circuitBreaker(config -> config
										.setName("productsCB")
										.setFallbackUri("forward:/contactSupport")))
						.uri("lb://PRODUCTS"))
				.route(p -> p
						.path("/ecommerce/orders/**")
						.filters(f -> f
								.rewritePath("/ecommerce/orders/(?<segment>.*)", "/${segment}")
								.retry(retryConfig -> retryConfig
										.setRetries(3)
										.setMethods(HttpMethod.GET)
										.setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true)))
						.uri("lb://ORDERS"))
				.route(p -> p
						.path("/ecommerce/basket/**")
						.filters(f -> f.rewritePath("/ecommerce/basket(?<segment>.*)", "/${segment}"))
						.uri("lb://BASKET"))
				.route(p -> p
						.path("/ecommerce/identity/**")
						.filters(f -> f
								.rewritePath("/ecommerce/identity/(?<segment>.*)", "/${segment}")
								.requestRateLimiter(config -> config
										.setRateLimiter(redisRateLimiter())
										.setKeyResolver(userKeyResolver())))
						.uri("lb://IDENTITY"))
				.build();
	}

	@Bean
	public RedisRateLimiter redisRateLimiter() {
		return new RedisRateLimiter(1, 1, 1);
	}

	@Bean
	KeyResolver userKeyResolver() {
		return exchange -> Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("user"))
				.defaultIfEmpty("anonymous");
	}

}
