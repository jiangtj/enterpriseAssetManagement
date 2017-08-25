package com.jtj.web.service;

import com.jtj.web.common.ResultDto;
import com.jtj.web.dto.AssetTypeDto;
import com.jtj.web.entity.AssetType;
import com.jtj.web.entity.KeyValue;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2016/12/23 23:26 End.
 */
@Service
public interface AssetTypeService extends BaseService<AssetType,AssetTypeDto> {

    ResultDto<List<AssetType>> getType(AssetTypeDto dto);

    ResultDto<List<KeyValue>> getMapByPid(Long pid);
}
