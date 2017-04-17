package com.jtj.web.dao;

import com.jtj.web.entity.Dictionary;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/4/10.
 */
@Mapper
public interface SystemDao {

    int init();

    List<Dictionary> getDictionary();

}
