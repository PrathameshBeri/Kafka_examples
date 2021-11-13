package entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class StockDeserializer implements Deserializer<Stock> {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Stock deserialize(String s, byte[] bytes) {
        try {
            return mapper.readValue(bytes, Stock.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
