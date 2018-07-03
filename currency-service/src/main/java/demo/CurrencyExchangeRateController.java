package demo;

import demo.model.CurrencyExchangeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeRateController {

    private static final Logger log = LoggerFactory.getLogger(CurrencyExchangeRateController.class);

    private final String currencyExchangeUrl;

    private final String currencyToken;

    private final RestTemplate restTemplate;

    public CurrencyExchangeRateController(Environment env, RestTemplate restTemplate) {
        this.currencyExchangeUrl = env.getProperty("currency-exchange-url");
        this.currencyToken = env.getProperty("currency-token");
        this.restTemplate = restTemplate;
        log.info("currency exchange url: {}", currencyExchangeUrl);
        log.info("currency token: {}", currencyToken);
    }

    @GetMapping("/{currency}")
    public CurrencyExchangeDTO getExchangeRates(@PathVariable String currency) {
        log.info("Request for exchange rates of currency: {}", currency);
        String url = currencyExchangeUrl.replace(currencyToken, currency);
        return restTemplate //
                .getForEntity(url, CurrencyExchangeDTO.class)
                .getBody();
    }
}