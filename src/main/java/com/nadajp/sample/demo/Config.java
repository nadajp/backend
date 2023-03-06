package com.nadajp.sample.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {
   
    @Bean 
    public RestTemplate restTemplate() {
        RestTemplate template = new RestTemplate();
        // include other settings here
        return template;
    }
}
