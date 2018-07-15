package demo;

import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<CountryEntity, Long> {
    CountryEntity findByCapitalCityContainingIgnoreCase(String capitalCity);
}
