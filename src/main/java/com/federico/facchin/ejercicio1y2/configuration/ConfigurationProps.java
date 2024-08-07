package com.federico.facchin.ejercicio1y2.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("database")
public record ConfigurationProps(String username , String password, String databaseName) {
}
