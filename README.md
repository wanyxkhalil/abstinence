Abstinence
==========

一些包的统一配置。

1. 将所需项目发布到仓库

2. 添加依赖

```groovy	
// implementation('..web..')
// implementation('..jpa..')
// ...
```

3. 配置系统信息

```
server:
  port: 8080
spring:
  application:
    name: demo
  datasource:
    url: jdbc:mysql://...
    username: ...
    password: ...
#wub:
#  logging-response: true
#  jpa:
#    show-sql: true
#  mybatis:
#    scan: com.meihaofenqi.common.wubmybatis.sample.dao
```
consul的配置需位于boostrap.yml中，且处于default配置部分（即各环境配置之上）
```
#wub:
#  consul:
#    group: aaa
#    prefix: aa
#
#---
#spring:
#  profiles: local
```

4. 编写 *domain*, *repository*/*dao*, *web* 包等业务相关包