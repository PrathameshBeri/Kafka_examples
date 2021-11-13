package basic;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.sum;

public class SparkAggregator implements Runnable{


    @Override
    public void run() {
        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SparkSession session = SparkSession.builder().appName("test").master("local").getOrCreate();
        System.out.println("Starting the spark aggregator");
        Dataset<Row> ds = session.read().format("csv")
                            .option("header", true)
                            .option("sep", ",")
                            .option("inferSchema",true)
                            .load("./test3.csv");
        System.out.println("Excerpts of the ddata set");
        ds.show(10,false);
        ds.printSchema();
        Dataset<Row> df = ds.agg(sum("stock_value"));
        df.show(5,false);
    }
}
