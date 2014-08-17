package com.bitchinc.romantics.config;

import com.mongodb.Mongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.net.UnknownHostException;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.bitchinc.romantics.controller"})
@EnableMongoRepositories(basePackages = "com.bitchinc.romantics.service")
public class MVCConfig {
    private String MONGO_HOST_NAME = "localhost";
    private String DATABASE_NAME = "applifyed";
    private Integer MONGO_PORT = 27017;
    @Bean
    public MongoTemplate mongoTemplate() throws UnknownHostException {
        return new MongoTemplate(new Mongo(MONGO_HOST_NAME), DATABASE_NAME);
    }

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        Mongo mongo = new Mongo(MONGO_HOST_NAME, MONGO_PORT);
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongo,
                DATABASE_NAME);
        return mongoDbFactory;
    }

    @Bean
    public GridFsTemplate gridFsTemplate() throws Exception{
        MongoDbFactory dbFactory = mongoDbFactory();
        MongoConverter converter = mongoConverter();
        GridFsTemplate gridFsTemplate = new GridFsTemplate(dbFactory, converter);
        return gridFsTemplate;
    }

    @Bean
    public MongoConverter mongoConverter() throws Exception{
        MongoMappingContext mappingContext = new MongoMappingContext();
        MappingMongoConverter mappingMongoConverter = new MappingMongoConverter(mongoDbFactory(), mappingContext);
        mappingMongoConverter.afterPropertiesSet();
        return mappingMongoConverter;
    }

//    @Bean
//    public StandardServletMultipartResolver multipartResolver(){
//        return new StandardServletMultipartResolver();
//    }
    @Bean
    public MultipartResolver multipartResolver()
    {
        // return new StandardServletMultipartResolver();
        return new CommonsMultipartResolver();
    }
}
