package com.humber.edgeserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import reactor.core.publisher.Mono;

@Configuration
public class GatewayConfig {

    Logger logger = LoggerFactory.getLogger(GatewayConfig.class);

    // CORS configuration for Next.js frontend - Axios requests
    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("*");
        corsConfig.addAllowedMethod(HttpMethod.GET);
        corsConfig.addAllowedMethod(HttpMethod.POST);
        corsConfig.addAllowedMethod(HttpMethod.DELETE);
        corsConfig.addAllowedMethod(HttpMethod.PUT);
        corsConfig.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("products", r -> r.path("/products/**")
                        .filters(f->f.rewritePath("/api/(?<service>.*)/(?<remaining>.*)", "/${service}/${remaining}").
                                circuitBreaker(config -> config.setName("userCircuitBreaker").setFallbackUri("forward:/fallback")))
                        .uri("lb://product-service"))
                .route("orders", r -> r.path("/orders/**")
                        .filters(f->f.rewritePath("/api/(?<service>.*)/(?<remaining>.*)", "/${service}/${remaining}"))
                        .uri("lb://order-service"))
                .build();
    }

    @Bean
    public GlobalFilter customGlobalFilter() {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String requestMethod = request.getMethod().name();
            String requestPath = request.getPath().toString();

            //System.out.println("Incoming request " + requestMethod + " " + requestPath);
            logger.info("Incoming request " + requestMethod + " " + requestPath);

            long startTime = System.currentTimeMillis();

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                // Capture and log response details
                ServerHttpResponse response = exchange.getResponse();
                HttpStatusCode responseStatus = response.getStatusCode();
                long duration = System.currentTimeMillis() - startTime;

                //System.out.println("Outgoing response with status code " + responseStatus + " processed in " + duration + " ms");
                logger.info("Outgoing response with status code " + responseStatus + " processed in " + duration + " ms");
            }));
        };
    }
}
