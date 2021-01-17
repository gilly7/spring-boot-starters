package org.shield.swagger.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zacksleo@gmail.com
 */
@ConfigurationProperties(prefix = "springfox.documentation")
public class SwaggerProperties {
    /**
     * 标题
     */
    String title = "";
    /**
     * 描述
     */
    String description = "接口文档";
    /**
     * 版本号
     */
    String version = "";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
