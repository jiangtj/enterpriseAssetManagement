package com.jtj.web.entity;

import com.jtj.web.common.BaseEntity;

import java.util.List;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2016/12/23 23:26 End.
 */
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getPointId() {
        return pointId;
    }

    public void setPointId(Long pointId) {
        this.pointId = pointId;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public List<Point> getQueryPoints() {
        return queryPoints;
    }

    public void setQueryPoints(List<Point> queryPoints) {
        this.queryPoints = queryPoints;
    }

    public List<Point> getEditPoints() {
        return editPoints;
    }

    public void setEditPoints(List<Point> editPoints) {
        this.editPoints = editPoints;
    }
}
