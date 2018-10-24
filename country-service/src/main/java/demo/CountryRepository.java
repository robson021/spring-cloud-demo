package demo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CountryRepository extends ReactiveMongoRepository<CountryEntity, Long> {
    Mono<CountryEntity> findByCapitalCityContainingIgnoreCase(String capitalCity);
}
