package org.shield.kafka;

import org.shield.mq.MqProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Kafka 消息发布实现
 *
 * @author zacksleo <zacksleo@gmail.com>
 */
@Service("kafkaProducer")
public class KafkaProducer implements MqProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<Object, Object> template;

    @Override
    public void send(String topic, Object message) {
        logger.info(String.format("Producing Kafka message: %s --> %s", message, topic));
        template.send(topic, message);
    }
}
