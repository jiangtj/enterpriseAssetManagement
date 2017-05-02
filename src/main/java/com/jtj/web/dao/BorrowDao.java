package com.jtj.web.dao;

import com.jtj.web.dto.BorrowDto;
import com.jtj.web.entity.Borrow;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/3/15.
 */
@Mapper
@Component
public interface BorrowDao extends BaseDao<Borrow,BorrowDto>{

}
