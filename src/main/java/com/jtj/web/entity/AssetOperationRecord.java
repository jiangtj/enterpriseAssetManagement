package com.jtj.web.entity;

import com.jtj.web.common.BaseEntity;
import lombok.*;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/2/22.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AssetOperationRecord extends BaseEntity {

    private String uuid;
    private Long userId;
    private Integer operationType;
    private String operationTypeName;
    private String remark;

}
