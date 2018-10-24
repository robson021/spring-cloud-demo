package demo;

import demo.model.CurrencyExchangeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeRateController {

    private static final Logger log = LoggerFactory.getLogger(CurrencyExchangeRateController.class);

    private final String currencyExchangeUrl;

    private final String currencyToken;

    private final WebClient webClient;

    public CurrencyExchangeRateController(Environment env, WebClient webClient) {
        this.currencyExchangeUrl = env.getProperty("currency-exchange-url");
        this.currencyToken = env.getProperty("currency-token");
        this.webClient = webClient;
        log.info("currency exchange url: {}", currencyExchangeUrl);
        log.info("currency token: {}", currencyToken);
    }

    @GetMapping("/test")
    public Mono<CurrencyExchangeDTO> test() {
        return this.getExchangeRates("usd");
    }

    @GetMapping("/{currency}")
    public Mono<CurrencyExchangeDTO> getExchangeRates(@PathVariable String currency) {
        log.info("Request for exchange rates of currency: {}", currency);
        String url = currencyExchangeUrl.replace(currencyToken, currency);
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(CurrencyExchangeDTO.class);
    }

}
