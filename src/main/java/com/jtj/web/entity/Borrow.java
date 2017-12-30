package com.jtj.web.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jtj.web.common.BaseEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/2/22.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Borrow extends BaseEntity {

    private Long userId;
    private String uuid;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expectReturnTime;
    private String remark;
    private Integer status;
    private Date returnTime;

    private Asset asset;

}
