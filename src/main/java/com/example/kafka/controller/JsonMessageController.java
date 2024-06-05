package com.example.kafka.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafka.model.User;
import com.example.kafka.producer.JsonKafkaProducer;

@RestController
@RequestMapping("/api/v1/kafka")
public class JsonMessageController {

    private JsonKafkaProducer kafkaProducer;

    public JsonMessageController(JsonKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody User user){
    	
    	for(int i=0;i<1000;i++) {
    		User usr = new User();
    		usr.setId(i);
    		usr.setFirstName("First" + i);
    		usr.setLastName("Last" + i);
    		kafkaProducer.sendMessage(usr);
    	}
        return ResponseEntity.ok("Json message sent to kafka topic");
    }
}
