/**
 * Created by jiang (jiang.taojie@foxmail.com) 
 * 2017/10/8 19:18 End.
 */
module asset {

    requires spring.boot;
    requires spring.context;
    requires spring.beans;
    requires spring.web;
    requires spring.core;
    requires spring.webmvc;
    requires spring.tx;

    requires jackson.databind;
    requires jackson.annotations;

    requires springfox.swagger2;
    requires springfox.spring.web;
    requires springfox.spi;
    requires springfox.core;
    requires swagger.annotations;

    requires shiro.spring;
    requires shiro.core;
    requires shiro.web;

    requires mybatis;
    requires thymeleaf;
    requires commons.collections;
    requires aspectjweaver;
    requires slf4j.api;
    requires tomcat.embed.core;

}