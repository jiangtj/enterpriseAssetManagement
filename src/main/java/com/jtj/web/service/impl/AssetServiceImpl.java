package com.jtj.web.service.impl;

import com.jtj.web.common.Constant;
import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.common.utils.BeanUtils;
import com.jtj.web.dao.AssetDao;
import com.jtj.web.dao.BorrowDao;
import com.jtj.web.dto.AssetDto;
import com.jtj.web.entity.Asset;
import com.jtj.web.entity.Borrow;
import com.jtj.web.service.AssetOperationRecordService;
import com.jtj.web.service.AssetService;
import com.jtj.web.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/3/15.
 */
@Service
public class AssetServiceImpl
        extends BaseServiceImpl<Asset,AssetDto,AssetDao>
        implements AssetService {

    @Autowired
    private AssetDao assetDao;
    @Autowired
    private AssetOperationRecordService assetOperationRecordService;
    @Autowired
    private BorrowDao borrowDao;

    @Override
    public ResultDto<Object> add(Asset t) {
        String uuid = UUID.randomUUID().toString();
        t.setUuid(uuid);
        ResultDto<Object> result = super.add(t);
        assetOperationRecordService.addOperationRecord(uuid, Constant.OperationType.ADD,result.getTitle());
        return result;
    }

    @Override
    public ResultDto<Object> borrowAsset(Borrow borrow) {
        ResultDto<Object> result = new ResultDto<>();
        AssetDto dto = BeanUtils.fromBean(borrow.getAsset(),AssetDto.class);
        dto.setStatus(Constant.AssetStatus.NORMAL.getId());
        List<Asset> assets = assetDao.getList(dto);
        if (assets.size() == 0){
            result.setResultCode(ResultCode.ASSET_NON_EXISTENT);
            return result;
        }
        if (assets.size() > 1){
            result.setResultCode(ResultCode.ASSET_NOT_ONLY);
            result.setMessage("请使用uuid或者添加更多条件");
            return result;
        }
        //todo 权限判断
        String uuid = assets.get(0).getUuid();
        result.setResultCode(updateAssetStatus(uuid, Constant.AssetStatus.BORROW) == 1?
                ResultCode.SUCCESS:ResultCode.OPERATE_FAIL);
        assetOperationRecordService.addOperationRecord(uuid, Constant.OperationType.BORROW,
                result.getTitle()+",租借人："+borrow.getUserId());
        borrow.setUuid(uuid);
        borrowDao.add(borrow);
        return result;
    }

    @Override
    public ResultDto<Object> returnAsset(Borrow borrow) {
        ResultDto<Object> result = new ResultDto<>();
        AssetDto dto = BeanUtils.fromBean(borrow.getAsset(),AssetDto.class);
        dto.setStatus(Constant.AssetStatus.BORROW.getId());
        List<Asset> assets = assetDao.getList(dto);
        if (assets.size() == 0){
            result.setResultCode(ResultCode.ASSET_NON_EXISTENT);
            return result;
        }
        if (assets.size() > 1){
            result.setResultCode(ResultCode.ASSET_NOT_ONLY);
            result.setMessage("请使用uuid或者添加更多条件");
            return result;
        }
        String uuid = assets.get(0).getUuid();
        result.setResultCode(updateAssetStatus(uuid, Constant.AssetStatus.NORMAL) == 1?
                ResultCode.SUCCESS:ResultCode.OPERATE_FAIL);
        assetOperationRecordService.addOperationRecord(uuid, Constant.OperationType.RETURN,
                result.getTitle()+",归还人："+borrow.getUserId());
        borrow.setUuid(uuid);
        borrow.setStatus(2);
        if (borrow.getReturnTime() == null) borrow.setReturnTime(new Date());
        borrowDao.updateStatus(borrow);
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

    private int updateAssetStatus(String uuid, Constant.AssetStatus status){
        return assetDao.updateAssetStatus(uuid,status.getId());
    }
}
