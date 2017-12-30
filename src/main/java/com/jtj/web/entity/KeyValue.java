package com.jtj.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/3/15.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeyValue {

    private String key;
    private Object value;

}
