package com.school.SpringSecuritywithDatabase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {
    public static final String USER_REST_TEMPLATE = "userRestTemplate";

    public static final String DB_BUSINESS = "dbBusiness";

    @Bean(name =USER_REST_TEMPLATE)
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Bean(name =DB_BUSINESS)
    public RestTemplate restTemplateDB(){
        return new RestTemplate();
    }
}
