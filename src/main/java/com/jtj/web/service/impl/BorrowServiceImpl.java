package com.jtj.web.service.impl;

import com.jtj.web.common.Constant;
import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.common.exception.AssetException;
import com.jtj.web.common.utils.BeanUtils;
import com.jtj.web.dao.AssetDao;
import com.jtj.web.dao.BorrowDao;
import com.jtj.web.dao.UserDao;
import com.jtj.web.dto.AssetDto;
import com.jtj.web.entity.Asset;
import com.jtj.web.entity.Borrow;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.User;
import com.jtj.web.service.AssetOperationRecordService;
import com.jtj.web.service.BorrowService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/3/15.
 */
@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowDao borrowDao;
    @Autowired
    private AssetOperationRecordService assetOperationRecordService;
    @Autowired
    private AssetDao assetDao;
    @Autowired
    private UserDao userDao;

    @Override
    public BorrowDao getRepository() {
        return borrowDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto<Object> borrowAsset(Borrow borrow) throws AssetException {
        ResultDto<Object> result = new ResultDto<>();
        AssetDto dto = BeanUtils.fromBean(borrow.getAsset(),AssetDto.class);
        dto.setStatus(Constant.AssetStatus.NORMAL.getId());
        List<Asset> assets = assetDao.getList(dto);
        if (assets.size() == 0){
            result.setResultCode(ResultCode.ASSET_NON_EXISTENT);
            throw new AssetException(result);
        }
        if (assets.size() > 1){
            result.setResultCode(ResultCode.ASSET_NOT_ONLY);
            result.setMessage("请使用uuid或者添加更多条件");
            throw new AssetException(result);
        }
        //todo 权限判断
        String uuid = assets.get(0).getUuid();
        result.setResultCode(updateAssetStatus(uuid, Constant.AssetStatus.BORROW) == 1?
                ResultCode.SUCCESS_OPERATE:ResultCode.OPERATE_FAIL);
        assetOperationRecordService.addOperationRecord(uuid, Constant.OperationType.BORROW,
                result.getTitle()+",租借人id："+borrow.getUserId());
        borrow.setUuid(uuid);
        borrowDao.add(borrow);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto<Object> returnAsset(Borrow borrow) throws AssetException {
        ResultDto<Object> result = new ResultDto<>();
        AssetDto dto = BeanUtils.fromBean(borrow.getAsset(),AssetDto.class);
        dto.setStatus(Constant.AssetStatus.BORROW.getId());
        List<Asset> assets = assetDao.getList(dto);
        if (assets.size() == 0){
            result.setResultCode(ResultCode.ASSET_NON_EXISTENT);
            throw new AssetException(result);
        }
        if (assets.size() > 1){
            result.setResultCode(ResultCode.ASSET_NOT_ONLY);
            result.setMessage("请使用uuid或者添加更多条件");
            throw new AssetException(result);
        }
        String uuid = assets.get(0).getUuid();
        result.setResultCode(updateAssetStatus(uuid, Constant.AssetStatus.NORMAL) == 1?
                ResultCode.SUCCESS_OPERATE:ResultCode.OPERATE_FAIL);
        assetOperationRecordService.addOperationRecord(uuid, Constant.OperationType.RETURN,
                result.getTitle()+",归还人id："+borrow.getUserId());
        borrow.setUuid(uuid);
        borrow.setStatus(2);
        if (borrow.getReturnTime() == null) borrow.setReturnTime(new Date());
        borrowDao.updateStatus(borrow);
        return result;
    }

    @Override
    public ResultDto<List<Borrow>> getMyBorrow() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        ResultDto<List<Borrow>> result = new ResultDto<>(ResultCode.SUCCESS_GET);
        result.setObject(borrowDao.getMyBorrow(user.getId()));
        return result;
    }

    @Override
    public ResultDto<Object> borrowAssetBySelf(Borrow borrow) throws AssetException {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        borrow.setUserId(user.getId());
        return borrowAsset(borrow);
    }

    @Override
    public ResultDto<List<KeyValue>> getUsers(String name) {
        ResultDto<List<KeyValue>> result = new ResultDto<>(ResultCode.SUCCESS_GET);
        if (!StringUtils.isEmpty(name))
            result.setObject(userDao.getKeyValueByName(name));
        return result;
    }

    private int updateAssetStatus(String uuid, Constant.AssetStatus status){
        return assetDao.updateAssetStatus(uuid,status.getId());
    }
}
