# Mybatis Spring Boot Starter

## 使用

### 分页查询基类

查询 Form 使用或继承 PageableQuery, 包含的 pageNum, pageSize, 和 sorts 三个参数

在 yaml中进行如下配置

```yaml
# mybatis pagehelper 配置查询页码参数
pagehelper:
  params: pageNum=pageInde&orderBy=sorts
```

分页查询，可以使用如下形式调用, startPage 方法对分页设置以上三个参数

```java
    public PageInfo<Admin> list(PageableQuery form) {
        Page<Admin> page = PageHelper.startPage(form);
        service.findAll();
        return PageInfo.of(page);
    }
```

### LogicId

@LogicId 注解用于自动生成编号，在插入数据表时，会自动生成

```java
@Data
@Table(name = "admin")
public class Admin {

    /**
     * 编号
     */
    @Column(name = "`admin_id`")
    @LogicId(prefix = "ADMI")
    private String adminId;
}
```

## 注意事项

1. 插入的 DAO 实体类初始化时，不要使用双大括号写法，如

```java
        Notification notification = new Notification(){{
            setChannel(channel);
            setTitle(title);
            setContent(content);
        }};
```

应用使用以下形式

```java
        Notification notification = new Notification();
        notification.setChannel(channel);
        notification.setTitle(title);
        notification.setContent(content);
```
