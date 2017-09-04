const loginApp = new Vue({
    el: '#login-block',
    data: {
        baseUrl:baseUrl,
        username: null,
        password:null
    },
    methods:{
        login:function () {
            let now=new Date();
            let time = parseInt(now.getTime()/1000);
            let MD5Password = hex_md5(this.password+time);
            new WebBuilder("/public/login")
                .addIntercept(defaultIntercept)
                .body({
                    username:this.username,
                    password:MD5Password,
                    loginTime:time
                })
                .post(function (data) {
                    Web.go("/index");
                });
        }
    }
});