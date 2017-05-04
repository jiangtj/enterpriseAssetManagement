package com.jtj.web.service.impl;

import com.jtj.web.common.Constant;
import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dao.AssetDao;
import com.jtj.web.dao.StockTakeDao;
import com.jtj.web.dto.AssetDto;
import com.jtj.web.dto.StockTakeDto;
import com.jtj.web.entity.StockTake;
import com.jtj.web.entity.User;
import com.jtj.web.service.StockTakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/5/4.
 */
@Service
public class StockTakeServiceImpl extends BaseServiceImpl<StockTake,StockTakeDto,StockTakeDao> implements StockTakeService{

    @Autowired
    private StockTakeDao stockTakeDao;
    @Autowired
    private AssetDao assetDao;

    @Override
    public ResultDto<Object> addByAsset(String name, AssetDto dto) {
        StockTake stockTake = new StockTake();
        stockTake.setName(name);

        Integer allAmount = assetDao.getNum(dto);
        stockTake.setAllAmount(allAmount);
        stockTake.setHandlingAmount(allAmount);

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        stockTake.setUserId(user.getId());

        ResultDto<Object> result = super.add(stockTake);

        if (ResultCode.SUCCESS.getCode().equals(result.getCode())){
            stockTakeDao.addItem(stockTake.getId(),dto);
        }

        return result;
    }

}
