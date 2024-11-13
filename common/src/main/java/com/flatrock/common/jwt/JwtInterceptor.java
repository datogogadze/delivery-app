package com.flatrock.common.jwt;

import com.flatrock.common.security.ServiceEnum;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.util.StringUtils;

public class JwtInterceptor implements RequestInterceptor {
    private final TokenManager tokenManager;
    private final ServiceEnum serviceEnum;

    public JwtInterceptor(TokenManager tokenManager, ServiceEnum serviceEnum) {
        this.tokenManager = tokenManager;
        this.serviceEnum = serviceEnum;
    }


    @Override
    public void apply(RequestTemplate template) {
        String token = tokenManager.getToken(serviceEnum);
        if (StringUtils.hasText(token)) {
            template.header("Authorization", "Bearer " + token);
        }
    }
}
