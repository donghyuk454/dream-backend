package com.dream.application.web.auth.config;

import com.dream.application.web.auth.interceptor.LoginInterceptor;
import com.dream.application.web.auth.resolver.LoginArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class AuthConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns("/css/**", "/images/**", "/js/**", "/api/v1/auth/**", "/api/v1/login", "/api/v1/members");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginArgumentResolver());
    }
}
