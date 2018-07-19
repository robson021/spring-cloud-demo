package demo;

import demo.model.CountryDTO;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
                .orElseThrow(() -> {
                    log.warn("Could not found any country with capital city matching pattern: {}", capitalCity);
                    return new CountryNotFoundException();
                });
        log.info("Found: {}", entity);
        return modelMapper.map(entity, CountryDTO.class);
    }

    @ExceptionHandler({IncorrectResultSizeDataAccessException.class})
    public ResponseEntity<?> handleSearchExceptions(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity //
                .status(HttpStatus.BAD_REQUEST)
                .body("More than one result has been found");
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Country not found")
    private class CountryNotFoundException extends RuntimeException {
    }
}
