package com.jtj.web.entity;

import com.jtj.web.common.BaseEntity;
import lombok.*;

import java.math.BigDecimal;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/2/22.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StockTakeItem extends BaseEntity {

    private Long stockTakeId;
    private String uuid;
    private String customsId;
    private String name;
    private BigDecimal price;
    private Integer status;
    private String statusName;
    private Integer assetsTypeId;
    private Long pointId;

}
