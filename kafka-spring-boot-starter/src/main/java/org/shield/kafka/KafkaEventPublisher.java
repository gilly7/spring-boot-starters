package org.shield.kafka;

import org.shield.mq.EventPublisher;
import org.shield.mq.MqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * kafka 事件发布实现
 *
 * @author zacksleo <zacksleo@gmail.com>
 */
@Service("kafkaEventPublisher")
public class KafkaEventPublisher implements EventPublisher {

    @Autowired
    @Qualifier("kafkaProducer")
    MqProducer mqProducer;

    @Value("${spring.kafka.template.default-topic}")
    private String defaultTopic;

    @Override
    public void publish(Object event) {
        mqProducer.send(defaultTopic, event);
    }

    @Override
    public void publish(String topic, Object event) {
        mqProducer.send(topic, event);
    }
}
