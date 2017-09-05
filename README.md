# enterpriseAssetManagement
企业固定资产管理系统  
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/10b70ef4eea14dad9280115ec3b5d44d)](https://www.codacy.com/app/116749895/enterpriseAssetManagement?utm_source=github.com&utm_medium=referral&utm_content=JiangTJ/enterpriseAssetManagement&utm_campaign=badger)
[![build status](https://gitlab.com/JiangTJ/enterpriseAssetManagement/badges/master/build.svg)](https://gitlab.com/JiangTJ/enterpriseAssetManagement/commits/master)

> 示例：[企业固定资产管理系统](http://www.kurome.xin:8080/)  
超级管理员 用户名:admin 密码:123456  
在用户管理中所有的用户密码与超管一致  
大部分模块都未完善，在逐步调整ヾ(･ω･`｡)


### 环境
- JDK环境：jdk 8+
- 数据库：mysql 5.7.14
- 服务器：tomcat
- 系统：window/linux
- 运行环境：支持chrome、edge、foxfire、safari最新版,**不支持ie**

### 框架  

#### 后台：
- spring boot
- spring cloud
- mybatis
- apache shiro  

#### 前端：
- spring thymeleaf
- vue.js
- jQuery  

#### 其他：
- swagger2  

### java 运行

1. 下载源码，maven编译获取jar包（暂不提供下载）
1. 配置数据库，请仿照`application-dev.properties`，创建相应的配置文件`application.properties`，并与war包放在相同目录或者config/下
1. java 运行 `java -jar asset.jar --server.port=8080`,其中`server.port`设置端口号，该配置可写于`application.properties`中

### 注意
- 需java运行环境
- 需数据库导入other/asset.sql
- 默认user-id 为 1 的用户拥有最大权限，必要时修改数据库中用户id
- 后台运行方式，linux 使用`nohup`，windows 使用`javaw`代替`java`
- 若要支持ie，需修改所有js中es6语法为es5，理论上支持ie9+

### docker 运行

- docker命令行输入以下代码
```
docker  run -p 80:8080 \
   -e 'SPRING_DATASOURCE_URL=jdbc:mysql://192.169.2.2:3306/asset?useUnicode=true&characterEncoding=utf-8&useSSL=false' \
   -e 'SPRING_DATASOURCE_USERNAME=root' \
   -e 'SPRING_DATASOURCE_PASSWORD=pw' \
   kurome/asset-run  
```  
- 在网页端，输入docker虚拟环境ip地址即可  



