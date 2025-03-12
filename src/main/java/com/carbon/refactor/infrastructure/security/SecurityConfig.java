package com.carbon.refactor.infrastructure.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Security configuration for the application.
 * This class registers the JWT token filter to intercept requests.
 */
@Configuration
public class SecurityConfig {

    /**
     * Register the JWT token filter.
     * This filter will be applied to all API requests to extract user information from JWT tokens.
     * 
     * @param jwtTokenFilter The JWT token filter
     * @return The filter registration bean
     */
    @Bean
    public FilterRegistrationBean<JwtTokenFilter> jwtTokenFilterRegistration(JwtTokenFilter jwtTokenFilter) {
        FilterRegistrationBean<JwtTokenFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(jwtTokenFilter);
        registrationBean.addUrlPatterns("/api/*"); // Apply to all API endpoints
        registrationBean.setOrder(1); // Set the filter order (lower values have higher priority)
        return registrationBean;
    }
}
