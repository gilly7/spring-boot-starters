package org.shield.storage.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zacksleo@gmail.com
 */
public class FileVo {

    @ApiModelProperty(value = "返回Key值", example = "4611fc829c774f158fa54fa736455e54.png")
    private String key;

    @ApiModelProperty(value = "文件名称", example = "logo.30c81c88.png")
    private String name;

    @ApiModelProperty(value = "文件访问URL", example = "https://regaion.oss.com/test/4611fc829c774f158fa54fa736455e54.png")
    private String url;

    @ApiModelProperty(value = "Hash值", example = "30C81C8874D188088ABF83441CE14A46")
    private String hash;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
