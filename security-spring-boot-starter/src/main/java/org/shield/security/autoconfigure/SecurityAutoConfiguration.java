package org.shield.security.autoconfigure;

import org.shield.security.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zacksleo@gmail.com
 */
@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class SecurityAutoConfiguration {

    @Autowired
    private JwtProperties jwtProps;

    @Bean
    @ConditionalOnMissingBean
    public JwtTokenUtils jwtTokenUtils() {
        return new JwtTokenUtils(jwtProps.getBase64Secret(), jwtProps.getTokenValidityInSeconds());
    }
}
