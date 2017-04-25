package com.jtj.web.service;

import com.jtj.web.common.PageDto;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dto.MenuDto;
import com.jtj.web.dto.PermissionDto;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.Menu;
import com.jtj.web.entity.Permission;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2016/12/23 23:26 End.
 */
@Service
public interface MenuService extends BaseService<Menu,MenuDto> {

    ResultDto<List<Menu>> getMenu(MenuDto dto);

    ResultDto<List<KeyValue>> getMapById(Long pid);
}
