package entities;


import com.fasterxml.jackson.annotation.JsonProperty;

public class BondStock extends Stock{

    public static final String TYPE = "Bond_Stock";

    public BondStock(@JsonProperty("stock_name") String name, @JsonProperty("stock_price") Double price,
                     @JsonProperty("stock_time") String time){
        super(name, price, time);
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String toString() {
        return "BondStock{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", time='" + time + '\'' +
                '}';
    }
}
