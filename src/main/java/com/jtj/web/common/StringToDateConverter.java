package com.jtj.web.common;

import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/4/8.
 */
public class StringToDateConverter implements Converter<String,Date> {
    @Override
    public Date convert(String source) {
        return new Date(Long.parseLong(source));
    }
}
