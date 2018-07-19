package demo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CountryRepository extends MongoRepository<CountryEntity, Long> {
    Optional<CountryEntity> findByCapitalCityContainingIgnoreCase(String capitalCity);
}
