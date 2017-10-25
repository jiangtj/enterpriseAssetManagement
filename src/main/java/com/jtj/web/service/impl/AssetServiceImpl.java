package com.jtj.web.service.impl;

import com.jtj.web.common.Constant;
import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dao.AssetDao;
import com.jtj.web.dao.BorrowDao;
import com.jtj.web.entity.Asset;
import com.jtj.web.service.AssetOperationRecordService;
import com.jtj.web.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/3/15.
 */
@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetDao assetDao;
    @Autowired
    private AssetOperationRecordService assetOperationRecordService;
    @Autowired
    private BorrowDao borrowDao;

    @Override
    public AssetDao getRepository() {
        return assetDao;
    }

    @Override
    public ResultDto<Object> add(Asset t) {
        ResultDto<Object> result = new ResultDto<>();
        String uuid = UUID.randomUUID().toString();
        t.setUuid(uuid);
        assetDao.add(t);
        result.setResultCode(ResultCode.SUCCESS_POST);
        assetOperationRecordService.addOperationRecord(uuid, Constant.OperationType.ADD,result.getTitle());
        return result;
    }

    @Override
    public ResultDto<Object> updateStatus(String uuid, Integer status, String remark) {
        ResultDto<Object> result = new ResultDto<>();
        result.setResultCode(assetDao.updateAssetStatus(uuid,status) == 1?
                ResultCode.SUCCESS:ResultCode.OPERATE_FAIL);
        if (status == 3) assetOperationRecordService.addOperationRecord(uuid,Constant.OperationType.MAINTENANCE,result.getTitle()+","+remark);
        if (status == 4) assetOperationRecordService.addOperationRecord(uuid,Constant.OperationType.ABANDONED,result.getTitle()+","+remark);
        return result;
    }
}
