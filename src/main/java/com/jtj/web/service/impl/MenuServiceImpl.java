package com.jtj.web.service.impl;

import com.jtj.web.dao.MenuDao;
import com.jtj.web.dao.PermissionDao;
import com.jtj.web.dto.MenuDto;
import com.jtj.web.dto.PermissionDto;
import com.jtj.web.entity.Menu;
import com.jtj.web.entity.Permission;
import com.jtj.web.service.MenuService;
import com.jtj.web.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/3/15.
 */
@Service
public class MenuServiceImpl
        extends BaseServiceImpl<Menu,MenuDto,MenuDao>
        implements MenuService {

    @Autowired
    private MenuDao menuDao;

}
