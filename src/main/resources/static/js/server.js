const defaultIntercept = function (response,status,thrown,context) {
    //dataType数据类型
    context.dataType = context.dataType.toLowerCase();
    //默认回调处理
    if (context.dataType === "json"){
        if (!JsonUtils.isJson(response)) {
            if (options.url.indexOf(".") === -1){
                Web.go(url);
                return true;//true 拦截
            }
            return false;
        }
        ToastrUtils.showResult(response);
        if (Web.isSuccess(response)){
            return false;//false 不拦截
        }else {
            context.fail(response,status,thrown);
            return true;
        }
    }
};

const defaultListIntercept = function (response,status,thrown,context) {

    if (!JsonUtils.isJson(response)) {
        ToastrUtils.show("类型不匹配","",9);
        console.log(response);
        return true;
    }

    if (Web.isSuccess(response)){
        return false;//false 不拦截
    }

    ToastrUtils.showResult(response);
    context.fail(response,status,thrown);
    return true;
};

Web.setBaseUrl(baseUrl);

Web.setPrototype({
    setFail:function (callback) {
        this.options.fail = callback;
        return this;//return this 链式调用
    }
});

Web.setDefault({
    fail:function () {},
    error:function (XMLHttpRequest, textStatus, errorThrown) {
        debugger;
        ToastrUtils.show("系统错误","ajax请求出错，可能原因授权过期，请重新登录！",9);
        console.log(XMLHttpRequest);
    }
});

const Server = {
    user:{
        getList:new WebBuilder("/user/getList",{intercepts:defaultListIntercept}),
        add:new WebBuilder("/user/add",{intercepts:defaultIntercept}),
        delete:new WebBuilder("/user/delete",{intercepts:defaultIntercept}),
        update:new WebBuilder("/user/update",{intercepts:defaultIntercept}),
        updatePoint:new WebBuilder("/user/updatePoint",{intercepts:defaultIntercept})
    },
    role:{
        getList:new WebBuilder("/role/getList",{intercepts:defaultListIntercept}),
        add:new WebBuilder("/role/add",{intercepts:defaultIntercept}),
        delete:new WebBuilder("/role/delete",{intercepts:defaultIntercept}),
        update:new WebBuilder("/role/update",{intercepts:defaultIntercept}),
        getPermission:new WebBuilder("/role/getPermission",{intercepts:defaultListIntercept}),
        updatePermission:new WebBuilder("/role/updatePermission",{intercepts:defaultIntercept}),
        updatePoint:new WebBuilder("/role/updatePoint",{intercepts:defaultIntercept})
    },
    permission:{
        getList:new WebBuilder("/permission/getList",{intercepts:defaultListIntercept}),
        add:new WebBuilder("/permission/add",{intercepts:defaultIntercept}),
        addQuick:new WebBuilder("/permission/addQuick",{intercepts:defaultIntercept}),
        delete:new WebBuilder("/permission/delete",{intercepts:defaultIntercept}),
        update:new WebBuilder("/permission/update",{intercepts:defaultIntercept})
    },
    menu:{
        getList:new WebBuilder("/menu/getList",{intercepts:defaultListIntercept}),
        add:new WebBuilder("/menu/add",{intercepts:defaultIntercept}),
        delete:new WebBuilder("/menu/delete",{intercepts:defaultIntercept}),
        update:new WebBuilder("/menu/update",{intercepts:defaultIntercept}),
        getMenu:new WebBuilder("/menu/getMenu",{intercepts:defaultListIntercept}),
        getPublicMenu:new WebBuilder("/public/getMenu",{intercepts:defaultListIntercept}),
        getMapByPid:new WebBuilder("/menu/getMapByPid",{intercepts:defaultListIntercept})
    },
    assetType:{
        getList:new WebBuilder("/assetType/getList",{intercepts:defaultListIntercept}),
        add:new WebBuilder("/assetType/add",{intercepts:defaultIntercept}),
        delete:new WebBuilder("/assetType/delete",{intercepts:defaultIntercept}),
        update:new WebBuilder("/assetType/update",{intercepts:defaultIntercept}),
        getType:new WebBuilder("/assetType/getType",{intercepts:defaultListIntercept}),
        getMapByPid:new WebBuilder("/assetType/getMapByPid",{intercepts:defaultListIntercept})
    },
    point:{
        getList:new WebBuilder("/point/getList",{intercepts:defaultListIntercept}),
        add:new WebBuilder("/point/add",{intercepts:defaultIntercept}),
        delete:new WebBuilder("/point/delete",{intercepts:defaultIntercept}),
        update:new WebBuilder("/point/update",{intercepts:defaultIntercept}),
        getPoint:new WebBuilder("/point/getPoint",{intercepts:defaultListIntercept}),
        getMapByPid:new WebBuilder("/point/getMapByPid",{intercepts:defaultListIntercept}),
        getPublicPoint:new WebBuilder("/public/getPoint",{intercepts:defaultListIntercept})
    },
    asset:{
        getList:new WebBuilder("/asset/getList",{intercepts:defaultListIntercept}),
        add:new WebBuilder("/asset/add",{intercepts:defaultIntercept}),
        delete:new WebBuilder("/asset/delete",{intercepts:defaultIntercept}),
        update:new WebBuilder("/asset/update",{intercepts:defaultIntercept}),
        getOperationRecordByUuid:new WebBuilder("/asset/getOperationRecordByUuid",{intercepts:defaultListIntercept}),
        borrowAsset:new WebBuilder("/asset/borrowAsset",{intercepts:defaultIntercept}),
        returnAsset:new WebBuilder("/asset/returnAsset",{intercepts:defaultIntercept}),
        updateStatus:new WebBuilder("/asset/updateStatus",{intercepts:defaultIntercept}),
        addStockTake:new WebBuilder("/asset/addStockTake",{intercepts:defaultIntercept})
    },
    report:{
        getOverall:new WebBuilder("/report/getOverall",{intercepts:defaultListIntercept}),
        getBorrow:new WebBuilder("/report/getBorrow",{intercepts:defaultListIntercept}),
    },
    stockTake:{
        handle:new WebBuilder("/stockTake/handle",{intercepts:defaultIntercept}),
        getList:new WebBuilder("/stockTake/getList",{intercepts:defaultListIntercept}),
        add:new WebBuilder("/stockTake/add",{intercepts:defaultIntercept}),
        delete:new WebBuilder("/stockTake/delete",{intercepts:defaultIntercept}),
        updateAmount:new WebBuilder("/stockTake/updateAmount",{intercepts:defaultIntercept}),
        getItemList:new WebBuilder("/stockTake/getItemList",{intercepts:defaultListIntercept}),
        updateToAbnormal:new WebBuilder("/stockTake/updateToAbnormal",{intercepts:defaultIntercept}),
        close:new WebBuilder("/stockTake/close",{intercepts:defaultIntercept})
    }
};