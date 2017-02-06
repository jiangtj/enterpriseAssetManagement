//定义路由堆
const routes = [];

//添加路由对象
function pushRoute(item){
    var object = {
        menu: item,
        path: item.url,
        component: function (resolve) {
            var url = item.staticUrl;//获取url
            var view = "";
            $.ajax({
                type: "get",
                url:url,
                async: false,
                dataType: "text",
                success: function (data) {
                    view = data;
                }
            });
            resolve({
                template:view
            })
        }
    };
    routes.push(object);
}

//通过菜单添加路由
function getRoutes(items) {
    for (var i = 0; i < items.length ; i++){
        var item = items[i];
        if (item.list != null){
            getRoutes(item.list);
        }
        if (item.url != null) {
            pushRoute(item)
        }
    }
}
getRoutes(AppMenu);

//配置路由
const router = new VueRouter({
    routes:routes
});

//更新菜单class
function updateMenuStatus(route,status) {
    for (var i = 0 ; i < routes.length ; i++){
        if (routes[i].path == route.fullPath){
            routes[i].menu.isActive = status;
            return;
        }
    }
}

//路由钩子
router.beforeEach(function(to, from, next){
    updateMenuStatus(from,false);
    updateMenuStatus(to,true);
    next();
});

//单页配置
var App = new Vue({
    el:"#wrapper",
    router:router,
    data:{
        baseUrl:baseUrl,
        user: sessionUser,
        menu:AppMenu,
        alwaysTrue:true
    }
});