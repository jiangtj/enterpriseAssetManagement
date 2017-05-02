package com.jtj.web.service.impl;

import com.jtj.web.common.Constant;
import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.common.utils.BeanUtils;
import com.jtj.web.dao.AssetDao;
import com.jtj.web.dto.AssetDto;
import com.jtj.web.entity.Asset;
import com.jtj.web.entity.Borrow;
import com.jtj.web.service.AssetOperationRecordService;
import com.jtj.web.service.AssetService;
import com.jtj.web.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private BorrowService borrowService;

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
        assetOperationRecordService.addOperationRecord(uuid, Constant.OperationType.BORROW,result.getTitle());
        borrow.setUuid(uuid);
        return borrowService.add(borrow);
    }

    @Override
    public ResultDto<Object> returnAsset(Borrow borrow) {
        ResultDto<Object> result = new ResultDto<>();
        AssetDto dto = BeanUtils.fromBean(borrow.getAsset(),AssetDto.class);
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
        result.setResultCode(updateAssetStatus(assets.get(0).getUuid(), Constant.AssetStatus.NORMAL) == 1?
                ResultCode.SUCCESS:ResultCode.OPERATE_FAIL);
        assetOperationRecordService.addOperationRecord(assets.get(0).getUuid(), Constant.OperationType.RETURN,result.getTitle());
        //todo 修改状态
        return borrowService.update(borrow);
    }

    private int updateAssetStatus(String uuid, Constant.AssetStatus status){
        return assetDao.updateAssetStatus(uuid,status.getId());
    }
}
