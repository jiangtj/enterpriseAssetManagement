package com.jtj.web.dao;

import com.jtj.web.common.ResultDto;
import com.jtj.web.dto.MenuDto;
import com.jtj.web.dto.PermissionDto;
import com.jtj.web.entity.Menu;
import com.jtj.web.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/3/15.
 */
@Mapper
@Component
public interface MenuDao extends BaseDao<Menu,MenuDto>{

    List<Menu> getMenu(MenuDto dto);
}
