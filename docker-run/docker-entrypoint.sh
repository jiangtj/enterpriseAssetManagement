#!/bin/bash

nohup java -jar /javaweb/asset.war --server.port=8080 &
echo "21"
#java -jar /javaweb/asset.war

exec "$@"