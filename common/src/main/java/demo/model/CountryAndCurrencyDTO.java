package demo.model;

public class CountryAndCurrencyDTO {

    private CountryDTO country;

    private CurrencyExchangeDTO currencyExchange;

    public CountryAndCurrencyDTO(CountryDTO country, CurrencyExchangeDTO currencyExchange) {
        this.country = country;
        this.currencyExchange = currencyExchange;
    }

    public CountryAndCurrencyDTO() {
    }

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }

    public CurrencyExchangeDTO getCurrencyExchange() {
        return currencyExchange;
    }

    public void setCurrencyExchange(CurrencyExchangeDTO currencyExchange) {
        this.currencyExchange = currencyExchange;
    }

    @Override
    public String toString() {
        return "CountryAndCurrencyDTO{" +
                "country=" + country +
                ", currencyExchange=" + currencyExchange +
                '}';
    }
}
