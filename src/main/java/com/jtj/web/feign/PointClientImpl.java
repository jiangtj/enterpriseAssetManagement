package com.jtj.web.feign;

import com.jtj.web.dao.PointDao;
import com.jtj.web.entity.Point;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/8/21 23:22 End.
 */
@Deprecated
//@Component
public class PointClientImpl implements PointClient {

    @Autowired
    private PointDao pointDao;

    @Override
    public List<Point> getPoint() {
        return pointDao.getAllPoint();
    }
}
