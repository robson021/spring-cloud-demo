package demo;

import demo.model.CurrencyExchangeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    private final CurrencyServiceProxy currencyServiceProxy;

    @SuppressWarnings("all")
    public MainController(CurrencyServiceProxy currencyServiceProxy) {
        this.currencyServiceProxy = currencyServiceProxy;
    }

    @RequestMapping("/test")
    public CurrencyExchangeDTO test() {
        log.info("Test endpoint request");
        return currencyServiceProxy.getExchangeRates("usd");
    }
}
