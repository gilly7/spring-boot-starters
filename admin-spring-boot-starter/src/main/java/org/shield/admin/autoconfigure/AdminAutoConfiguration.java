package org.shield.admin.autoconfigure;

import org.shield.admin.annotation.EnableAdminConsole;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author zacksleo@gmail.com
 */
@Configuration
@ConditionalOnBean(annotation = EnableAdminConsole.class)
@ComponentScan({"org.shield.admin"})
@MapperScan({"org.shield.admin.mapper"})
public class AdminAutoConfiguration {
}
