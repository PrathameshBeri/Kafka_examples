package entities;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class StockSerializer implements Serializer<Stock> {

   private ObjectMapper mapper = new ObjectMapper();

    @Override
    public byte[] serialize(String s, Stock stock) {
        try {
            return mapper.writeValueAsBytes(stock);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


}
