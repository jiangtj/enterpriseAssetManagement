package com.jtj.web.service.impl;

import com.jtj.web.common.BasePointDto;
import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.common.config.PointConfig;
import com.jtj.web.common.exception.AssetException;
import com.jtj.web.dao.PointDao;
import com.jtj.web.dto.PointDto;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.Point;
import com.jtj.web.entity.User;
import com.jtj.web.feign.PointClient;
import com.jtj.web.service.PointService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public ResultDto<Object> update(Point t) {
        if (t.getId() == 0) t.setPid(0L);
        return super.update(t);
    }

    @Override
    public ResultDto<Object> delete(Long[] ids) throws AssetException {
        for (Long id : ids) {
            if (id == 0) {
                return new ResultDto<>(ResultCode.NOT_DELETE_ROOT);
            }
        }
        return super.delete(ids);
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

        synchronized (this){
            allPointMap = new HashMap<>();
            allPointList = new ArrayList<>();
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
        if (pid == null) {
            result.setObject(getAllPoint().stream().map(item->new KeyValue(item.getId()+"",item.getName()))
                    .collect(Collectors.toList()));
            return result;
        }
        //todo 调整js tree
        //todo issue1 id为0的总部不能删除
        //todo issue2 前端控件多次调用接口

        //pid存在返回子节点
        Point point = allPointMap.get(pid);
        if (point == null && allPointList.size() == 0) {
            getAllPoint();
            point = allPointMap.get(pid);
        }

        if (point == null){
            result.setResultCode(ResultCode.NOT_FOUND);
            return result;
        }

        result.setObject(point.getNodes().stream().map(item->new KeyValue(item.getId()+"",item.getName()))
                .collect(Collectors.toList()));

        return result;
    }

    //todo "system-point-subordinate:query"
    //"system-point-subordinate:query:edit"
    //"system-point-subordinate:query:globe"
    @Override
    public List<Point> getQueryPoint() {
        Point point = getQueryRootPoint();
        List<Point> points = new ArrayList<>();
        points.add(point);
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isPermitted("system-point-subordinate:query")) {
            return points;
        }
        List<List<Point>> lists = new ArrayList<>();
        lists.add(points);
        int i = 1;
        while (points.size() != 0){
            List<Point> pre = lists.get(i-1);
            points = new ArrayList<>();
            for (Point p1:pre){
                points.addAll(p1.getNodes());
            }
            if (points.size() == 0) break;
            lists.add(points);
            i++;
        }
        return lists.stream().flatMap(Collection::stream).distinct().collect(Collectors.toList());
    }

    @Override
    public Point getQueryRootPoint() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        if (allPointMap == null || allPointMap.size() == 0) getAllPoint();
        if (subject.isPermitted("system-point-subordinate:query:globe")) {
            return allPointMap.get(0L);
        }
        return allPointMap.get(user.getPointId());
    }

    @Override
    public Point getAuthenticationPoint(BasePointDto dto) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        long pointId = dto.getPointId() == null?0L:dto.getPointId();
        if (subject.isPermitted("point-subordinate-query:point-globe-query")) {
            return allPointMap.get(pointId);
        }
        if (subject.isPermitted("point-subordinate-query")){
            boolean flag = checkAuthenticationPoint(pointId,user.getPoint());
            return flag?allPointMap.get(pointId):user.getPoint();
        }
        return user.getPoint();
    }

    @Override
    public ResultDto<List<Point>> getPointByPid(Long pid) {
        ResultDto<List<Point>> result = new ResultDto<>(ResultCode.SUCCESS);
        if (allPointMap == null || allPointMap.size() == 0) getAllPoint();
        result.setObject(allPointMap.get(pid).getNodes());
        return result;
    }

    private boolean checkAuthenticationPoint(long pointId, Point point) {
        if (point == null) return false;
        if (point.getId() == pointId) return true;
        if (point.getNodes() == null) return false;
        for (Point node : point.getNodes()){
            if (checkAuthenticationPoint(pointId,node)) return true;
        }
        return false;
    }
}
