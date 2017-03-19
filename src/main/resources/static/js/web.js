//ajax请求
const Web = {
    baseUrl:baseUrl,
    buildUrl:function (url) {
        if (url == null) return null;
        if (url.charAt(0) == "/"){
            if (url.charAt(1) == "/"){
                return "http:" + url;
            }
            return Web.baseUrl + url;
        }
        if (url.charAt(0) == "~"){
            if (url.charAt(1) == "/"){
                return url.substring(1);
            }
        }
        return url;
    },
    isCallbackOrOptions:function (data) {
        if (jQuery.isFunction( data )) return true;
        if (!JsonUtils.isJson(data)) return false;
        return (jQuery.isFunction(data.success) || jQuery.isFunction(data.error) || data.defaultHandling != null);
    },
    isSuccess:function(obj){
        return obj.code.charAt(1) == "0";
    },
    go:function (url) {
        window.location.href = Web.buildUrl(url);
    },
    submit:function (customsOptions) {
        var options = jQuery.extend(true,{},customsOptions);
        //dataType数据类型
        options.dataType = options.dataType.toLowerCase();
        //默认回调处理
        if (options.dataType == "json" && options.defaultHandling){
            options.success = function (response,status,xhr) {
                if (!JsonUtils.isJson(response)) {
                    if (options.url.indexOf(".") == -1){
                        Web.go(url);
                        return;
                    }
                    customsOptions.success(response,status,xhr);
                    return;
                }
                ToastrUtils.showResult(response);
                customsOptions[Web.isSuccess(response)?"success":"fail"](response,status,xhr);
            };
        }
        return jQuery.ajax(options);
    }
};
jQuery.each( [ "get", "post" ], function( i, method ) {
    Web[ method ] = function( url, data, callbackOrOptions) {

        //处理回调
        if ( Web.isCallbackOrOptions( data ) ) {
            callbackOrOptions = data;
            data = undefined;
        }
        if ( jQuery.isFunction( callbackOrOptions ) ) {
            callbackOrOptions = {success:callbackOrOptions};
        }

        //默认配置
        var options = {
            url:Web.buildUrl(url),
            type:method,
            data:data,
            dataType:"json",
            defaultHandling:true,
            error:function (XMLHttpRequest, textStatus, errorThrown) {
                debugger;
                ToastrUtils.show("系统错误","ajax请求出错，可能原因授权过期，请重新登录！",9);
                console.log(XMLHttpRequest);
                /*if (textStatus == "parsererror"){
                 Web.go(url);
                 }*/
            }
        };
        return Web.submit(jQuery.extend(true,options,callbackOrOptions));
    };
});

function WebBuilder(url,defaultOptions) {
    this.options = {};
    this.defaultOptions = jQuery.extend(true,{
        url:Web.buildUrl(url),
        dataType:"json",
        defaultHandling:true,
        error:function (XMLHttpRequest, textStatus, errorThrown) {
            debugger;
            ToastrUtils.show("系统错误","ajax请求出错，可能原因授权过期，请重新登录！",9);
            console.log(XMLHttpRequest);
        }
    },defaultOptions);
}
jQuery.extend(true,WebBuilder.prototype,{
    url:function (url) {
        this.options.url = Web.buildUrl(url);
        return this;
    },
    set:function (options) {
        jQuery.extend(true,this.options,options);
        return this;
    },
    setData:function (data) {
        this.options.data = data;
        return this;
    },
    setMethod:function (method) {
        this.options.type = method;
        return this;
    },
    setType:function (daraType) {
        this.options.daraType = daraType;
        return this;
    },
    setAsync:function (async) {
        this.options.async = async;
        return this;
    },
    setSuccess:function (callback) {
        this.options.success = callback;
        return this;
    },
    setFail:function (callback) {
        this.options.fail = callback;
        return this;
    },
    setError:function (callback) {
        this.options.error = callback;
        return this;
    },
    setDefaultHandling:function (flag) {
        this.options.defaultHandling = flag;
        return this;
    }
});
jQuery.each(['get','post','execute'],function (i,method) {
    WebBuilder.prototype[method] = function (callback) {
        if (method != 'execute') this.options.type = method;
        if (jQuery.isFunction(callback)){
            this.options.success = callback;
        }else {
            this.options = jQuery.extend(true,this.options,callback);
        }
        var tempOptions = jQuery.extend(true,this.options,this.defaultOptions);
        this.options = {};
        return Web.submit(tempOptions);
    }
});