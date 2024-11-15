package com.flatrock.delivery.config;

import com.flatrock.common.jwt.JwtInterceptor;
import com.flatrock.common.jwt.TokenManager;
import com.flatrock.common.security.ServiceEnum;
import com.flatrock.delivery.rest.OrderServiceClient;
import feign.Contract;
import feign.Feign;
import jakarta.servlet.ServletContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


@Configuration
public class WebConfigurer implements ServletContextInitializer {

    private final Logger log = LoggerFactory.getLogger(WebConfigurer.class);

    private final Environment env;

    private final TokenManager tokenManager;

    @Value("${application.services.order}")
    private String orderService;

    public WebConfigurer(Environment env, TokenManager tokenManager) {
        this.env = env;
        this.tokenManager = tokenManager;
    }

    @Override
    public void onStartup(ServletContext servletContext) {
        if (env.getActiveProfiles().length != 0) {
            log.info("Web application configuration, using profiles: {}", (Object[]) env.getActiveProfiles());
        }

        log.info("Web application fully configured");
    }

    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor(tokenManager, ServiceEnum.ORDER_SERVICE);
    }

    @Bean
    public OrderServiceClient orderServiceClient() {
        return Feign.builder()
                .contract(feignContract())
                .requestInterceptor(jwtInterceptor()).target(OrderServiceClient.class, orderService);
    }

    @Bean
    public Contract feignContract() {
        return new SpringMvcContract();
    }
}
