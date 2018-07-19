package demo;

import demo.model.CountryAndCurrencyDTO;
import demo.model.CountryDTO;
import demo.model.CurrencyExchangeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    private final CurrencyServiceProxy currencyServiceProxy;

    private final CountryServiceProxy countryServiceProxy;

    @SuppressWarnings("all")
    public TestController(CurrencyServiceProxy currencyServiceProxy, CountryServiceProxy countryServiceProxy) {
        this.currencyServiceProxy = currencyServiceProxy;
        this.countryServiceProxy = countryServiceProxy;
    }

    @RequestMapping("/test")
    public CountryAndCurrencyDTO test() {
        log.info("Test endpoint request");
        CountryDTO country = countryServiceProxy.getCountryInfo("washington");
        log.info("Found country: {}", country);
        CurrencyExchangeDTO exchangeRates = currencyServiceProxy.getExchangeRates(country.getCurrency());
        log.info("Found exchange rates: {}", exchangeRates);
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
