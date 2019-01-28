通用的网络实体包
==========

web系统中的请求、返回类型的父类

## 请求

- BaseReq 所有请求的父类，当前无属性。可添加统一请求属性。
- PageReq 继承至*BaseReq* ，用于分页的请求父类

## 返回

- BaseResp 所有返回的父类，有统一的返回属性，返回的数据需继承此类中的 *BaseData* 类
- PageRespData 继承至 *BaseData* ，用于分页的返回父类
- ResponseCode 一些返回码

