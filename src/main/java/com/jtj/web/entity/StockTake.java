package com.jtj.web.entity;

import com.jtj.web.common.BaseEntity;
import lombok.*;

import java.util.Date;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/5/4.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StockTake extends BaseEntity {

    private String name;
    private Long userId;
    private Date endTime;
    private Integer allAmount;
    private Integer handlingAmount;
    private Integer normalAmount;
    private Integer abnormalAmount;
    private Integer status;

}
