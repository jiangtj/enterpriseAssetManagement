FROM java:8-jdk

# 配置数据库连接地址
# ENV SPRING_DATASOURCE_URL jdbc:mysql://localhost:3306/asset?useUnicode=true&characterEncoding=utf-8&useSSL=false
# 数据库用户名
# ENV SPRING_DATASOURCE_USERNAME root
# 数据库密码
# ENV SPRING_DATASOURCE_PASSWORD pw

#更新apt-get源 使用163的源
RUN mv /etc/apt/sources.list /etc/apt/sources.list.bak && \
    echo "deb http://mirrors.163.com/debian/ jessie main non-free contrib" >/etc/apt/sources.list && \
    echo "deb http://mirrors.163.com/debian/ jessie-proposed-updates main non-free contrib" >>/etc/apt/sources.list && \
    echo "deb-src http://mirrors.163.com/debian/ jessie main non-free contrib" >>/etc/apt/sources.list && \
    echo "deb-src http://mirrors.163.com/debian/ jessie-proposed-updates main non-free contrib" >>/etc/apt/sources.list

# war
RUN mkdir /javaweb
COPY asset.jar /javaweb/

# RUN mkdir /javaweb/temp
# VOLUME ["/javaweb/temp"]

EXPOSE 8080

ENTRYPOINT ["java","-jar","/javaweb/asset.jar"]