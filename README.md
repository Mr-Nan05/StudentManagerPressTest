# README

### 数据库配置

数据库类型：本地数据库mysql

配置信息如下：

```
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/db_example
spring.datasource.username=root
spring.datasource.password=19990505
```

### 系统操作简介

添加学生信息：可使用postman向localhost:8080/manager/add发送post请求，参数以请求体form-data形式携带

更新学生信息：路由：manager/update，参数同add操作

查询学生信息：除使用id查询外，在service中添加使用name查询方法

删除学生信息：/delete，使用学生学号（id）删除

另：manager/students可展示全部学生信息