使用示例
==========

1.添加依赖

```groovy
compileOnly('org.projectlombok:lombok')
	
// implementation('..wub..')
// implementation('..jpa..')
// implementation('..consul..')
// implementation('..mybatis..')
runtimeOnly('mysql:mysql-connector-java')	
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

3.编写 *domain*, *repository*/*dao*, *web* 包即可
