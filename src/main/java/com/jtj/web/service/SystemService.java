package com.jtj.web.service;

import com.jtj.web.common.ResultDto;
import com.jtj.web.entity.KeyValue;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/3/15.
 */
@Service
public interface SystemService {

    String init(HttpServletRequest request, HttpServletResponse response);

    ResultDto<List<KeyValue>> getDictionaryMap(String table, String column);

}
