package com.scaler.productservicejune24.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// to configure something that's not needed to be created more than once

@Configuration // one of special classes that contains bean annotation
public class ApplicationsConfig {
    @Bean // spring will allow to create only one object of this method
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
