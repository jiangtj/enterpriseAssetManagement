var loginApp = new Vue({
    el: '#login-block',
    data: {
        baseUrl:baseUrl,
        username: null,
        password:null
    },
    methods:{
        login:function () {
            Web.post("/public/login",{
                name:this.username,
                password:this.password
            },function (data) {
                if (Web.isSuccess(data)){
                    Web.go("/index")
                }
            });
        }
    }
});