package demo;

import demo.model.CountryDTO;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

@FeignClient("country-service")
@RibbonClient("country-service")
public interface CountryServiceProxy {

    @GetMapping("/country-info/{capitalCity}")
    Mono<CountryDTO> getCountryInfo(@PathVariable String capitalCity);

}
