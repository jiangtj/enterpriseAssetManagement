//ajax请求
const Web = {
    baseUrl:null,
    innerOptions:{
        dataType:"json",
        fail:function () {}
    },
    globalIntercepts:[],
    //url
    buildUrl:function (url) {
        if (url === undefined) return null;
        if (url.charAt(0) === "/"){
            if (url.charAt(1) === "/"){
                return "http:" + url;
            }
            return Web.baseUrl + url;
        }
        if (url.charAt(0) === "~"){
            if (url.charAt(1) === "/"){
                return url.substring(1);
            }
        }
        return url;
    },
    go:function (url) {
        window.location.href = Web.buildUrl(url);
    },
    setBaseUrl:function (url) {
        Web.baseUrl = url;
        return this;
    },
    isSuccess:function(obj){
        return obj.code.charAt(1) === "0";
    },
    //WebBuilder
    setDefault:function (options) {
        jQuery.extend(true,Web.innerOptions,options);
        return this;
    },
    addIntercept:function (intercept) {
        Web.globalIntercepts.push(intercept);
        return this;
    },
    //数据
    updateDate:function (data) {
        jQuery.each(data,function (key,value) {
            Web.updateObject(key,value,data);
        });
        return data;
    },
    updateObject:function (key,value,content) {
        if (value === null) content[key] = undefined;
        if (typeof(value) === "object" && Object.prototype.toString.call(value).toLowerCase() === "[object object]" && !value.length){
            jQuery.each(value,function (x,y) {
                Web.updateObject(key+"."+x,y,content);
                if (content[key] === {}){
                    content[key+"."+x] = undefined;
                }else {
                    content[key+"."+x] = value[x];
                }
                value[x] = undefined;
            });
        }
    }
};

function WebBuilder(url,defaultOptions) {
    this.defaultOptions = defaultOptions;
    if (!this.defaultOptions) this.defaultOptions = {};
    this.defaultOptions.url = Web.buildUrl(url);
    if (!this.defaultOptions.intercepts) this.defaultOptions.intercepts = [];
    this.options = $.extend(true,{},this.defaultOptions);
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
        //string
        if (Object.prototype.toString.call(data) === "[object String]"){
            this.options.data = data;
            return this;
        }
        //json
        Web.updateDate(data);
        this.options.data =data;
        return this;
    },
    setMethod:function (method) {
        this.options.type = method;
        return this;
    },
    setType:function (daraType) {
        this.options.dataType = daraType;
        return this;
    },
    setAsync:function (async) {
        this.options.async = async;
        return this;
    },
    addIntercept:function (intercept) {
        this.options.intercepts.push(intercept);
        return this;
    },
    //被拦截进入
    setFail:function (callback) {
        this.options.fail = callback;
        return this;
    },
    setSuccess:function (callback) {
        this.options.success = callback;
        return this;
    },
    setError:function (callback) {
        this.options.error = callback;
        return this;
    },
    path:function (map) {
        for (let key in map){
            let reg = new RegExp("{"+key+"}","g");
            this.options.url=this.options.url.replace(reg,map[key]);
        }
        return this;
    }
});
jQuery.each(['get','put','post','delete','execute'],function (i,method) {
    WebBuilder.prototype[method] = function (callback) {

        let tempOptions = jQuery.extend(true,{},Web.innerOptions,this.defaultOptions,this.options);
        if (method !== 'execute') tempOptions.type = method;
        if (jQuery.isFunction(callback)){
            tempOptions.success = callback;
        }else {
            jQuery.extend(true,tempOptions,callback);
        }

        let tempSuccess = tempOptions.success;
        tempOptions.success = function (request, status, thrown) {
            for (let i in Web.globalIntercepts) {
                if (Web.globalIntercepts[i](request, status, thrown,tempOptions)) {
                    tempOptions.fail(request, status, thrown,tempOptions);
                    return;
                }
            }
            for (let i in tempOptions.intercepts) {
                if (tempOptions.intercepts[i](request, status, thrown,tempOptions)) {
                    tempOptions.fail(request, status, thrown,tempOptions);
                    return;
                }
            }
            tempSuccess(request, status, thrown);
        };

        this.options = $.extend(true,{},this.defaultOptions);

        return jQuery.ajax(tempOptions);
    }
});