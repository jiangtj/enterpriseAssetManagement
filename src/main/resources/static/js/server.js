const defaultIntercept = function (response,status,thrown,context) {
    //dataType数据类型
    context.dataType = context.dataType.toLowerCase();
    //默认回调处理
    if (context.dataType == "json"){
        if (!JsonUtils.isJson(response)) {
            if (options.url.indexOf(".") == -1){
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
        getList:new WebBuilder("/user/getList"),
        add:new WebBuilder("/user/add",{intercepts:defaultIntercept}),
        delete:new WebBuilder("/user/delete",{intercepts:defaultIntercept}),
        update:new WebBuilder("/user/update",{intercepts:defaultIntercept})
    }
};