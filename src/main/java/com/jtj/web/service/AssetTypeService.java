package com.jtj.web.service;

import com.jtj.web.common.ResultDto;
import com.jtj.web.common.exception.AssetException;
import com.jtj.web.dao.AssetTypeDao;
import com.jtj.web.dto.AssetTypeDto;
import com.jtj.web.entity.AssetType;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.service.base.CurdService;
import com.jtj.web.service.base.TreeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2016/12/23 23:26 End.
 */
@Service
public interface AssetTypeService extends CurdService<AssetType,AssetTypeDto,AssetTypeDao>, TreeService<AssetType> {

    ResultDto<List<AssetType>> getType(AssetTypeDto dto);

    ResultDto<List<KeyValue>> getMapByPid(Long pid);

    ResultDto<Object> deleteById(Long id) throws AssetException;
}
