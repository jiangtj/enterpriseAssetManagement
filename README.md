# enterpriseAssetManagement
企业固定资产管理系统  
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/10b70ef4eea14dad9280115ec3b5d44d)](https://www.codacy.com/app/116749895/enterpriseAssetManagement?utm_source=github.com&utm_medium=referral&utm_content=JiangTJ/enterpriseAssetManagement&utm_campaign=badger)
[![build status](https://gitlab.com/JiangTJ/enterpriseAssetManagement/badges/master/build.svg)](https://gitlab.com/JiangTJ/enterpriseAssetManagement/commits/master)


### 环境
- JDK环境：jdk 8
- 数据库：mysql 5.7.14
- 服务器：tomcat
- 系统：window/linux
- 运行环境：支持chrome、edge、foxfire、safari最新版,**不支持ie**

### 框架  

#### 后台：
- spring boot(version 2.0)
- mybatis
- apache shiro  

#### 前端：
- spring thymeleaf
- vue.js(version 2.0)
- jQuery(version 2.1)

#### 其他：
- swagger2  

### java 运行

1. 下载源码，maven编译获取jar包或者您可以访问该项目的[**gitlab pipelines**](https://gitlab.com/JiangTJ/enterpriseAssetManagement/pipelines)下载
1. 配置数据库，请仿照`application-example.properties`，创建相应的配置文件`application.properties`，并与war包放在相同目录或者config/下
1. java 运行 `java -jar asset.jar --server.port=8080`,其中`server.port`设置端口号，该配置可写于`application.properties`中

### 注意
- 需java运行环境
- 第一次启动，需添加`--spring.datasource.initialization-mode=always`参数，以初始化数据库（*目前仍处于开发阶段，数据库结构可能存在调整，且不提供版本更新sql，数据库文件放在resources目录下*）
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
   -e 'SPRING_DATASOURCE_INITIALIZATION_MODE=always' \
   kurome/asset-run  
```  
- 在网页端，输入docker虚拟环境ip地址即可  

### 不再维护！！
很遗憾告诉您这个消息，该项目部分功能实现超出我的能力范围（如果要做到好用，
需与打印机等硬件交互）。同时，在项目架构设计时也存在一些缺陷：
1. 前后端未能实现分离  
2. 前后端代码留有许多个人习惯，并且部分地方过度封装，导致他人维护成本较高   

#### 一些琐事
1. 前端：vue的是个非常不错的前端框架，很灵活，很有意思。但相应的由于其太过于灵活，
架构设计很难设计完美，导致后续的维护成本增加。建议重构该项目的前端框架，angular或许是
个不错的选择。
2. 持久层框架：Mybatis与Spring Data Jpa。这两个框架各有各的优势，但对于开源项目
来说，Jpa更适合开源协作（无需关心数据库），而Mybatis更适合企业内部系统使用
（一般而言，企业内部有专门维护数据库的人员）。我后续的开源项目，将更多的采用
Jpa框架。

最后，感谢您的关注与收藏

