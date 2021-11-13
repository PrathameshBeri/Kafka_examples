package entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
                include =  JsonTypeInfo.As.EXISTING_PROPERTY,
                property = "type")
@JsonSubTypes(
        {
                @JsonSubTypes.Type(value = EquityStock.class, name = EquityStock.TYPE),
                @JsonSubTypes.Type(value = BondStock.class, name = BondStock.TYPE)
        }
)
public abstract class Stock {

    @JsonProperty("stock_name")
    String name;

    @JsonProperty("stock_price")
    Double price;

    @JsonProperty("price_time")
    String time;

    public Stock(String name, Double price, String time) {
        this.name = name;
        this.price = price;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return "Stock{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", time='" + time + '\'' +
                '}';
    }

    public String toCSVString() {
        return this.name +"," + this.price+"," +this.time.toString();
    }
}
