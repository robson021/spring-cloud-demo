package demo;

import demo.model.WeatherDTO;
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
@RequestMapping("/weather")
public class WeatherController {

    private static final Logger log = LoggerFactory.getLogger(WeatherController.class);

    private final String moscowWeatherUrl;

    private final WebClient webClient;

    public WeatherController(Environment env, WebClient webClient) {
        this.webClient = webClient;
        this.moscowWeatherUrl = env.getProperty("moscow-weather-url");
        log.info("moscow-weather-url: {}", moscowWeatherUrl);
    }

    @GetMapping("/test")
    public Mono<WeatherDTO> test() {
        return this.getWeather("Moscow");
    }

    @GetMapping("/{city}")
    public Mono<WeatherDTO> getWeather(@PathVariable String city) {
        log.info("Request for weather conditions in city: {}", city);
        return webClient.get()
                .uri(moscowWeatherUrl)
                .retrieve()
                .bodyToMono(WeatherDTO.class);
    }

}
