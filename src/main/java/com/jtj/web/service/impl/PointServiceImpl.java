package com.jtj.web.service.impl;

import com.jtj.web.common.Constant;
import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.common.config.PointConfig;
import com.jtj.web.dao.PointDao;
import com.jtj.web.dto.PointDto;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.Point;
import com.jtj.web.feign.PointClient;
import com.jtj.web.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private PointConfig pointConfig;
    @Autowired
    private PointClient pointClient;

    //if you has redis, you can put it into redis
    private static Map<Long,Point> allPointMap = new HashMap<>();
    private static List<Point> allPointList = new ArrayList<>();

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
        List<Point> pointList =  pointDao.getPoint(dto);
        result.setObject(pointList);
        return result;
    }

    @Override
    public List<Point> getAllPoint() {

        if (allPointList.size() != 0)
            return allPointList;

        allPointMap = new HashMap<>();
        allPointList = new ArrayList<>();
        synchronized (this){
            allPointList = pointConfig.isEnabled()?pointClient.getPoint():pointDao.getAllPoint();
            Map<Long,Point> temp = allPointList.stream().collect(Collectors.toMap(Point::getId, y->{
                y.setNodes(new ArrayList<>());
                return y;
            }));
            allPointList.forEach(point -> {
                if (Objects.equals(point.getPid(), point.getId())) return;
                Point p = temp.get(point.getPid());
                p.getNodes().add(point);
            });
            allPointMap = temp;
        }

        return allPointList;
    }

    @Override
    public ResultDto<List<KeyValue>> getMapByPid(Long pid) {
        ResultDto<List<KeyValue>> result = new ResultDto<>(ResultCode.SUCCESS);

        //pid不存在返回全部
        /*if (pid == null) {
            result.setObject(getAllPoint().stream().map(item->new KeyValue(item.getId()+"",item.getName()))
                    .collect(Collectors.toList()));
            return result;
        }*/
        //todo 调整js tree
        //todo issue1 id为0的总部不能删除
        //todo issue2 前端控件多次调用接口
        if (pid == null) pid = 0L;

        //pid存在返回子节点
        Point point = allPointMap.get(pid);
        if (point == null && allPointList.size() == 0) {
            getAllPoint();
            point = allPointMap.get(pid);
        }

        result.setObject(point.getNodes().stream().map(item->new KeyValue(item.getId()+"",item.getName()))
                .collect(Collectors.toList()));

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
