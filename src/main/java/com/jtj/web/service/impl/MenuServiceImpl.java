package com.jtj.web.service.impl;

import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dao.MenuDao;
import com.jtj.web.dao.PermissionDao;
import com.jtj.web.dto.MenuDto;
import com.jtj.web.dto.PermissionDto;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.Menu;
import com.jtj.web.entity.Permission;
import com.jtj.web.service.MenuService;
import com.jtj.web.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public ResultDto<Object> add(Menu t) {
        if (t.getPid() == null) {
            t.setPid(0L);
            t.setLevel(1);
        }else {
            Menu menu = menuDao.getById(t.getPid());
            t.setLevel(menu.getLevel() + 1);
        }
        return super.add(t);
    }

    @Override
    public ResultDto<List<Menu>> getMenu(MenuDto dto) {
        ResultDto<List<Menu>> result = new ResultDto<>(ResultCode.SUCCESS);
        result.setObject( menuDao.getMenu(dto));
        return result;
    }

    @Override
    public ResultDto<List<KeyValue>> getMapById(Long pid) {
        ResultDto<List<KeyValue>> result = new ResultDto<>(ResultCode.SUCCESS);
        result.setObject( menuDao.getMapById(pid));
        return result;
    }
}
