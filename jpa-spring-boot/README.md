JPA Spring Boot 
==========

## 使用方式

```groovy
compile '...jpa...'
```

## 包含功能

### 分页基础类*PageRespJpaData*

1. 继承于分页返回基类*PageRespData*
2. 可使用方法`buildPageInfo(Page<T> page)`设置返回类的分页信息

使用示例：

```java

// import ...

@Setter
@Getter
@ToString(callSuper = true)
public class TestRespData extends PageRespJpaData {

    public List<Info> infoList;

    @Data
    @Builder
    public static class Info {
        private Integer id;
        private String userName;
    }
}
```



### 分页排序工具类*WebRequestSortJpaUtils*

使用示例：

```
PageRequest pageRequest = WebRequestSortJpaUtils.getPageInfoAsc(pageReq, "id");
```

### [字段映射策略]()

已配置*SpringPhysicalNamingStrategy*，自动完成对 *类* 和 *表* 的命名映射。

ps: 某些表名特殊, 可用 *@Table* 明确指定。

### 属性配置

1. 可通过`wub.jpa.show-sql=true` 开启sql打印。默认关闭。
2. 已关闭 *open-in-view*
