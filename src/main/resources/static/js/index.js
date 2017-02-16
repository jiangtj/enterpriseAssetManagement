//单页配置
var App = new Vue({
    el:"#wrapper",
    router:AppRouter,
    data:{
        baseUrl:baseUrl,
        user: sessionUser,
        menu:AppMenu,
        alwaysTrue:true
    },
    methods:{
        loginOut:function () {
            debugger;
            Web.post("/public/loginOut",function () {
                Web.go("/loginPage");
            })
        }
    }
});