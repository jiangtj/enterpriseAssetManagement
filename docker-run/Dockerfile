FROM java:8-jdk

ENV SPRING_DATASOURCE_URL jdbc:mysql://localhost:3306/asset?useUnicode=true&characterEncoding=utf-8&useSSL=false
ENV SPRING_DATASOURCE_USERNAME root
# ENV SPRING_DATASOURCE_PASSWORD

#更新apt-get源 使用163的源
RUN mv /etc/apt/sources.list /etc/apt/sources.list.bak && \
    echo "deb http://mirrors.163.com/debian/ jessie main non-free contrib" >/etc/apt/sources.list && \
    echo "deb http://mirrors.163.com/debian/ jessie-proposed-updates main non-free contrib" >>/etc/apt/sources.list && \
    echo "deb-src http://mirrors.163.com/debian/ jessie main non-free contrib" >>/etc/apt/sources.list && \
    echo "deb-src http://mirrors.163.com/debian/ jessie-proposed-updates main non-free contrib" >>/etc/apt/sources.list

# mine
RUN mkdir /javaweb
COPY asset.war /javaweb/

EXPOSE 8080

CMD ["java","-jar","/javaweb/asset.war",
    "--spring.datasource.url='$SPRING_DATASOURCE_URL'"，
    "--spring.datasource.username='$SPRING_DATASOURCE_USERNAME'"，
    "--spring.datasource.password='$SPRING_DATASOURCE_PASSWORD'"]
#CMD ["/bin/bash"]