package com.officialshivam.HomeLab.WorkerManagement.services.kafka;

import com.officialshivam.HomeLab.WorkerManagement.logger.ApplicationLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;


import java.util.concurrent.CompletableFuture;

@Service
public class KafkaService {


    @Value("${kafka.topic.one}")
    private String topic1;

    @Value("${kafka.topic.two}")
    private String topic2;


    @Autowired
    private ApplicationLogger logger;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(String message, String topic) {
        CompletableFuture<SendResult<String, Object>> future = (CompletableFuture<SendResult<String, Object>>) kafkaTemplate.send(topic, message);
        future.whenComplete((result, ex) -> {
            if(ex == null){
                logger.logInfo("Sent Message=["+message+"] with offset =["+result.getRecordMetadata().offset() + "]");
            }
            else {
                logger.logInfo("Unable to send message=["+message+"] due to : "+ex.getMessage());
            }
        });
    }
    public void sendMessage(String message) {
        sendMessage(message, topic1);

    }
}