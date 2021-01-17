package org.shield.kafka.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zacksleo <zacksleo@gmail.com>
 */
@Configuration
@ComponentScan("org.shield.kafka")
@ConditionalOnProperty(name = "spring.kafka")
public class KafkaAutoConfiguration {
}
