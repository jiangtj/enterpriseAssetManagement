package com.jtj.web.service.impl;

import com.jtj.web.common.Constant;
import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dao.PointDao;
import com.jtj.web.dto.PointDto;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.Point;
import com.jtj.web.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/3/15.
 */
@Service
public class PointServiceImpl
        extends BaseServiceImpl<Point,PointDto,PointDao>
        implements PointService {

    @Autowired
    private PointDao pointDao;

    @Override
    public ResultDto<Object> add(Point t) {
        if (t.getPid() == null) {
            t.setPid(0L);
            t.setLevel(1);
        }else {
            Point type = pointDao.getById(t.getPid());
            t.setLevel(type.getLevel() + 1);
        }
        return super.add(t);
    }

    @Override
    public ResultDto<List<Point>> getPoint(PointDto dto) {
        ResultDto<List<Point>> result = new ResultDto<>(ResultCode.SUCCESS);
        result.setObject( pointDao.getPoint(dto));
        return result;
    }

    @Override
    public ResultDto<List<KeyValue>> getMapByPid(Long pid) {
        ResultDto<List<KeyValue>> result = new ResultDto<>(ResultCode.SUCCESS);
        result.setObject( pointDao.getMapByPid(pid));
        return result;
    }

    @Override
    public ResultDto<List<Point>> getPublicPoint(HttpSession session) {
        ResultDto<List<Point>> result = new ResultDto<>(ResultCode.SUCCESS);
        List<Point> points = (List<Point>) session.getAttribute(Constant.SESSION_POINT);
        result.setObject(points);
        return result;
    }
}
