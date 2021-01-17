package org.shield.weimob.autoconfigure;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zacksleo@gmail.com
 */
@Configuration
@ComponentScan("org.shield.weimob")
@EnableConfigurationProperties(WeimobOauth2ClientProperties.class)
public class WeimobAutoConfiguration {
}
