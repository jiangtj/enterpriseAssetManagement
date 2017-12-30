package com.jtj.web.entity;

import com.jtj.web.common.BaseEntity;
import lombok.*;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/4/17 21:52 End.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Permission extends BaseEntity {

    private String code;
    private String name;

}
