package com.jtj.web.service;

import com.jtj.web.common.PageDto;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dto.PermissionDto;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.Permission;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2016/12/23 23:26 End.
 */
@Service
public interface PermissionService extends BaseService<Permission,PermissionDto> {

    ResultDto<List<KeyValue>> getMap();
}
