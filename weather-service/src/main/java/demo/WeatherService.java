package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class WeatherService {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder rtb) {
        return rtb.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(WeatherService.class, args);
    }
}