package com.bitchinc.romantics.config;

import com.mongodb.Mongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.net.UnknownHostException;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.bitchinc.romantics.controller"})
@EnableMongoRepositories(basePackages = "com.bitchinc.romantics.service")
public class MVCConfig {

    @Bean
    public MongoTemplate mongoTemplate() throws UnknownHostException {
        return new MongoTemplate(new Mongo("localhost"), "applifyed");
    }
}
