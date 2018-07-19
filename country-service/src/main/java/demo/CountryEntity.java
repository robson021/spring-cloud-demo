package demo;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class CountryEntity {

    @Id
    private ObjectId id;

    private String name;

    private String capitalCity;

    private String currency;

    @PersistenceConstructor
    public CountryEntity(ObjectId id, String name, String capitalCity, String currency) {
        this.id = id;
        this.name = name;
        this.capitalCity = capitalCity;
        this.currency = currency;
    }

    CountryEntity(String name, String capitalCity, String currency) {
        this.name = name;
        this.capitalCity = capitalCity;
        this.currency = currency;
    }

    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCapitalCity() {
        return capitalCity;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryEntity that = (CountryEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
