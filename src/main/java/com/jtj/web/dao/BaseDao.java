package com.jtj.web.dao;

import com.jtj.web.common.BaseDto;
import com.jtj.web.common.BaseEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/4/17 22:32 End.
 */
public interface BaseDao<E extends BaseEntity,T extends BaseDto> {

    int add(E t);

    int delete(@Param("ids") Long[] ids);

    int update(E t);

    List<E> getList(T dto);

    int getNum(T dto);
}
