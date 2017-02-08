//单页配置
var App = new Vue({
    el:"#wrapper",
    router:router,
    data:{
        baseUrl:baseUrl,
        user: sessionUser,
        menu:AppMenu,
        alwaysTrue:true,
        tableDefaultData:[
            {id:1,name:"ss",password:"s"},
            {id:1,name:"ss1",password:"s"},
            {id:1,name:"ss2",password:"s"}
        ]
    }
});