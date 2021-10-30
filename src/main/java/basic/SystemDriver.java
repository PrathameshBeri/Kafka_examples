package basic;

import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SystemDriver {
    public static void main(String args[]) throws InterruptedException {

        ExecutorService es = Executors.newFixedThreadPool(4);

        BasicKafkaProducer kProducer = new BasicKafkaProducer();
        for(int i = 0; i < 5; i++) {
            es.submit(kProducer);
        }

        Thread.sleep(5000);
        es.shutdown();
        kProducer.shutDown();

    }
}
