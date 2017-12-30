package com.jtj.web.entity;

import com.jtj.web.common.BaseEntity;
import lombok.*;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/4/17.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Dictionary extends BaseEntity {

    private String table;
    private String column;
    private String key;
    private String value;

}
