package com.jtj.web.service.impl;

import com.jtj.web.common.Constant;
import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.common.utils.BeanUtils;
import com.jtj.web.dao.AssetDao;
import com.jtj.web.dao.BorrowDao;
import com.jtj.web.dto.AssetDto;
import com.jtj.web.dto.BorrowDto;
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
public class BorrowServiceImpl
        extends BaseServiceImpl<Borrow,BorrowDto,BorrowDao>
        implements BorrowService {

    @Autowired
    private BorrowDao borrowDao;
}
