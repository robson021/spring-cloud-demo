package demo;

import demo.model.CountryDTO;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/country-info")
public class CountryServiceController {

    private final CountryRepository repository;

    private final ModelMapper modelMapper;

    public CountryServiceController(CountryRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all-countries")
    public Flux<CountryEntity> test() {
        return repository.findAll();
    }

    @GetMapping("/{capitalCity}")
    public Mono<CountryDTO> getCountryInfo(@PathVariable String capitalCity) {
        return repository.findByCapitalCityContainingIgnoreCase(capitalCity)
                .map(countryEntity -> modelMapper.map(countryEntity, CountryDTO.class))
                .log();
    }

}
