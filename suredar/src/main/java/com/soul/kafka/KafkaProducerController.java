package com.soul.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2019/4/2.
 */
@RestController
@RequestMapping("kafka")
public class KafkaProducerController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping(value = "send")
    public void send(String msg) {
        try {
            logger.info("kafka消息:"+msg);
            kafkaTemplate.send("test_topic",msg);
            logger.info("发送kafka成功");
        }catch (Exception e){
            logger.error("发送kafka失败",e);

        }


    }
}
