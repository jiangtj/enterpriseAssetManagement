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
        logout:function () {
            Web.post("/public/logout",function () {
                Web.go("/login");
            })
        }
    }
});