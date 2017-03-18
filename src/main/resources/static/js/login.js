const loginApp = new Vue({
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
            var MD5Password = hex_md5(this.password+time);
            new WebBuilder("/public/login").setData({
                name:this.username,
                password:MD5Password,
                time:time
            }).post(function (data) {
                Web.go("/index");
            });
        }
    }
});