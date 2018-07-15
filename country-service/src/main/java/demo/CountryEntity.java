package demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CountryEntity {

    @Id
    @GeneratedValue
    @Column(updatable = false)
    private Long id;

    private String name;

    private String capitalCity;

    private String currency;

    public CountryEntity() {
    }

    public CountryEntity(String name, String capitalCity, String currency) {
        this.name = name;
        this.capitalCity = capitalCity;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapitalCity() {
        return capitalCity;
    }

    public void setCapitalCity(String capitalCity) {
        this.capitalCity = capitalCity;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "CountryEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capitalCity='" + capitalCity + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
