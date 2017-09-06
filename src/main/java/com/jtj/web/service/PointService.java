package com.jtj.web.service;

import com.jtj.web.common.BasePointDto;
import com.jtj.web.common.ResultDto;
import com.jtj.web.common.exception.AssetException;
import com.jtj.web.dto.PointDto;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.Point;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2016/12/23 23:26 End.
 */
@Service
public interface PointService extends BaseService<Point,PointDto> {

    ResultDto<List<Point>> getPoint(PointDto dto);

    List<Point> getAllPoint();

    ResultDto<List<Point>> getPointTree();

    ResultDto<List<KeyValue>> getMapByPid(Long pid) throws AssetException;

    List<Point> getQueryPoint();

    List<Point> getQueryRootPoint();

    Point getAuthenticationPoint(BasePointDto dto);

    ResultDto<List<Point>> getPointByPid(Long pid);

    ResultDto<Point> getPointById(Long id);

    void refresh();
}
