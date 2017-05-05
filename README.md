# enterpriseAssetManagement
企业固定资产管理系统--毕业设计

> 示例：[企业固定资产管理系统](http://www.kurome.xin:8080/)  
用户：用户名密都是1

> 需要弄毕设的原因，将大致的功能实现了，但各方面都不完善，但也算预览版了吧

### 环境
- JDK环境：jdk 8+
- 数据库：mysql 5.7.14
- 服务器：tomcat
- 系统：window/linux
- 运行环境：支持chrome、edge、foxfire、safari最新版,**不支持ie**

### 框架
- 后台：spring boot : (ssm) spring + spring mvc + mybatis
- 前端：spring thymeleaf + vue.js

### 运行

1. 下载war包[asset.war](https://github.com/JiangTJ/enterpriseAssetManagement/tree/master/other/asset.war)  
1. java 运行 `java -jar asset.war --server.port=8080`,其中`server.port`设置端口号
1. 如果需要配置数据库，请仿照`application-dev.properties`，创建相应的配置文件`application-yourdb.properties`，并与war包放在相同目录或者config/下，`java -jar asset.war --spring.profiles.active=yourdb`来生效数据库配置

### LICENSE
```
Copyright Mr.J

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

