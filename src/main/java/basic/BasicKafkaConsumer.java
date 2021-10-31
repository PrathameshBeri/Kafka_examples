package basic;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BasicKafkaConsumer implements Runnable {

    final String topicName = "getting_started_4";

    final Map<String, Object> config = Map.of(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
            "localhost:9092",
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
            StringDeserializer.class.getName(),
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
            StringDeserializer.class.getName(),
            ConsumerConfig.GROUP_ID_CONFIG,
            "basic_consumer_sample",
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
            "earliest",
            ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,
            false
    );

    KafkaConsumer<String, Object> consumer;

    List<Object> recs = new ArrayList<>();

    BasicKafkaConsumer(){
        consumer = new KafkaConsumer<String, Object>(config);
    }

    @Override
    public void run() {
        consumer.subscribe(Set.of(topicName));

       // while(true){
            final var records = consumer.poll(Duration.ofMillis(250));
            for(var record: records){
                System.out.format("get record with  %s and %s, added to the list \n", record.key(), record.value());
                recs.add(record);
            }
            consumer.commitAsync();
       // }
    }


    void shutdown(){
        consumer.close();
    }

}
