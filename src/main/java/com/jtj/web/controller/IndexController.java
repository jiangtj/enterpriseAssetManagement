package com.jtj.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/1/24 22:37 End.
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/loginPage")
    public String loginPage(){
        return "login";
    }
}
