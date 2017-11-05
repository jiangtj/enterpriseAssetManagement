package com.jtj.web.service;

import com.jtj.web.common.BasePointDto;
import com.jtj.web.common.ResultDto;
import com.jtj.web.common.exception.AssetException;
import com.jtj.web.dao.PointDao;
import com.jtj.web.dto.PointDto;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.Point;
import com.jtj.web.service.base.CurdService;
import com.jtj.web.service.base.TreeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2016/12/23 23:26 End.
 */
@Service
public interface PointService extends CurdService<Point,PointDto,PointDao>, TreeService<Point> {

    ResultDto<Object> deleteById(Long id) throws AssetException;

    ResultDto<List<KeyValue>> getMapByPid(Long pid) throws AssetException;

    List<Point> getQueryPoint();

    List<Point> getQueryRootPoint();

    Point getAuthenticationPoint(BasePointDto dto);
}
