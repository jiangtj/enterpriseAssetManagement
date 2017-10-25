package com.jtj.web.service.base;

import com.jtj.web.common.*;
import com.jtj.web.common.exception.AssetException;
import com.jtj.web.dao.BaseDao;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/10/16.
 */
public interface CurdService<E extends BaseEntity,T extends BaseDto,D extends BaseDao<E,T>> {

    D getRepository();

    default ResultDto<Object> add(E t) {
        D dao = getRepository();
        ResultDto<Object> result = new ResultDto<>();
        dao.add(t);
        result.setResultCode(ResultCode.SUCCESS_POST);
        return result;
    }

    @Transactional(rollbackFor=Exception.class)
    default ResultDto<Object> delete(Long[] ids) throws AssetException {
        D dao = getRepository();
        ResultDto<Object> result = new ResultDto<>();
        int count = dao.delete(ids);
        int all = ids.length;
        if (count == all){
            result.setResultCode(ResultCode.SUCCESS_DELETE);
            return result;
        }
        result.setResultCode(ResultCode.OPERATE_FAIL);
        result.setMessage("存在"+(all - count)+"/"+all+"数据有误！");
        throw new AssetException(result);
    }

    default ResultDto<Object> update(E t) {
        D dao = getRepository();
        ResultDto<Object> result = new ResultDto<>();
        dao.update(t);
        result.setResultCode(ResultCode.SUCCESS_PUT);
        return result;
    }

    default ResultDto<PageDto<E>> getList(T dto) {
        D dao = getRepository();
        ResultDto<PageDto<E>> result = new ResultDto<>(ResultCode.SUCCESS_GET);
        PageDto<E> page = new PageDto<>();
        page.setList(dao.getList(dto));
        page.setCount(dao.getNum(dto));
        result.setObject(page);
        return result;
    }
}
