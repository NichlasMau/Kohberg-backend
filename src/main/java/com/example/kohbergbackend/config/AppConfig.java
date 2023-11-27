package com.example.kohbergbackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {
    private final String databaseConnectionString;

    @Autowired
    public AppConfig(@Value("${DATABASE_CONNECTION_STRING}") String databaseConnectionString) {
        this.databaseConnectionString = databaseConnectionString;
    }

    public String getDatabaseConnectionString() {
        return databaseConnectionString;
    }
}
