//弹出框
const ToastrUtils = {
    defaultConfig:function () {
        toastr.options = {
            "closeButton": true,
            "debug": false,
            "progressBar": true,
            "preventDuplicates": false,
            "positionClass": "toast-top-right",
            "onclick": null,
            "showDuration": "400",
            "hideDuration": "1000",
            "timeOut": "5000",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        };
    },
    show:function (title,msg,level) {
        level = level||1;
        var toastrMethod;
        switch (level){
            case 0:toastrMethod = "success";break;
            case 1:
            case 2:
            case 3:toastrMethod = "info";break;
            case 4:
            case 5:
            case 6:toastrMethod = "warning";break;
            case 7:
            case 8:
            case 9:toastrMethod = "error";break;
        }
        toastr[toastrMethod](msg,title);
    },
    showResult:function (obj) {
        var level = Number(obj.code.charAt(1));
        ToastrUtils.show(obj.title,obj.message,level);
        if (level >= 4) {
            console.log("code:"+obj.code+",title:"+obj.title+",message:"+obj.message);
        }
    }
};
ToastrUtils.defaultConfig();

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
    },
    isSuccess:function(obj){
        return obj.code.charAt(1) == "0";
    },
    go:function (url) {
        window.location.href = Web.buildUrl(url);
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

        //深拷贝
        options = jQuery.extend(true, options, callbackOrOptions);

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
                    callbackOrOptions.success(response,status,xhr);
                    return;
                }
                ToastrUtils.showResult(response);
                callbackOrOptions.success(response,status,xhr);
            };
        }
        return jQuery.ajax(options);
    };
});

const ValidationUtils = {
    check:function (position) {
        $(position).each(function(){
            var item = $(this);
            item.validate({
                onkeyup:true,
                submitHandler: function () {
                }
            });
            item.submit();
        });
    },
    show:function (position) {
        $(position).each(function(){
            var item = $(this);
            item.validate({
                onkeyup:false,
                submitHandler: function () {
                },
                errorPlacement: function (error, element) {
                },
                showErrors:function(errorMap,errorList) {
                    if (errorList.length == 0) return;
                    var temp ='[' + errorList[0].method + ']' + errorList[0].message;
                    ToastrUtils.show("提醒",temp,5);
                    this.defaultShowErrors();
                }
            });
            item.submit();
        });
    }
};