package com.jtj.web.feign;

import com.jtj.web.entity.Point;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/8/21 21:51 End.
 */
@Deprecated
/*@FeignClient(name = "${point.client.name}", url = "${point.client.url}", configuration = PointConfiguration.class,
        fallback = PointClientImpl.class)*/
public interface PointClient {

    @GetMapping("${point.client.path}")
    List<Point> getPoint();

}
