package com.jtj.web.dao;

import com.jtj.web.dto.MenuDto;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    List<KeyValue> getMapById(@Param("pid") Long pid);

    Menu getById(@Param("id") Long id);
}
