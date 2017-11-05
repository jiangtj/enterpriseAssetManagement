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
        if (response.code !== "000000" && response.code !== "000001") ToastrUtils.showResult(response);
        return !Web.isSuccess(response);
    }
};

Web.setBaseUrl(baseUrl).addIntercept(defaultIntercept);

Web.setDefault({
    error:function (request) {
        ToastrUtils.showResult(request.responseJSON);
        console.log(request);
    }
});

const Server = {};
const ServerUtils = {
    base:function (arr) {
        let tempArr = jQuery.isArray(arr)?arr:arguments;
        jQuery.each(tempArr,function (index,item) {
            Server[item] = {
                list:new WebBuilder("/"+item+"/list",{type:"get"}),
                add:new WebBuilder("/"+item+"/add",{type:"post"}),
                delete:new WebBuilder("/"+item+"/delete",{type:"delete"}),
                update:new WebBuilder("/"+item+"/update",{type:"put"}),
            }
        });
        return ServerUtils;
    },
    config:function (obj) {
        jQuery.extend(true,Server,obj);
    }
};

ServerUtils.base("user","role","permission","assetType","point","asset","report","stockTake");
ServerUtils.config({
    user:{
    },
    role:{
        getPermission:new WebBuilder("/role/getPermission",{type:"get"}),
        updatePermission:new WebBuilder("/role/updatePermission",{type:"put"}),
        updatePoint:new WebBuilder("/role/updatePoint",{type:"put"})
    },
    permission:{
    },
    assetType:{
        deleteById:new WebBuilder("/assetType/delete/{id}",{type:"delete"}),
        getTypeTree:new WebBuilder("/assetType/tree",{type:"get"}),
        getType:new WebBuilder("/assetType/getType",{type:"get"}),
        getMapByPid:new WebBuilder("/assetType/getMapByPid",{type:"get"})
    },
    point:{
        deleteById:new WebBuilder("/point/delete/{id}",{type:"delete"}),
        getPointTree:new WebBuilder("/point/tree",{type:"get"}),
        getMapByPid:new WebBuilder("/point/map",{type:"get"}),
        getPointByPid:new WebBuilder("/point/get",{type:"get"}),
        getPointById:new WebBuilder("/point/get/{id}",{type:"get"}),
        getQueryRootPoint:new WebBuilder("/point/root/query",{type:"get"})
    },
    asset:{
        getOperationRecordByUuid:new WebBuilder("/asset/getOperationRecordByUuid",{type:"get"}),
        borrowAsset:new WebBuilder("/borrow/add",{type:"post"}),
        returnAsset:new WebBuilder("/borrow/return",{type:"post"}),
        updateStatus:new WebBuilder("/asset/updateStatus",{type:"put"}),
        addStockTake:new WebBuilder("/asset/addStockTake",{type:"post"})
    },
    borrow:{
        me:new WebBuilder("/borrow/me",{type:"get"}),
        borrowBySelf:new WebBuilder("/borrow/add/me",{type:"post"}),
    },
    report:{
        getOverall:new WebBuilder("/report/getOverall",{type:"get"}),
        getBorrow:new WebBuilder("/report/getBorrow",{type:"get"}),
    },
    stockTake:{
        handle:new WebBuilder("/stockTake/handle",{type:"post"}),
        updateAmount:new WebBuilder("/stockTake/updateAmount",{type:"put"}),
        getItemList:new WebBuilder("/stockTake/getItemList",{type:"get"}),
        updateToAbnormal:new WebBuilder("/stockTake/updateToAbnormal",{type:"put"}),
        close:new WebBuilder("/stockTake/close",{type:"post"})
    }
});

