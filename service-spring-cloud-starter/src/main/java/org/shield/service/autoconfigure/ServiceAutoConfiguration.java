package org.shield.service.autoconfigure;

import org.shield.service.converter.HeaderDecoderConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zacksleo@gmail.com
 */
@Configuration
public class ServiceAutoConfiguration {

    @Bean
    public HeaderDecoderConverter headerDecoderConverter() {
        return new HeaderDecoderConverter();
    }
}
