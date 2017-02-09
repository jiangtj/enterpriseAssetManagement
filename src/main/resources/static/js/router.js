//定义路由堆
const routes = [];

//定义路由数据
const RouteData={
    tableDefaultData:[
        {id:1,name:"ss",password:"s"},
        {id:1,name:"ss1",password:"s"},
        {id:1,name:"ss2",password:"s"}
    ],
    tableDefaultData2:{
        title:{
            id:"id",
            name:"名称",
            password:"密码"
        },
        data:[
            {id:1,name:"ss",password:"s"},
            {id:1,name:"ss1",password:"s"},
            {id:1,name:"ss2",password:"s"}
        ]
    },
    items:[{text:"wqwd"},{text:"s"}]
};

//添加路由对象
function pushRoute(item){
    var object = {
        menu: item,
        path: item.url,
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
                    return RouteData;
                }
            });
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
    //更新菜单样式
    updateMenuStatus(from,false);
    updateMenuStatus(to,true);
    //默认头部标签
    headerLabel.setDefault();
    //通过
    next();
});