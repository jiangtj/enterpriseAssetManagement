package com.jtj.web.aspect;

import com.jtj.web.common.AssetException;
import com.jtj.web.common.BasePointDto;
import com.jtj.web.common.Constant;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dto.PointDto;
import com.jtj.web.entity.Point;
import com.jtj.web.entity.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/4/9 20:16 End.
 */
@Aspect
@Component
public class PointAspect {


    @Pointcut("execution(* com.jtj.web.controller.*.*(..))")
    public void pointcut(){}

    @Before("pointcut()")
    public void aroundMethod(JoinPoint joinPoint){
        Object[] args=joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BasePointDto) {
                BasePointDto dto = (BasePointDto) arg;
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                HttpSession session = request.getSession();
                List<Point> points = (List<Point>) session.getAttribute(Constant.SESSION_POINT);
                List<Long> ids = points.stream().map(Point::getId).collect(Collectors.toList());

                if (dto.getPointId() == null) {
                    dto.setPointIds(ids);
                    return;
                }

                if (ids.contains(dto.getPointId())) return;

                dto.setPointIds(ids);

            }
        }
    }

}
