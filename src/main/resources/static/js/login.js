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
            let time = now.getTime();
            let MD5Password = hex_md5(this.password+time);
            new WebBuilder("/public/login")
                .setIntercepts(defaultIntercept)
                .setData({
                    name:this.username,
                    password:MD5Password,
                    time:time
                })
                .post(function (data) {
                    Web.go("/index");
                });
        }
    }
});