package org.shield.mybatis.autoconfigure;

import org.shield.mybatis.plugin.LogicIdInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author zacksleo@gmail.com
 */
@Configuration
@MapperScan("org.shield.mybatis.mappers")
public class MybatisAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public LogicIdInterceptor logicIdInterceptor() {
        return new LogicIdInterceptor();
    }
}
