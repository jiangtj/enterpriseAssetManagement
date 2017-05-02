package com.jtj.web.service;

import com.jtj.web.common.Constant;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dto.AssetDto;
import com.jtj.web.entity.Asset;
import com.jtj.web.entity.AssetOperationRecord;
import com.jtj.web.entity.Borrow;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2016/12/23 23:26 End.
 */
@Service
public interface AssetOperationRecordService {

    int addOperationRecord(String uuid,Constant.OperationType type);

    int addOperationRecord(String uuid, Constant.OperationType type, String remark);

    ResultDto<List<AssetOperationRecord>> getOperationRecordByUuid(String uuid);

}
