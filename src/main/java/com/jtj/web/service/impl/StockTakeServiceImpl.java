package com.jtj.web.service.impl;

import com.jtj.web.common.Constant;
import com.jtj.web.common.PageDto;
import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.common.utils.BeanUtils;
import com.jtj.web.dao.AssetDao;
import com.jtj.web.dao.StockTakeDao;
import com.jtj.web.dto.AssetDto;
import com.jtj.web.dto.StockTakeDto;
import com.jtj.web.dto.StockTakeItemDto;
import com.jtj.web.entity.*;
import com.jtj.web.service.AssetOperationRecordService;
import com.jtj.web.service.StockTakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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
    @Autowired
    private AssetOperationRecordService assetOperationRecordService;

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

    @Override
    public ResultDto<Object> handleItem(StockTakeItem item) {
        ResultDto<Object> result = new ResultDto<>();
        StockTakeItemDto dto = BeanUtils.fromBean(item,StockTakeItemDto.class);
        List<StockTakeItem> itemList = stockTakeDao.getItemList(dto);
        if (itemList.size() == 0){
            result.setResultCode(ResultCode.ASSET_NON_EXISTENT);
            return result;
        }
        if (itemList.size() > 1){
            result.setResultCode(ResultCode.ASSET_NOT_ONLY);
            result.setMessage("请使用uuid或者添加更多条件");
            return result;
        }
        //todo 权限判断
        String uuid = itemList.get(0).getUuid();
        result.setResultCode(updateItemStatus(itemList.get(0).getStockTakeId(),uuid, Constant.StockTakeItemStatus.NORMAL) == 1?
                ResultCode.SUCCESS:ResultCode.OPERATE_FAIL);
        assetOperationRecordService.addOperationRecord(uuid, Constant.OperationType.PAN, result.getTitle());
        return result;
    }

    @Override
    public ResultDto<List<KeyValue>> getAvailableMap() {
        ResultDto<List<KeyValue>> result = new ResultDto<>(ResultCode.SUCCESS);
        result.setObject(stockTakeDao.getAvailableMap());
        return result;
    }

    @Override
    public ResultDto<StockTake> updateAmount(Long id) {
        ResultDto<StockTake> result = new ResultDto<>();
        result.setResultCode(stockTakeDao.updateAmount(id)==1?ResultCode.SUCCESS:ResultCode.OPERATE_FAIL);
        result.setObject(stockTakeDao.getById(id));
        return result;
    }

    @Override
    public ResultDto<PageDto<StockTakeItem>> getItemList(StockTakeItemDto dto) {
        return null;
    }

    public int updateItemStatus(Long stockTakeId,String uuid, Constant.StockTakeItemStatus status) {
        return stockTakeDao.updateItemStatus(stockTakeId, uuid, status.getId());
    }

}
