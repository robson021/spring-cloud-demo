package demo;

import demo.model.CountryDTO;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;

@Configuration
public class CountryServiceRoutes {

    private final CountryRepository repository;

    private final ModelMapper modelMapper;

    public CountryServiceRoutes(CountryRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Bean
    @SuppressWarnings("All")
    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions.route(GET("/country-info/all-countries"), this::getAllCountries)
                .andRoute(GET("/country-info/{capitalCity}"), this::getCapitalCity);
    }

    private Mono<ServerResponse> getAllCountries(ServerRequest request) {
        return ServerResponse.ok().contentType(APPLICATION_JSON)
                .body(repository.findAll(), CountryEntity.class)
                .switchIfEmpty(notFound().build());
    }

    private Mono<ServerResponse> getCapitalCity(ServerRequest request) {
        return ServerResponse.ok().contentType(APPLICATION_JSON)
                .body(getCountryInfo(request.pathVariable("capitalCity")), CountryDTO.class)
                .switchIfEmpty(notFound().build());
    }

    private Mono<CountryDTO> getCountryInfo(String capitalCity) {
        return repository.findByCapitalCityContainingIgnoreCase(capitalCity)
                .map(countryEntity -> modelMapper.map(countryEntity, CountryDTO.class))
                .doOnNext(countryDTO -> System.out.println("Found: " + countryDTO));
    }
}
