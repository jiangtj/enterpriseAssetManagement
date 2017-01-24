package com.jtj.web.service;

import com.jtj.web.common.ResultDto;
import org.springframework.stereotype.Service;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2016/12/23 23:26 End.
 */
@Service
public interface UserService {

    ResultDto<Object> queryById(long id);

}
