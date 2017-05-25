FROM java:8-jdk

ENV SPRING_DATASOURCE_URL jdbc:mysql://localhost:3306/asset?useUnicode=true&characterEncoding=utf-8&useSSL=false
ENV SPRING_DATASOURCE_USERNAME root
ENV SPRING_DATASOURCE_PASSWORD null

#更新apt-get源 使用163的源
RUN mv /etc/apt/sources.list /etc/apt/sources.list.bak && \
    echo "deb http://mirrors.163.com/debian/ jessie main non-free contrib" >/etc/apt/sources.list && \
    echo "deb http://mirrors.163.com/debian/ jessie-proposed-updates main non-free contrib" >>/etc/apt/sources.list && \
    echo "deb-src http://mirrors.163.com/debian/ jessie main non-free contrib" >>/etc/apt/sources.list && \
    echo "deb-src http://mirrors.163.com/debian/ jessie-proposed-updates main non-free contrib" >>/etc/apt/sources.list


COPY docker-entrypoint.sh /usr/local/bin/

RUN set -x \ && apt-get update && apt-get install -y --no-install-recommends dos2unix \
    && rm -rf /var/lib/apt/lists/*
RUN dos2unix /usr/local/bin/docker-entrypoint.sh

RUN ln -s usr/local/bin/docker-entrypoint.sh /entrypoint.sh # backwards compat

# mine
RUN mkdir /javaweb
COPY asset.war /javaweb/
COPY application.properties /javaweb/
RUN ls -a
RUN ls -a /javaweb

ENTRYPOINT ["docker-entrypoint.sh"]

EXPOSE 8080
CMD ["/bin/bash"]