package demo;

import demo.model.City;
import demo.model.WeatherInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private static final Logger log = LoggerFactory.getLogger(WeatherController.class);

    private final RestTemplate restTemplate;

    private final String moscowWeatherUrl;

    public WeatherController(RestTemplate restTemplate, Environment env) {
        this.restTemplate = restTemplate;
        this.moscowWeatherUrl = env.getProperty("moscow-weather-url");
        log.info("moscow-weather-url: {}", moscowWeatherUrl);
    }

    @GetMapping("/{city}")
    public City getWeather(@PathVariable String city) {
        log.info("Request for weather conditions in city: {}", city);
        if (!city.equalsIgnoreCase("Moscow"))
            throw new UnsupportedCityException();

        City cityResponse = restTemplate.exchange(moscowWeatherUrl, HttpMethod.GET, null, WeatherInfo.class)
                .getBody()
                .getCity();

        log.info("Received data for city: {}", cityResponse.getName());
        return cityResponse;
    }

    @ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED, reason = "Sorry, given city is currently not supported")
    private class UnsupportedCityException extends RuntimeException {
    }
}
