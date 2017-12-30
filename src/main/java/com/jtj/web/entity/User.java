package com.jtj.web.entity;

import com.jtj.web.common.BaseEntity;
import lombok.*;

import java.util.List;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2016/12/23 23:26 End.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    private String name;//用户名
    private String password;//密码
    private String description;//密码
    private Long roleId;//角色id
    private Role role;//角色对象
    private Long pointId;//角色id
    private Point point;//角色对象

    private List<Point> queryPoints;
    private List<Point> editPoints;

}
