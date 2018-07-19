package demo;

import demo.model.CountryDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/country-info")
public class CountryServiceController {

    private static final Logger log = LoggerFactory.getLogger(CountryServiceController.class);

    private final CountryRepository repository;

    private final ModelMapper modelMapper;

    public CountryServiceController(CountryRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{capitalCity}")
    public CountryDTO getCountryInfo(@PathVariable String capitalCity) {
        log.info("Request for capital city: {}", capitalCity);
        CountryEntity entity = repository  //
                .findByCapitalCityContainingIgnoreCase(capitalCity)
                .orElseThrow(CountryNotFoundException::new);
        return modelMapper.map(entity, CountryDTO.class);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Country not found")
    private class CountryNotFoundException extends RuntimeException {
    }
}
