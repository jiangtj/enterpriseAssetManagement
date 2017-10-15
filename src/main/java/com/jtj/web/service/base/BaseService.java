package com.jtj.web.service.base;

import com.jtj.web.common.BaseDto;
import com.jtj.web.common.BaseEntity;
import com.jtj.web.common.PageDto;
import com.jtj.web.common.ResultDto;
import com.jtj.web.common.exception.AssetException;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/4/17 22:13 End.
 */
public interface BaseService<E extends BaseEntity,T extends BaseDto> {

    ResultDto<Object> add(E t);

    ResultDto<Object> delete(Long[] ids) throws AssetException;

    ResultDto<Object> update(E t);

    ResultDto<PageDto<E>> getList(T dto);
}
