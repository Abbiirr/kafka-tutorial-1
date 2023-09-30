package org.example;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumerDemo {
    Logger logger = LoggerFactory.getLogger(ConsumerDemo.class);

    public ConsumerDemo() {

    }

    public void consumeMessage(String groupId) {
        KafkaConsumer<String, String> consumer = getStringStringConsumer(groupId);
        consumer.subscribe(Collections.singleton(CommonConfig.topic));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for(ConsumerRecord<String, String> record: records) {
                logger.info("Consumer received: "+ groupId);
                logger.info("Key: "+record.key()+", Value: "+record.value());
                logger.info("Partition: "+record.partition()+", Offset: "+record.offset());
            }
        }

    }

    public KafkaConsumer<String, String> getStringStringConsumer(String groupId ) {

        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, CommonConfig.bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        return new KafkaConsumer<>(properties);
    }
}
