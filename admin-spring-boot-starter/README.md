# 系统管理通用模块

## 功能

1. 系统账号管理
2. 基于RBAC的权限角色管理

## 使用

```java
@SpringBootApplication
@EnableAdminConsole
public class Application {

```

配置 mybatis

```yaml
mybatis:
  mapper-locations:
    - classpath:mapper/*Mapper.xml
    - classpath*:/mapper/*.xml
  configuration:
    map-underscore-to-camel-case: true

```
