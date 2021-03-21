package org.shield.storage.autoconfigure;

import org.shield.storage.service.FileService;
import org.shield.storage.service.impl.LocalFileServiceImpl;
import org.shield.storage.service.impl.OssFileServiceImpl;
import org.shield.storage.annotation.EnableStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.pig4cloud.plugin.oss.OssProperties;
import com.pig4cloud.plugin.oss.service.OssTemplate;

/**
 * @author zacksleo@gmail.com
 */
@Configuration
@ConditionalOnBean(annotation = EnableStorage.class)
@ComponentScan({"org.shield.storage.controller"})
public class StorageAutoConfiguration {

    @Autowired
    private OssTemplate template;

    @Autowired
    private OssProperties ossProperties;

    @ConditionalOnProperty(name = "oss.driver", havingValue = "local")
    @Bean
    public FileService jobA() {
        return new LocalFileServiceImpl();
    }

    @ConditionalOnProperty(name = "oss.driver", havingValue = "oss", matchIfMissing = true)
    @Bean
    public FileService jobB() {
        return new OssFileServiceImpl(template, ossProperties);
    }
}
