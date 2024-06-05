package com.example.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);

    /*
    @KafkaListener(topics = "${spring.kafka.topic-json.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(User user){
        LOGGER.info(String.format("Json message recieved -> %s", user.toString()));
    }
    */
    
    /**
     * Acknowledgment ack -> only work once you have configured the Ack mode as Manual (spring.kafka.listener.ack-mode: MANUAL_IMMEDIATE) 
     * @param payload
     * @param offset
     * @param topic
     * @param partitionId
     * @param ack
     */
    
    @KafkaListener(topics = "${spring.kafka.topic-json.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(
    		@Payload String payload,
    		@Header(KafkaHeaders.OFFSET) String offset,
    		@Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
    		@Header(KafkaHeaders.RECEIVED_PARTITION_ID) String partitionId,
    		Acknowledgment ack
    		){
        LOGGER.info(String.format("Json message recieved -> %s", payload.toString()));
        ack.acknowledge();
    }
}
