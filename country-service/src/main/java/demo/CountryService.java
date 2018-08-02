package demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@EnableDiscoveryClient
@SpringBootApplication
public class CountryService {

    @Bean
    public CommandLineRunner runner(CountryRepository repository) {
        return args -> {
            List<CountryEntity> countries = Stream.of(  //
                    new CountryEntity("United States of America", "Washington DC", "USD"),
                    new CountryEntity("Great Britain", "London", "GBP"),
                    new CountryEntity("Poland", "Warsaw", "PLN"))
                    .collect(Collectors.toList());
            repository.saveAll(countries);
            repository.findAll().forEach(System.out::println);
        };
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(CountryService.class, args);
    }
}
