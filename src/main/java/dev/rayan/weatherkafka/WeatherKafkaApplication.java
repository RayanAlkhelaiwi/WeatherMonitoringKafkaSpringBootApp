package dev.rayan.weatherkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@EnableConfigurationProperties
public class WeatherKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherKafkaApplication.class, args);
	}
}
