Consul Spring Cloud
==========

## 使用方式

```groovy
compile '...consul'
```

## 包含功能

### Config默认配置

1. 修改远程配置文件名分隔符为 `::`
2. 修改远程配置文件类型为 *yaml*
3. 修改默认目录为系统名
4. 修改服务实例ID为 *{系统名}:{环境}*

### Discovery默认配置

1. 修改健康检查间隔为20s
2. 修改默认使用IP而非机器名
3. 修改标签为 `group={group}, urlprefix-/{prefix}` 。*group* 默认为 *meihao*，可通过`web.consul.group`配置。*prefix* 默认为系统名，可通过`web.consul.prefix`配置

### 健康检查接口

默认已添加健康检查接口

ps: 以下为需配置数据
```yaml
server:
  port: 8080
spring:
  application:
    name: demo
  profiles:
    active: local
#wub:
#  consul:
#    group: cash
#    prefix: aa

---
spring:
  profiles: local
  cloud:
    consul:
      enabled: false

---
spring:
  profiles: dev
  cloud:
    consul:
      host: 
      port: 
      config:
        token: 

---
# ...
```