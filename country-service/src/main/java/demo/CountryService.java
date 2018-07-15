package demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class CountryService {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CommandLineRunner runner(CountryRepository repository) {
        return args -> {
            Stream.of(  //
                    new CountryEntity("United States of America", "Washington DC", "USD"),
                    new CountryEntity("Great Britain", "London", "GBP"),
                    new CountryEntity("Poland", "Warsaw", "PLN"))
                    .forEach(repository::save);
            repository.findAll().forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(CountryService.class, args);
    }
}
