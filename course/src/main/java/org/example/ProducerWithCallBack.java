package org.example;

import org.slf4j.LoggerFactory;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;

import java.util.Properties;

public class ProducerWithCallBack {
    Logger logger = LoggerFactory.getLogger(ProducerWithCallBack.class);
    public ProducerWithCallBack() {

    }

    public void sendMessage(int number) {
        KafkaProducer<String, String> producer = getStringStringKafkaProducer();

        String topic = "first_topic";

        for(int i=0;i<number;i++) {
            String message = "hello world "+(i+1);
            String key = "id_"+((i)%3);

            ProducerRecord<String, String> record = new ProducerRecord<>(topic, key,message);

//            logger.info("Key: "+key);

            producer.send(record, (recordMetadata, e) -> {
                // executes every time a record is successfully sent or an exception is thrown
                if (e == null) {
                    // the record was successfully sent
                    logger.info("Received new metadata. \n" +
                            "Topic: " + recordMetadata.topic() + "\n" +
                            "Key: " + key + "\n" +
                            "Partition: " + recordMetadata.partition() + "\n" +
                            "Offset: " + recordMetadata.offset() + "\n" +
                            "Timestamp: " + recordMetadata.timestamp());
                } else {

                    logger.error("Error while producing", e);
//                    e.printStackTrace();
                }
            });
        }

        producer.flush();
        producer.close();
    }

    private static KafkaProducer<String, String> getStringStringKafkaProducer() {
        String bootstrapServers = "localhost:9092";
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//        properties.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG, RoundRobinPartitioner.class.getName());

        return new KafkaProducer<>(properties);
    }

}
