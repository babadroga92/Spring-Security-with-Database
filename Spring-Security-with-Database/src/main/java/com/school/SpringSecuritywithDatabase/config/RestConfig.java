package com.school.SpringSecuritywithDatabase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {
    public static final String USER_REST_TEMPLATE_GET = "userGetRestTemplate";

    public static final String USER_REST_TEMPLATE_POST = "userPostRestTemplate";

    @Bean(name = USER_REST_TEMPLATE_GET)
    public RestTemplate restGetTemplate(){
        return new RestTemplate();
    }
    @Bean(name = USER_REST_TEMPLATE_POST)
    public RestTemplate restPostTemplate(){
        return new RestTemplate();
    }
}
