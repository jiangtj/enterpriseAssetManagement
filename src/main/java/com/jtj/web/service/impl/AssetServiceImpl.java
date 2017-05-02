package com.jtj.web.service.impl;

import com.jtj.web.common.Constant;
import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dao.AssetDao;
import com.jtj.web.dao.AssetTypeDao;
import com.jtj.web.dto.AssetDto;
import com.jtj.web.dto.AssetTypeDto;
import com.jtj.web.entity.Asset;
import com.jtj.web.entity.AssetType;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.User;
import com.jtj.web.service.AssetService;
import com.jtj.web.service.AssetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @Override
    public ResultDto<Object> add(Asset t) {
        String uuid = UUID.randomUUID().toString();
        t.setUuid(uuid);
        assetDao.addOperationRecord(uuid, Constant.OperationType.ADD)
        return super.add(t);
    }

    private int addOperationRecord(String uuid,Constant.OperationType type){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constant.SESSION_USER);

        return assetDao.addOperationRecord( );
    }

}
