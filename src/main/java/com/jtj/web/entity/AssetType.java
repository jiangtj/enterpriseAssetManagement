package com.jtj.web.entity;

import com.jtj.web.service.base.TreeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/4/29 19:25 End.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AssetType extends TreeEntity<AssetType> {

    private String name;
    private Integer order;

}
