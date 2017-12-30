package com.jtj.web.entity;

import com.jtj.web.common.BaseEntity;
import lombok.*;

import java.util.List;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/1/31 22:31 End.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {

    private String name;//角色名
    private Integer status;//状态，1：启用，2：不启用
    private List<Permission> permissions;

}
