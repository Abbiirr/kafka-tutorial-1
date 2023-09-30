package org.example;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        String bootstrapServers = "kafka1:9092";
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", bootstrapServers);
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


        System.out.println("Hello world!");
    }
}