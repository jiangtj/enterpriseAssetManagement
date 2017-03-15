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
        },
        getMap:function (url) {
            var list = [];
            Web.get(url,{
                defaultHandling:false,
                async: false,
                success:function (data) {
                    if (Web.isSuccess(data)){
                        list = data.object;
                    }else {
                        ToastrUtils.showResult(data);
                    }
                }
            });
            return list;
        }
    },
    computed:{
        roleMap:function () {
            return this.getMap("/public/map/role");
        }
    }
});