package com.jtj.web.service;

import com.jtj.web.common.exception.AssetException;
import com.jtj.web.common.PageDto;
import com.jtj.web.common.ResultDto;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/4/17 22:13 End.
 */
public interface BaseService<Entity,Dto> {

    ResultDto<Object> add(Entity t);

    ResultDto<Object> delete(Long[] ids) throws AssetException;

    ResultDto<Object> update(Entity t);

    ResultDto<PageDto<Entity>> getList(Dto dto);
}
