package com.federico.facchin.ejercicio1y2;

import com.federico.facchin.ejercicio1y2.configuration.ConfigurationProps;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConfigurationProps.class)
public class Ejercicio1y2Application {

	public static void main(String[] args) {
		SpringApplication.run(Ejercicio1y2Application.class, args);
	}

}
