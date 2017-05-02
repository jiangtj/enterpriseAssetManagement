package com.jtj.web.service.impl;

import com.jtj.web.common.Constant;
import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dao.AssetOperationRecordDao;
import com.jtj.web.entity.AssetOperationRecord;
import com.jtj.web.entity.User;
import com.jtj.web.service.AssetOperationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/5/2 23:50 End.
 */
@Service
public class AssetOperationRecordServiceImpl implements AssetOperationRecordService {

    @Autowired
    private AssetOperationRecordDao assetOperationRecordDao;

    @Override
    public int addOperationRecord(String uuid,Constant.OperationType type){
        return addOperationRecord(uuid, type, null);
    }

    @Override
    public int addOperationRecord(String uuid,Constant.OperationType type,String remark){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        AssetOperationRecord record = new AssetOperationRecord();
        record.setUuid(uuid);
        record.setOperationType(type.getId());
        record.setUserId(user.getId());
        record.setRemark(remark);
        return assetOperationRecordDao.addOperationRecord(record);
    }

    @Override
    public ResultDto<List<AssetOperationRecord>> getOperationRecordByUuid(String uuid) {
        ResultDto<List<AssetOperationRecord>> result = new ResultDto<>(ResultCode.SUCCESS);
        result.setObject(assetOperationRecordDao.getOperationRecordByUuid(uuid));
        return result;
    }

}
