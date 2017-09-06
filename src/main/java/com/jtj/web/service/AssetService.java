package com.jtj.web.service;

import com.jtj.web.common.ResultDto;
import com.jtj.web.common.exception.AssetException;
import com.jtj.web.dto.AssetDto;
import com.jtj.web.entity.Asset;
import com.jtj.web.entity.Borrow;
import org.springframework.stereotype.Service;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2016/12/23 23:26 End.
 */
@Service
public interface AssetService extends BaseService<Asset,AssetDto> {

    ResultDto<Object> borrowAsset(Borrow borrow) throws AssetException;

    ResultDto<Object> returnAsset(Borrow borrow) throws AssetException;

    ResultDto<Object> updateStatus(String uuid, Integer status, String remark);
}
