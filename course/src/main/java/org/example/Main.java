package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        ProducerDemo producerDemo = new ProducerDemo();
        ProducerWithCallBack producerWithCallBack = new ProducerWithCallBack();
        producerWithCallBack.sendMessage(100);
//        ConsumerDemo consumerDemo = new ConsumerDemo();
//        consumerDemo.consumeMessage("my-fourth-application");
        ConsumerAssignSeek consumerAssignSeek = new ConsumerAssignSeek();
        consumerAssignSeek.consumeMessage();
//        System.out.println("Hello World");
    }
}