var loginApp = new Vue({
    el: '#login-block',
    data: {
        baseUrl:baseUrl,
        username: null,
        password:null
    },
    methods:{
        login:function () {
            var now=new Date();
            var time = now.getTime();
            //var MD5Password = $.md5(this.password+time);
            var MD5Password = hex_md5(this.password+time);
            Web.post("/public/login",{
                name:this.username,
                password:MD5Password,
                time:time
            },function (data) {
                if (Web.isSuccess(data)){
                    Web.go("/index")
                }
            });
        }
    }
});