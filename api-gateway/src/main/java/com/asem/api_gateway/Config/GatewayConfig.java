package com.asem.api_gateway.Config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class GatewayConfig {

    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> {
            String clientIP = exchange.getRequest()
                    .getHeaders()
                    .getFirst("X-Forwarded-For");

            if (clientIP == null || clientIP.isEmpty()) {
                clientIP = exchange.getRequest()
                        .getRemoteAddress() != null ?
                        exchange.getRequest().getRemoteAddress().getAddress().getHostAddress() :
                        "unknown";
            } else {
                // X-Forwarded-For can contain multiple IPs, take the first one
                clientIP = clientIP.split(",")[0].trim();
            }

            return Mono.just(clientIP);
        };
    }
}
