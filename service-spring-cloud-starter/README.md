# Spring Cloud Starter Service

Spring Cloud 微服务通用依赖

## 使用

在 pom 中加入依赖

```xml
    <!-- Spring Cloud Application Service -->
    <dependency>
      <groupId>org.shield</groupId>
      <artifactId>service-spring-cloud-starter</artifactId>
    </dependency>
```

如果需要排除某个组件，使用 exclusion

```xml
    <!-- Spring Cloud Application Service -->
    <dependency>
      <groupId>org.shield</groupId>
      <artifactId>service-spring-cloud-starter</artifactId>
      <exclusions>
        <exclusion>
          <groupId>org.shield</groupId>
          <artifactId>rest-spring-boot-starter</artifactId>
        </exclusion>
      </exclusions>
    </dependency>
```

## 功能

### 公共依赖

- spring-boot-starter-web Web 服务
- spring-cloud-starter-config 配置中心
- spring-cloud-starter-netflix-hystrix 断路器
- spring-cloud-starter-netflix-eureka-client Eureka 微服务注册
- spring-boot-admin-starter-client SpringAdmin 监控客户端
- rest-spring-boot-starter REST 常用异常及全局异常处理
- swagger-spring-boot-starter swagger 文档生成
- spring-boot-starter-actuator 监控端点

### header 中文自动解码

> 由于限制，header 中无法直接传中文，需要进行 urlencode. 所以正常情况下需要在获取 header 值时需手动解码.

> 通过实现 GenericConverter 接口，对含有 `@RequestHeader` 注解的值进行了自动解码

例如

```java

    public PageInfo<Admin> list(@RequestHeader("auth-name") String authName, PageableQuery form) {
        System.out.printf("auth-name=%s\n", authName);
        PageHelper.startPage(form);
        return new PageInfo<>(service.findAll());
    }

```

authName 拿到解码将是自动解码的值

### 调试

```yaml
logging:
 level:
   root: debug

management:
  endpoints:
    web:
      exposure:
        include: "*,trace"
```
