//定义路由堆
const CustomRoutes = {
    "dsc":{
        menu:"Home",
        name:"思考每次是",
        url:"/sl/sac/sa",
        staticUrl:"/model/index.vue"
    }
};

//定义路由
const Routes = jQuery.extend(true,{},MenuRoutes,CustomRoutes);
//定义app路由,vue内部调用
const AppRoutes = [];
//定义全局路由对象
var Route = null;
var RouteConfig = {};

//路由对象工具类
const RouteUtils = {
    get:function (key) {
        return Routes[key];
    },
    put:function (key,item) {
        Routes[key] = item;
    },
    transformToAppRoutes:function () {
        for (var i in Routes){
            RouteUtils.putAppRoute(i,Routes[i]);
        }
    },
    putAppRoute:function (name,item) {
        var object = {
            path: item.url,
            name:name,
            data:{},
            component: function (resolve) {
                var url = item.staticUrl;//获取url
                var tempText = "";
                //动态获取静态模板
                new WebBuilder(url).setAsync(false).setType("text").get(function (data) {
                    tempText = data;
                });

                //获取view
                var tempIndex = tempText.indexOf("template");
                var templateStart = tempText.indexOf(">",tempIndex)+1;
                var templateEnd = tempText.lastIndexOf("template")-2;
                var view = tempText.substring(templateStart,templateEnd);

                //获取js
                RouteConfig = {};
                tempIndex = tempText.indexOf("script",templateEnd);
                if (tempIndex !== -1){
                    var scriptStart = tempText.indexOf(">",tempIndex)+1;
                    var scriptEnd = tempText.indexOf("script",scriptStart)-2;
                    var js = tempText.substring(scriptStart,scriptEnd);
                    eval(js);
                }

                var option = {
                    template:view,
                    data:function () {
                        return object.data;
                    }
                };
                option = jQuery.extend(true,option,RouteConfig);
                resolve(option);
            }
        };
        AppRoutes.push(object);
    }
};

//路由对象工具类转为
RouteUtils.transformToAppRoutes(Routes);

//路由构造者
const AppRouter = new VueRouter({
    routes:AppRoutes
});

//更新菜单class
function updateMenuStatus(hook,status) {
    if (hook.name === null) return;
    var route = Routes[hook.name];
    if (route.menu === undefined) return;
    var menu = MenuRoutes[route.menu];
    menu.isActive = status;
}

//路由钩子
AppRouter.beforeEach(function(to, from, next){
    //更新菜单样式
    updateMenuStatus(from,false);
    updateMenuStatus(to,true);
    //更改Route对象
    Route = Routes[to.name];
    //通过
    next();
});