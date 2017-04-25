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
        let toastrMethod;
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
        let level = Number(obj.code.charAt(1));
        ToastrUtils.show(obj.title,obj.message,level);
        if (level >= 4) {
            console.log("code:"+obj.code+",title:"+obj.title+",message:"+obj.message);
        }
    }
};
ToastrUtils.defaultConfig();

//SweetAlert弹出框
const SweetAlertUtils = {
    show:function (title,text) {

        let content;

        if (title === undefined){
            content = {};
        }else {
            content = JsonUtils.isJson(title)?title:{
                "title":title,
                "text":text
            };
        }

        let option = jQuery.extend(true,{
            title: "是否确定?",
            text: "你当前的操作属于威胁操作!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            closeOnConfirm: true
        },content);

        let button = {
            sure:function () {
                swal("成功!", "操作成功.", "success");
            },
            cancel:function () {
                //swal("取消!", "您取消了当前操作.", "success");
            }
        };

        swal(option, function (isConfirm) {
            isConfirm?button.sure():button.cancel();
        });

        let returnObj = {
            sure:function (func) {
                button.sure = func;
                return returnObj;
            },
            cancel:function (func) {
                button.cancel = func;
                return returnObj;
            }
        };

        return returnObj;
    }
};

//json
const JsonUtils = {
    isJson:function (data) {
        return typeof(data) === "object" && Object.prototype.toString.call(data).toLowerCase() === "[object object]"
            && !data.length;
    },
    copy:function (obj) {
        return jQuery.extend(true,{},obj);
    },
    setNull:function (obj) {
        let copyObj = jQuery.extend(true,{},obj);
        jQuery.each(copyObj,function (key,value) {
            if (JsonUtils.isJson(value)){
                copyObj[key] = JsonUtils.setNull(value);
            }else {
                copyObj[key] = null;
            }
        });
        return copyObj;
    },
    clear:function (obj) {
        let length = arguments.length;
        for (let i =1; i<length; i++){
            obj[arguments[i]] = undefined;
        }
    }
};

//Modal
function ModalBuilder(position) {
    this.obj = $(position);
    if (this.option !== undefined){
        this.obj.modal(this.option);
    }
}
ModalBuilder.option = {
    keyboard: false
};
ModalBuilder.prototype.show = function () {
    this.obj.modal("show");
    return this;
};
ModalBuilder.prototype.hide = function () {
    this.obj.modal("hide");
    return this;
};
ModalBuilder.prototype.set = function (option) {
    this.obj.modal(option);
    return this;
};

//校验
const ValidationUtils = {
    check:function (position) {
        let flag = true;
        $(position).each(function(){
            let item = $(this);
            item.validate({
                submitHandler: function () {
                }
            });
            item.submit();
            flag = item.valid() && flag;
        });
        return flag;
    }
};
//修改提醒为中文
jQuery.extend(jQuery.validator.messages, {
    required: "这是必填字段",
    remote: "请修正此字段",
    email: "请输入有效的电子邮件地址",
    url: "请输入有效的网址",
    date: "请输入有效的日期",
    dateISO: "请输入有效的日期 (YYYY-MM-DD)",
    number: "请输入有效的数字",
    digits: "只能输入数字",
    creditcard: "请输入有效的信用卡号码",
    equalTo: "你的输入不相同",
    extension: "请输入有效的后缀",
    maxlength: $.validator.format("最多可以输入 {0} 个字符"),
    minlength: $.validator.format("最少要输入 {0} 个字符"),
    rangelength: $.validator.format("请输入长度在 {0} 到 {1} 之间的字符串"),
    range: $.validator.format("请输入范围在 {0} 到 {1} 之间的数值"),
    max: $.validator.format("请输入不大于 {0} 的数值"),
    min: $.validator.format("请输入不小于 {0} 的数值")
});