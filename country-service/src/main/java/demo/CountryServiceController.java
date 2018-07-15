package demo;

import demo.model.CountryDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        CountryEntity entity = repository.findByCapitalCityContainingIgnoreCase(capitalCity);
        return modelMapper.map(entity, CountryDTO.class);
    }
}
