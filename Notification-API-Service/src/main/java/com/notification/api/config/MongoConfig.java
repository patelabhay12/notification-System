package com.notification.api.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.UuidRepresentation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Bean
    MongoClient mongoClient() {

        ConnectionString connectionString =
                new ConnectionString("mongodb://admin:admin123@localhost:27017/notification_service?authSource=admin");

        MongoClientSettings settings =
                MongoClientSettings.builder()
                        .applyConnectionString(connectionString)
                        .uuidRepresentation(UuidRepresentation.STANDARD)
                        .build();

        return MongoClients.create(settings);
    }
}