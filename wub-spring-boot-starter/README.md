Web统一配置
==========



## 包含功能

### 错误处理

1. 自定义异常*WebException.build(...)*处理。code为9001。

2. 入参绑定、验证错误

3. 入参读取错误

4. 通用异常处理

### 日志打印

默认打印 *method, path, remoteAddress, headers{request, response}, body{request}, timeTaken*

可在application.yml配置添加response body的打印

```yaml
wub:
  logging-response: true
```

### Jackson配置

1. 允许空对象
2. LocalDateTime格式配置为`yyyy-MM-dd HH:mm:ss`
