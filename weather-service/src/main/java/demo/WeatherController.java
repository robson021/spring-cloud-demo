package demo;

import demo.model.CityDTO;
import demo.model.WeatherDTO;
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

    @GetMapping("/test")
    public CityDTO test() {
        return this.getWeather("Moscow");
    }

    @GetMapping("/{city}")
    public CityDTO getWeather(@PathVariable String city) {
        log.info("Request for weather conditions in city: {}", city);

        if (!city.equalsIgnoreCase("Moscow"))
            throw new UnsupportedCityException();

        WeatherDTO body = restTemplate //
                .exchange(moscowWeatherUrl, HttpMethod.GET, null, WeatherDTO.class)
                .getBody();

        if (body == null)
            throw new CityNotFoundException();

        log.info("Received data for city: {}", body.getCity().getName());
        return body.getCity();
    }

    @ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED, reason = "Sorry, given city is currently not supported")
    private class UnsupportedCityException extends RuntimeException {
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "City not found")
    private class CityNotFoundException extends RuntimeException {
    }
}
