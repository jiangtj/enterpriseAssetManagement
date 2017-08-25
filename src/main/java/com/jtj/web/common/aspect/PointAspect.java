package com.jtj.web.common.aspect;

import com.jtj.web.common.BasePointDto;
import com.jtj.web.entity.Point;
import com.jtj.web.entity.User;
import com.jtj.web.service.PointService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/4/9 20:16 End.
 */
@Aspect
@Component
public class PointAspect {

    @Autowired
    private PointService pointService;

    @Pointcut("execution(* com.jtj.web.controller.*.*(..))")
    public void pointcut(){}

    @Before("pointcut()")
    public void aroundMethod(JoinPoint joinPoint){
        Object[] args=joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BasePointDto) {

                BasePointDto dto = (BasePointDto) arg;
                Subject subject = SecurityUtils.getSubject();
                User user = (User) subject.getPrincipal();
                List<Point> points = user.getQueryPoints();
                if (points == null){
                    points = pointService.getQueryPoint();
                    user.setQueryPoints(points);
                }

                //无权限
                if (points.size() == 1){
                    dto.setPointId(points.get(0).getId());
                    dto.setPointIds(null);
                    return;
                }

                //全局权限
                if (subject.isPermitted("system-point-subordinate:query:globe") ||
                        (user.getPoint().getId() == 0 && subject.isPermitted("system-point-subordinate:query"))){
                    if (dto.getPointIds() != null){
                        if (dto.getPointIds().size() == 1){
                            dto.setPointId(dto.getPointIds().get(0));
                            dto.setPointIds(null);
                        }
                    }
                    return;
                }

                //部分权限
                List<Long> ids = points.stream().map(Point::getId).collect(Collectors.toList());

                if (dto.getPointId() != null) {
                    dto.setPointIds(null);
                    if (!ids.contains(dto.getPointId())) {
                        dto.setPointId(user.getPointId());
                    }
                    return;
                }

                if (dto.getPointIds() == null) {
                    dto.setPointIds(ids);
                    return;
                }

                if (ids.contains(dto.getPointId())){
                    if (dto.getPointIds() != null || dto.getPointIds().size() == 1){
                        dto.setPointId(dto.getPointIds().get(0));
                        dto.setPointIds(null);
                    }
                    return;
                }

                dto.setPointId(user.getPointId());

            }
        }
    }

}
