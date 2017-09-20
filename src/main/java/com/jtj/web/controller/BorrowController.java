package com.jtj.web.controller;

import com.jtj.web.common.PageDto;
import com.jtj.web.common.ResultDto;
import com.jtj.web.common.exception.AssetException;
import com.jtj.web.dto.BorrowDto;
import com.jtj.web.entity.Borrow;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.service.BorrowService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/9/11 20:22 End.
 */
@RestController
@RequestMapping("/borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @PostMapping("/add")
    @RequiresPermissions("asset:borrow:operation")
    public ResultDto<Object> borrowAsset(@RequestBody Borrow borrow) throws AssetException {
        return borrowService.borrowAsset(borrow);
    }

    @PostMapping("/add/me")
    public ResultDto<Object> borrowAssetBySelf(@RequestBody Borrow borrow) throws AssetException {
        return borrowService.borrowAssetBySelf(borrow);
    }

    @PostMapping("/return")
    @RequiresPermissions("asset:borrow:operation")
    public ResultDto<Object> returnAsset(@RequestBody Borrow borrow) throws AssetException {
        return borrowService.returnAsset(borrow);
    }

    @GetMapping("/me")
    //@RequiresPermissions("asset:list")
    public ResultDto<List<Borrow>> getMyBorrow(){
        return borrowService.getMyBorrow();
    }

    @GetMapping("/list")
    @RequiresPermissions("asset:borrow:list")
    public ResultDto<PageDto<Borrow>> getList(BorrowDto dto){
        return borrowService.getList(dto);
    }

    @GetMapping("/users")
    @RequiresPermissions("asset:borrow:operation")
    public ResultDto<List<KeyValue>> getUsers(String name){
        return borrowService.getUsers(name);
    }

}
