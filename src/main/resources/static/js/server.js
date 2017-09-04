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
        getType:new WebBuilder("/assetType/getType",{type:"get"}),
        getMapByPid:new WebBuilder("/assetType/getMapByPid",{type:"get"})
    },
    point:{
        getPointTree:new WebBuilder("/point/tree",{type:"get"}),
        getMapByPid:new WebBuilder("/point/map",{type:"get"}),
        getPointByPid:new WebBuilder("/point/get",{type:"get"}),
        getPointById:new WebBuilder("/point/get/{id}",{type:"get"}),
        getQueryRootPoint:new WebBuilder("/point/root/query",{type:"get"})
    },
    asset:{
        getOperationRecordByUuid:new WebBuilder("/asset/getOperationRecordByUuid",{type:"get"}),
        borrowAsset:new WebBuilder("/asset/borrow",{type:"post"}),
        returnAsset:new WebBuilder("/asset/return",{type:"post"}),
        updateStatus:new WebBuilder("/asset/updateStatus",{type:"put"}),
        addStockTake:new WebBuilder("/asset/addStockTake",{type:"post"})
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


/*
const Server = {
    user:{
        getList:new WebBuilder("/user/getList"),
        add:new WebBuilder("/user/add"),
        delete:new WebBuilder("/user/delete"),
        update:new WebBuilder("/user/update"),
        updatePoint:new WebBuilder("/user/updatePoint")
    },
    role:{
        getList:new WebBuilder("/role/getList"),
        add:new WebBuilder("/role/add"),
        delete:new WebBuilder("/role/delete"),
        update:new WebBuilder("/role/update"),
        getPermission:new WebBuilder("/role/getPermission"),
        updatePermission:new WebBuilder("/role/updatePermission"),
        updatePoint:new WebBuilder("/role/updatePoint")
    },
    permission:{
        getList:new WebBuilder("/permission/getList"),
        add:new WebBuilder("/permission/add"),
        addQuick:new WebBuilder("/permission/addQuick"),
        delete:new WebBuilder("/permission/delete"),
        update:new WebBuilder("/permission/update")
    },
    menu:{
        getList:new WebBuilder("/menu/getList"),
        add:new WebBuilder("/menu/add"),
        delete:new WebBuilder("/menu/delete"),
        update:new WebBuilder("/menu/update"),
        getMenu:new WebBuilder("/menu/getMenu"),
        getPublicMenu:new WebBuilder("/public/getMenu"),
        getMapByPid:new WebBuilder("/menu/getMapByPid")
    },
    assetType:{
        getList:new WebBuilder("/assetType/getList"),
        add:new WebBuilder("/assetType/add"),
        delete:new WebBuilder("/assetType/delete"),
        update:new WebBuilder("/assetType/update"),
        getType:new WebBuilder("/assetType/getType"),
        getMapByPid:new WebBuilder("/assetType/getMapByPid")
    },
    point:{
        getList:new WebBuilder("/point/getList"),
        add:new WebBuilder("/point/add"),
        delete:new WebBuilder("/point/delete"),
        update:new WebBuilder("/point/update"),
        getPoint:new WebBuilder("/point/getPoint"),
        getMapByPid:new WebBuilder("/point/getMapByPid"),
        getPublicPoint:new WebBuilder("/public/getPoint")
    },
    asset:{
        getList:new WebBuilder("/asset/getList"),
        add:new WebBuilder("/asset/add"),
        delete:new WebBuilder("/asset/delete"),
        update:new WebBuilder("/asset/update"),
        getOperationRecordByUuid:new WebBuilder("/asset/getOperationRecordByUuid"),
        borrowAsset:new WebBuilder("/asset/borrowAsset"),
        returnAsset:new WebBuilder("/asset/returnAsset"),
        updateStatus:new WebBuilder("/asset/updateStatus"),
        addStockTake:new WebBuilder("/asset/addStockTake")
    },
    report:{
        getOverall:new WebBuilder("/report/getOverall"),
        getBorrow:new WebBuilder("/report/getBorrow"),
    },
    stockTake:{
        handle:new WebBuilder("/stockTake/handle"),
        getList:new WebBuilder("/stockTake/getList"),
        add:new WebBuilder("/stockTake/add"),
        delete:new WebBuilder("/stockTake/delete"),
        updateAmount:new WebBuilder("/stockTake/updateAmount"),
        getItemList:new WebBuilder("/stockTake/getItemList"),
        updateToAbnormal:new WebBuilder("/stockTake/updateToAbnormal"),
        close:new WebBuilder("/stockTake/close")
    }
};
*/
