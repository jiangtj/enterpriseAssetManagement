package com.jtj.web.service;

import com.jtj.web.dto.ResultDto;
import com.jtj.web.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2016/12/23 23:26 End.
 */
@Service
public interface UserService {

    ResultDto<Object> queryById(long id);

}
