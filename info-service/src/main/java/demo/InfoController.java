package demo;

import demo.model.CountryAndCurrencyDTO;
import demo.model.CountryDTO;
import demo.model.CurrencyExchangeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/info-service")
public class InfoController {

    private static final Logger log = LoggerFactory.getLogger(InfoController.class);

    private final CountryServiceProxy countryServiceProxy;

    private final CurrencyServiceProxy currencyServiceProxy;

    @SuppressWarnings("all")
    public InfoController(CountryServiceProxy countryServiceProxy, CurrencyServiceProxy currencyServiceProxy) {
        this.countryServiceProxy = countryServiceProxy;
        this.currencyServiceProxy = currencyServiceProxy;
    }

    @GetMapping("/country/{capitalCity}")
    public CountryAndCurrencyDTO getCountryInfo(@PathVariable String capitalCity) {
        CountryDTO country = countryServiceProxy.getCountryInfo(capitalCity);
        CurrencyExchangeDTO exchangeRates = currencyServiceProxy.getExchangeRates(country.getCurrency());
        return new CountryAndCurrencyDTO(country, exchangeRates);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handleAllExceptions(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity //
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }
}
