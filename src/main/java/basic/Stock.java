package basic;

public class Stock {

    String name;
    Double price;
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
