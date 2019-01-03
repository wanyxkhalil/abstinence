Mybatis统一配置
==========

## 包含功能

### 统一默认配置

1. 执行器类型为reuse
2. 映射策略为下划线-驼峰
3. 允许空值

### 分页工具类*PageHelperUtils*

可通过`startPage`方法设置分页

1. 和查询方法同时使用（推荐）

   ```
   Page<DebitOrder> page = PageHelperUtils.startPage(pageReq, () -> debitOrderDAO.findAll());
   ```

2. 单独使用，自动将分页注入后面执行的第一条SQL语句

   ```
   PageHelperUtils.startPage(pageReq);
   List<DebitOrder> list = debitOrderDAO.findAll();
   ```


### 分页返回数据基础类*PageRespMybatisData*

继承于分页返回基类*PageRespData*，可使用`buildPageInfo`方法设置返回类的分页信息。

1. 当查询返回值为`Page<T>`时

   ```
   data.buildPageInfo(page);
   // page.getResult() 可获取结果List。建议直接stream
   page.stream()...
   ```

2. 当查询返回值为`List<T>`时

   ```
   data.buildPageInfo(list);
   list.stream()...
   ```

   返回类*data* 继承*PageRespMybatisData*

### dao包扫描配置

默认扫描`com.meihao`包，但建议工程按如下方式在application.yml配置

```yaml
wub:
  mybatis:
    scan: io.khalil.sample.dao
```

