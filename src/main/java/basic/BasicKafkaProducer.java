package basic;

import entities.BondStock;
import entities.EquityStock;
import entities.Stock;
import entities.StockSerializer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

public class BasicKafkaProducer implements Runnable {

    final String topicName = "getting_started_10";
    Random random = new Random();

    final Map<String, Object> config = Map.of(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092",
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName(),
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StockSerializer.class.getName(),
            ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true
           // ProducerConfig.CLIENT_ID_CONFIG, "customer_id_config"

    );

    KafkaProducer producer;

    BasicKafkaProducer(){
        producer = new KafkaProducer<String, Stock>(config);
    }

    @Override
    public void run() {
        int i = 0;
        try{
        while( i < 10){
            final String keys = "myKey";
            final String value = LocalDateTime.now().toString();
            System.out.format("publishing a record with value %s at time %s \n", keys, value);
            final Callback callback = (metadata, exception) -> {
                System.out.format("Published with metadata: %s  and error: %s \n", metadata, exception);
            };
            Stock basic;
            if(random.nextBoolean()) {
                 basic = new EquityStock("equity stonk", random.nextDouble() + 100, LocalDateTime.now().toString());
            }else{
                basic = new BondStock("bond stonk", random.nextDouble() + 100, LocalDateTime.now().toString());
            }
            producer.send(new ProducerRecord<>(topicName, keys, basic), callback);
            i++;
            Thread.sleep(random.nextInt(1000) + 1000);
        }} catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    void shutdown(){
        producer.close();
    }
}
