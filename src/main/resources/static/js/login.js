var loginApp = new Vue({
    el: '#login-block',
    data: {
        baseUrl:baseUrl,
        username: null,
        password:null
    },
    methods:{
        login:function () {
            $.ajax({
                type: "post",
                url: this.baseUrl+"/public/login",
                data: {name:this.username,password:this.password},
                dataType: "json",
                success: function (data) {
                    if (data.code == "000000"){
                        window.location.href = loginApp.baseUrl + "/index";
                    }else {
                        alert('登陆失败！code：'+data.code+",message:"+data.message);
                    }
                },
                error: function (msg) {
                    alert("失败");
                }
            });
        }
    }
});