package basic;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.LocalDateTime;
import java.util.Map;

public class BasicKafkaProducer implements Runnable {

    final String topicName = "getting_started";

    final Map<String, Object> config = Map.of(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092",
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName(),
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName(),
            ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true

    );

    KafkaProducer producer;

    BasicKafkaProducer(){
        producer = new KafkaProducer<String, String>(config);
    }

    @Override
    public void run() {
        int i = 0;
        try{

            final String keys = "myKey";
            final String value = LocalDateTime.now().toString();
            System.out.format("publishing a record with value %s at time %s \n", keys, value);
            final Callback callback = (metadata, exception) -> {
                System.out.format("Published with metadata: %s  and error: %s \n", metadata, exception);
            };

            producer.send(new ProducerRecord<>(topicName, keys, value), callback);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    void shutDown(){
        producer.close();
    }
}
