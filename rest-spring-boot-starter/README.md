# Spring boot 微服务 Rest 通用库

## Rest 风格的异常

| 类型                                      | 状态码 | 业务码 | 说明               |
| ----------------------------------------- | ------ | ------ | ------------------ |
| BadRequest/ErrorRequestException          | 400    | 400    | 验证错误           |
| ForibiddenException                       | 403    | 403    | 没有权限           |
| NotFoundException/SourceNotFoundException | 404    | 400    | 请求的资源不存在   |
| ServiceException                          | 400    | 400    | 业务处理异常       |
| SourceExistedException                    | 400    | 400    | 资源已存在         |
| UnauthorizedException                     | 401    | 401    | 认证失败           |
| UnknownException                          | 500    | 500    | 发生未知错误       |
| UnprocessableEntityException              | 422    | 422    | 上传的文件无法处理 |
