package com.target.myretail.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;


@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${mongo.database}")
    String mongoDatabase;
    @Value("${mongo.uri}")
    String url;

    @Override
    protected String getDatabaseName() {
        return mongoDatabase.toString();
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoClient(), getDatabaseName());
    }

    @Override
    public MongoClient mongoClient() {
        String mongoURL = "mongodb://" + url.toString();
        MongoClientURI uri = new MongoClientURI(mongoURL);
        return new MongoClient(uri);
    }

}
