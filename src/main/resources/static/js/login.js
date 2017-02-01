var loginApp = new Vue({
    el: '#login-block',
    data: {
        username: null,
        password:null
    },
    methods:{
        login:function () {
            $.ajax({
                type: "post",
                url: "/public/login",
                data: {name:this.username,password:this.password},
                dataType: "json",
                success: function (data) {
                    if (data.code == "000000"){
                        window.location.href = "/index";
                    }else {
                        alert('登陆失败！code：'+data.code+",message:"+data.message);
                    }
                },
                error: function (msg) {
                    alert(msg);
                }
            });
        }
    }
});