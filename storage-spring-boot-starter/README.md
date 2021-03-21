# 文件存储

## 安装依赖

```xml
    <dependency>
      <groupId>org.shield</groupId>
      <artifactId>storage-spring-boot-starter</artifactId>
    </dependency>
```

## 启用

使用 @EnableStorage 开启API服务

```java
@EnableStorage
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

```

## 本地文件存储方式

```yaml
oss:
  # 使用本地存储
  driver: local
  # 访问地址，对应到 bucketName
  endpoint: https://domain.com/files
  # 本地 bucket 所在目录
  region: /var/www/html/data
  # 上文创建的AK, 一定注意复制完整不要有空格
  access-key: null
  # 上文创建的SK, 一定注意复制完整不要有空格
  secret-key: null
   # 上文创建的桶名称
  bucketName: bucketName
```

## OSS文件存储方式

```yaml
oss:
  # 使用OSS存储
  driver: oss
  #使用云OSS  需要关闭
  path-style-access: false
  #对应上图 ③ 处配置
  endpoint: oss-cn-hangzhou.aliyuncs.com
  # 上文创建的AK, 一定注意复制完整不要有空格
  access-key: access-key
  # 上文创建的SK, 一定注意复制完整不要有空格
  secret-key: secret-key
   # 上文创建的桶名称
  bucketName: bucketName
```
