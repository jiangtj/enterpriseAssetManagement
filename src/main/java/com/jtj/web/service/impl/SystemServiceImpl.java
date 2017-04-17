package com.jtj.web.service.impl;

import com.jtj.web.common.Constant;
import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dao.SystemDao;
import com.jtj.web.dao.UserDao;
import com.jtj.web.entity.Dictionary;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.User;
import com.jtj.web.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/3/15.
 */
@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private SystemDao systemDao;

    @Override
    public String init(HttpServletRequest request, HttpServletResponse response) {
        //todo 自动创表增加默认数据
        //todo 跳转至注册页面
        int count = systemDao.init();
        User user = userDao.getUserById(1L);
        if (user == null){
            return "init";
        }
        return "redirect:/login";
    }

    @Override
    public ResultDto<List<KeyValue>> getDictionaryMap(String table, String column) {
        ResultDto<List<KeyValue>> result = new ResultDto<>(ResultCode.SUCCESS);
        if (CollectionUtils.isEmpty(Constant.dictionaries)){
            Constant.dictionaries = systemDao.getDictionary();
        }
        List<KeyValue> list = Constant.dictionaries.stream()
                .filter(item -> (item.getTable().equals(table)&& item.getColumn().equals(column)))
                .map(item -> new KeyValue(item.getKey(),item.getValue()))
                .collect(Collectors.toList());

        result.setObject(list);
        return result;
    }
}
