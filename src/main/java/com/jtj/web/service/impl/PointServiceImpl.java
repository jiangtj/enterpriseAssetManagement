package com.jtj.web.service.impl;

import com.jtj.web.common.BasePointDto;
import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.common.exception.AssetException;
import com.jtj.web.dao.AssetDao;
import com.jtj.web.dao.PointDao;
import com.jtj.web.dao.UserDao;
import com.jtj.web.dto.AssetDto;
import com.jtj.web.dto.PointDto;
import com.jtj.web.dto.UserDto;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.Point;
import com.jtj.web.entity.User;
import com.jtj.web.service.PointService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private AssetDao assetDao;
    @Autowired
    private UserDao userDao;

    //if you has redis, you can put it into redis
    private static Map<Long,Point> allPointMap = new HashMap<>();
    private static List<Point> allPointList = new ArrayList<>();
    private static List<Point> allRootPointList = new ArrayList<>();

    @Override
    public ResultDto<Object> add(Point t) {
        if (t.getPid() == null) {
            t.setPid(0L);
            t.setLevel(1);
        }else {
            Point type = pointDao.getById(t.getPid());
            t.setLevel(type.getLevel() + 1);
        }
        ResultDto<Object> result = super.add(t);
        refresh();
        result.setMessage("请刷新当前页面！");
        return result;
    }

    @Override
    public ResultDto<Object> update(Point t) {
        if (t.getId() == 0) t.setPid(0L);
        ResultDto<Object> result = super.update(t);
        refresh();
        return result;
    }

    @Override
    @Transactional(rollbackFor = AssetException.class)
    public ResultDto<Object> deleteById(Long id) throws AssetException {
        Point point = getAllPointMap().get(id);
        if (point.getNodes().size() != 0){
            throw new AssetException(new ResultDto<>(ResultCode.NOT_DELETE_USED));
        }
        if (point.getPid() == 0 || Objects.equals(point.getPid(), id)) {
            //根节点，如果存在用户不能删除
            UserDto userDto = new UserDto();
            userDto.setPointId(point.getId());
            if (userDao.getNum(userDto) != 0)
                throw new AssetException(new ResultDto<>(ResultCode.NOT_DELETE_ROOT));
            //根节点，如果存在资产不能删除
            AssetDto assetDto = new AssetDto();
            assetDto.setPointId(point.getId());
            if (assetDao.getNum(assetDto) != 0)
                throw new AssetException(new ResultDto<>(ResultCode.NOT_DELETE_ROOT));
        }else {
            userDao.updateToNewPoint(id,point.getPid());
            assetDao.updateToNewPoint(id,point.getPid());
        }
        ResultDto<Object> result = super.delete(new Long[]{id});
        refresh();
        return result;
    }

    @Override
    public List<Point> getAllPoint() {

        if (allPointList.size() != 0)
            return allPointList;

        initPointData();

        return allPointList;
    }

    @Override
    public ResultDto<List<Point>> getPointTree() {
        ResultDto<List<Point>> result = new ResultDto<>(ResultCode.SUCCESS);
        if (allRootPointList.size() == 0) getAllPoint();
        result.setObject(allRootPointList);
        return result;
    }

    @Override
    public ResultDto<List<KeyValue>> getMapByPid(Long pid) throws AssetException {
        ResultDto<List<KeyValue>> result = new ResultDto<>(ResultCode.SUCCESS);

        //pid不存在返回全部
        if (pid == null) {
            result.setObject(getAllPoint().stream().map(item->new KeyValue(item.getId()+"",item.getName()))
                    .collect(Collectors.toList()));
            return result;
        }

        //pid为0，返回所有根节点
        if (pid == 0){
            if (allRootPointList.size() == 0) getAllPoint();
            result.setObject(allRootPointList.stream().map(item->new KeyValue(item.getId()+"",item.getName()))
                    .collect(Collectors.toList()));
            return result;
        }

        //pid存在返回子节点
        Point point = getAllPointMap().get(pid);

        if (point == null){
            throw new AssetException(new ResultDto<>(ResultCode.NOT_FOUND));
        }

        result.setObject(point.getNodes().stream().map(item->new KeyValue(item.getId()+"",item.getName()))
                .collect(Collectors.toList()));

        return result;
    }

    @Override
    public List<Point> getQueryPoint() {
        List<Point> point = getQueryRootPoint();
        List<Point> points = new ArrayList<>();
        points.addAll(point);
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
                if (p1 != null)
                    points.addAll(p1.getNodes());
            }
            if (points.size() == 0) break;
            lists.add(points);
            i++;
        }
        return lists.stream().flatMap(Collection::stream).distinct().collect(Collectors.toList());
    }

    @Override
    public List<Point> getQueryRootPoint() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        if (subject.isPermitted("system-point-subordinate:query:globe")) {
            if (allRootPointList.size() == 0) getAllPoint();
            return allRootPointList;
        }
        List<Point> points = new ArrayList<>();
        points.add(getAllPointMap().get(user.getPointId()));
        return points;
    }

    @Override
    public Point getAuthenticationPoint(BasePointDto dto) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        long pointId = dto.getPointId() == null?0L:dto.getPointId();
        if (subject.isPermitted("point-subordinate-query:point-globe-query")) {
            return getAllPointMap().get(pointId);
        }
        if (subject.isPermitted("point-subordinate-query")){
            boolean flag = checkAuthenticationPoint(pointId,user.getPoint());
            return flag?getAllPointMap().get(pointId):user.getPoint();
        }
        return user.getPoint();
    }

    @Override
    public ResultDto<List<Point>> getPointByPid(Long pid) {
        ResultDto<List<Point>> result = new ResultDto<>(ResultCode.SUCCESS);
        result.setObject(getAllPointMap().get(pid).getNodes());
        return result;
    }

    @Override
    public ResultDto<Point> getPointById(Long id) {
        ResultDto<Point> result = new ResultDto<>(ResultCode.SUCCESS);
        result.setObject(getAllPointMap().get(id));
        return result;
    }

    private void initPointData() {
        synchronized (this){
            if (allPointList.size() == 0 ) refresh();
        }
    }

    @Override
    public void refresh() {
        allPointMap = new HashMap<>();
        allPointList = new ArrayList<>();
        allRootPointList = new ArrayList<>();
        allPointList = pointDao.getAllPoint();
        Map<Long,Point> temp = allPointList.stream().collect(Collectors.toMap(Point::getId, y->{
            y.setNodes(new ArrayList<>());
            return y;
        }));
        allPointList.forEach(point -> {
            if (Objects.equals(point.getPid(), point.getId()) || point.getPid() == 0) {
                allRootPointList.add(point);
                return;
            }
            Point p = temp.get(point.getPid());
            p.getNodes().add(point);
        });
        allPointMap = temp;
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

    private Map<Long, Point> getAllPointMap() {
        if (allPointMap == null || allPointMap.size() == 0) initPointData();
        return allPointMap;
    }
}
