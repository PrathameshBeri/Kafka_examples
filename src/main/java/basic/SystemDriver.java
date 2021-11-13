package basic;

import com.google.common.io.Files;
import entities.Stock;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.sparkproject.guava.base.Charsets;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SystemDriver {
    public static void main(String args[]) throws InterruptedException, IOException {

        ExecutorService es = Executors.newFixedThreadPool(4);

        BasicKafkaProducer kProducer = new BasicKafkaProducer();
        /*for(int i = 0; i < 5; i++) {
            es.submit(kProducer);
        }


        Thread.sleep(5000);
        es.shutdown();
        kProducer.shutDown();*/

        es.submit(kProducer);

        BasicKafkaConsumer kConsumer = new BasicKafkaConsumer();
        while(kConsumer.recs.size() < 5) {
            System.out.println("submitting new consumer thread");
            es.submit(kConsumer);
            Thread.sleep(1500);
        }

        File f =  new File("./test1.json");
        StringBuffer buff = new StringBuffer();
        for(ConsumerRecord o : kConsumer.recs){
            System.out.println(o.toString());
            Stock s = (Stock)o.value();
            buff.append(s.toCSVString());
            buff.append("\n");
        }
        Files.write(buff.toString().getBytes(Charsets.UTF_8), f);

        es.awaitTermination(2, TimeUnit.MINUTES);
        es.shutdown();

    }
}
