package com.soul.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Administrator on 2019/4/2.
 */
@Component
public class KafkaConsumer {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(topics = "test_topic")
    public void listen(ConsumerRecord<?,?> record){
        Optional<?> value = Optional.ofNullable(record.value());
        logger.info("record="+value);
        if(value.isPresent()){
            System.out.println(record.value());
        }


    }
}
