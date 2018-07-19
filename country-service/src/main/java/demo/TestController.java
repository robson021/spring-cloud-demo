package demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    private final CountryRepository repository;

    public TestController(CountryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/test")
    public List<CountryEntity> test() {
        return repository.findAll();
    }
}
