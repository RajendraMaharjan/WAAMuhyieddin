package edu.miu.waalab.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
