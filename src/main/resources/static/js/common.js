//json
const JsonUtils = {
    isJson:function (data) {
        return typeof(data) == "object" && Object.prototype.toString.call(data).toLowerCase() == "[object object]"
            && !data.length;
    }
};

//ajax请求
const Web = {
    //url:"",
    baseUrl:baseUrl,
    get:null,
    post:null,
    buildUrl:function (url) {
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
            defaultHandling:true
        };

        //深拷贝
        options = jQuery.extend(true, options, callbackOrOptions);

        //dataType数据类型
        options.dataType = options.dataType.toLowerCase();

        //默认回调处理
        if (options.dataType == "json" && options.defaultHandling){
            options.success = function (response,status,xhr) {
                if (!JsonUtils.isJson(response)) {
                    if (options.url.indexOf(".") == -1){
                        window.location.href = Web.baseUrl + "/index";
                        return;
                    }
                    return;
                }
                if (response.code == "000000"){

                }
                callbackOrOptions.success(response,status,xhr);
            };
            //insideCallback.error = callback.error;
        }
        return jQuery.ajax(options);
    };
});