package com.jtj.web.service;

import com.jtj.web.common.PageDto;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dto.AssetDto;
import com.jtj.web.dto.AssetTypeDto;
import com.jtj.web.entity.Asset;
import com.jtj.web.entity.AssetOperationRecord;
import com.jtj.web.entity.AssetType;
import com.jtj.web.entity.KeyValue;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2016/12/23 23:26 End.
 */
@Service
public interface AssetService extends BaseService<Asset,AssetDto> {

    ResultDto<List<AssetOperationRecord>> getOperationRecordByUuid(String uuid);

}
