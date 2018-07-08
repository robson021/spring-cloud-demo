package demo;

import demo.model.CurrencyExchangeDTO;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("currency-service")
@RibbonClient("currency-service")
public interface CurrencyServiceProxy {

    @GetMapping("/currency-exchange/{currency}")
    CurrencyExchangeDTO getExchangeRates(@PathVariable String currency);

}
