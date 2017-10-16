package com.jtj.web.service;

import com.jtj.web.common.ResultDto;
import com.jtj.web.common.exception.AssetException;
import com.jtj.web.dao.BorrowDao;
import com.jtj.web.dto.BorrowDto;
import com.jtj.web.entity.Borrow;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.service.base.CurdService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2016/12/23 23:26 End.
 */
@Service
public interface BorrowService extends CurdService<Borrow,BorrowDto,BorrowDao> {

    ResultDto<Object> borrowAsset(Borrow borrow) throws AssetException;

    ResultDto<Object> returnAsset(Borrow borrow) throws AssetException;

    ResultDto<List<Borrow>> getMyBorrow();

    ResultDto<Object> borrowAssetBySelf(Borrow borrow) throws AssetException;

    ResultDto<List<KeyValue>> getUsers(String name);
}
