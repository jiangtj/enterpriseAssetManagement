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
    }
};

jQuery.each( [ "get", "post" ], function( i, method ) {
    Web[ method ] = function( url, data, callback, dataType ,type) {
        url = Web.buildUrl(url);
        if ( jQuery.isFunction( data ) ) {
            type = dataType || type;
            dataType = callback;
            callback = data;
            data = undefined;
        }
        return jQuery.ajax({
            url: url,
            type: method,
            dataType: dataType,
            data: data,
            success: callback
        });
    };
});