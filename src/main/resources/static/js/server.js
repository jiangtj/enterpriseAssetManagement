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
        update:new WebBuilder("/user/update",{intercepts:defaultIntercept})
    },
    role:{
        getList:new WebBuilder("/role/getList",{intercepts:defaultListIntercept}),
        add:new WebBuilder("/role/add",{intercepts:defaultIntercept}),
        delete:new WebBuilder("/role/delete",{intercepts:defaultIntercept}),
        update:new WebBuilder("/role/update",{intercepts:defaultIntercept})
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
        getMapById:new WebBuilder("/menu/getMapById",{intercepts:defaultListIntercept})
    }
};