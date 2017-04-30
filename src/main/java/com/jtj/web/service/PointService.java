package com.jtj.web.service;

import com.jtj.web.common.ResultDto;
import com.jtj.web.dto.AssetTypeDto;
import com.jtj.web.dto.PointDto;
import com.jtj.web.entity.AssetType;
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

    ResultDto<List<KeyValue>> getMapByPid(Long pid);
}
