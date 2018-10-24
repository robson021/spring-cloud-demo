package demo;

import demo.model.CountryAndCurrencyDTO;
import demo.model.CountryDTO;
import demo.model.CurrencyExchangeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/info")
public class InfoController {

    private final CountryServiceProxy countryServiceProxy;

    private final CurrencyServiceProxy currencyServiceProxy;

    @SuppressWarnings("all")
    public InfoController(CountryServiceProxy countryServiceProxy, CurrencyServiceProxy currencyServiceProxy) {
        this.countryServiceProxy = countryServiceProxy;
        this.currencyServiceProxy = currencyServiceProxy;
    }

    @RequestMapping("/test")
    public Mono<CountryAndCurrencyDTO> test() {
        return this.getCountryInfo("washington");
    }

    @GetMapping("/country/{capitalCity}")
    public Mono<CountryAndCurrencyDTO> getCountryInfo(@PathVariable String capitalCity) {
        Mono<CountryDTO> countryInfo = countryServiceProxy.getCountryInfo(capitalCity);
        Mono<CurrencyExchangeDTO> currencyExchange = countryInfo
                .flatMap(countryDTO -> Mono.just(countryDTO.getCurrency()))
                .flatMap(currencyServiceProxy::getExchangeRates);
        return currencyExchange.zipWith(countryInfo)
                .flatMap(tuple2 -> Mono.just(new CountryAndCurrencyDTO(tuple2.getT2(), tuple2.getT1())));
    }
}
