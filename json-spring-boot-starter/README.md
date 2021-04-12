# json-spring-boot-starter

## 信息脱敏

使用注解, 支持的类型：姓名，手机，邮箱，身份证, 生日, 密码

```java
@Sensitive(value=SensitiveType.CHINESE_NAME)
private String phone;
```
