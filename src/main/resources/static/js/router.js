//定义路由堆
const CustomRoutes = {
    "dsc":{
        menu: "2",
        path: "/ll/f",
        name:"dsc",
        data:{}
    }
};

//定义menu路由
const MenuRoutes = {};

//添加路由对象
function pushMenuRoute(key,item){
    var object = {
        menu: item,
        path: item.url,
        name:key,
        data:{},
        component: function (resolve) {
            var url = item.staticUrl;//获取url
            var view = "";
            Web.get(url,{
                async: false,
                dataType: "text",
                success: function (data) {
                    view = data;
                }
            });
            resolve({
                template:view,
                data:function () {
                    return object.data;
                }
            });
        }
    };
    MenuRoutes[key] = item;
}

//通过菜单添加路由
function pushMenuRoutes(items) {
    for (var i = 0; i < items.length ; i++){
        var item = items[i];
        if (item.list != null){
            pushMenuRoutes(item.list);
        }
        if (item.url != null) {
            pushMenuRoute(item.no,item)
        }
    }
}
pushMenuRoutes(AppMenu);

//路由对象堆
const Routes = jQuery.extend(true,MenuRoutes,CustomRoutes);
//路由对象工具类
const RouteUtils = {
    get:function (key) {
        return Routes[key];
    },
    set:function (item) {
        Routes[item.name] = item;
    }
};

//路由构造者
const Router = new VueRouter({
    routes:Routes
});

//更新菜单class
function updateMenuStatus(hook,status) {
    RouteUtils.get(hook.name);
    for (var i = 0 ; i < routes.length ; i++){
        if (routes[i].path == route.fullPath){
            routes[i].menu.isActive = status;
            return;
        }
    }
}

//路由钩子
router.beforeEach(function(to, from, next){
    //更新菜单样式
    updateMenuStatus(from,false);
    updateMenuStatus(to,true);
    //默认头部标签
    headerLabel.setDefault();
    //通过
    next();
});