使用示例
==========

将所需项目发布到仓库即可使用

1.添加依赖

```groovy	
// implementation('..wub..')
// implementation('..jpa..')
// implementation('..consul..')
// implementation('..mybatis..')
```

2.配置系统信息

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

3.编写 *domain*, *repository*/*dao*, *web* 包等业务相关包即可
