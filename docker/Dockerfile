FROM mysql:5.7.18


#更新apt-get源 使用163的源
RUN mv /etc/apt/sources.list /etc/apt/sources.list.bak && \
    echo "deb http://mirrors.163.com/debian/ jessie main non-free contrib" >/etc/apt/sources.list && \
    echo "deb http://mirrors.163.com/debian/ jessie-proposed-updates main non-free contrib" >>/etc/apt/sources.list && \
    echo "deb-src http://mirrors.163.com/debian/ jessie main non-free contrib" >>/etc/apt/sources.list && \
    echo "deb-src http://mirrors.163.com/debian/ jessie-proposed-updates main non-free contrib" >>/etc/apt/sources.list
#RUN sed -i s@/archive.ubuntu.com/@/mirrors.163.com/@g /etc/apt/sources.list
#RUN apt-get clean


RUN apt-get update && apt-get install -y --no-install-recommends \
		ca-certificates \
		curl \
		wget \
		bzr \
		git \
		mercurial \
		openssh-client \
		subversion \
		procps \
		bzip2 \
		unzip \
		xz-utils \
	&& rm -rf /var/lib/apt/lists/*

# Default to UTF-8 file.encoding
ENV LANG C.UTF-8

ENV VERSION 8u131
ENV BUILD 11

# 下载解压
RUN mkdir /usr/lib/jvm \
    && wget --no-cookies --no-check-certificate --header "Cookie: oraclelicense=accept-securebackup-cookie;" \
    http://download.oracle.com/otn-pub/java/jdk/"${VERSION}"-b"${BUILD}"/d54c1d3a095b4ff2b6607d096fa80163/jdk-"${VERSION}"-linux-x64.tar.gz \
#    && wget --no-cookies --no-check-certificate --header "Cookie: oraclelicense=accept-securebackup-cookie;" \
#    http://download.oracle.com/otn-pub/java/jdk/"${VERSION}"-b"${BUILD}"/d54c1d3a095b4ff2b6607d096fa80163/jdk-"${VERSION}"-linux-x64.tar.gz?AuthParam=1496210338_c02c9aa5685697f5f0bd72c5a28e658f \
    && tar -zxf jdk-"${VERSION}"-linux-x64.tar.gz -C /usr/lib/jvm

ENV JAVA_HOME /usr/lib/jvm/jdk1.8.0_131
ENV PATH=${PATH}:"${JAVA_HOME}"/bin

# dos2unix
RUN set -x \ && apt-get update && apt-get install -y --no-install-recommends dos2unix \
    && rm -rf /var/lib/apt/lists/*
RUN dos2unix /usr/local/bin/docker-entrypoint.sh

COPY docker-entrypoint.sh /usr/local/bin/
RUN dos2unix /usr/local/bin/docker-entrypoint.sh

# mine
# COPY db-init.sql /docker-entrypoint-initdb.d/
# COPY asset-data.sql /docker-entrypoint-initdb.d/
RUN mkdir /javaweb
COPY asset.war /javaweb/
COPY application.properties /javaweb/

COPY start.sh /javaweb/
RUN chmod 777 /javaweb/start.sh
RUN dos2unix /javaweb/start.sh

EXPOSE 3306 8080
#CMD ["sh","/javaweb/start.sh"]
#CMD ["/bin/bash"]
ENTRYPOINT ["sh","/javaweb/start.sh"]