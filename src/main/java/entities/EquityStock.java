package entities;


import com.fasterxml.jackson.annotation.JsonProperty;

public class EquityStock extends Stock{

    public static final String TYPE = "Equity_Stock";

    public EquityStock(@JsonProperty("stock_name") String name, @JsonProperty("stock_price") Double price,
                       @JsonProperty("stock_time") String time) {
        super(name, price, time);
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String toString() {
        return "EquityStock{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", time='" + time + '\'' +
                '}';
    }
}
