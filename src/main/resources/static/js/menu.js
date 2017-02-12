//todo 将menu数据存入数据库
const AppMenu = [
    {
        menu:"Home",
        name:"首页",
        icon:"fa-dashboard",
        url:"/",
        staticUrl:"/model/index.vue"
    },
    {
        menu:"RRR",
        name:"资源管理",
        icon:"fa-th-large",
        list:[
            {menu:"ac",name:"111111111111",url:"/bar",staticUrl:"/model/xxxx/aaa.vue"}
        ]
    },
    {
        menu:"XXX",
        name:"报表分析",
        icon:"fa-pie-chart",
        list:[
            {menu:"sa",name:"22222",url:"/bar",staticUrl:"/model/xxxx/aaa.vue"}
        ]
    },
    {
        menu:"System",
        name:"系统管理",
        icon:"fa-cogs",
        list:[
            {menu:"User",name:"用户管理",url:"/system/user",staticUrl:"/model/system/user.vue"},
            {menu:"Role",name:"角色管理",url:"/system/role",staticUrl:"/model/system/role.vue"},
            // {name:"权限菜单",url:"#",staticUrl:"/model/system/aaa.vue"},
            {menu:"Permission",name:"权限管理",url:"/system/permission",staticUrl:"/model/system/permission.vue"}
        ]
    }
];

//定义路由
const MenuRoutes = {};

//菜单工具类
const MenuUtils = {
    pushMenuRoute: function (key, item) {
        MenuRoutes[key] = item;
    },
    pushMenuRoutes: function (menus) {
        for (var i = 0; i < menus.length; i++) {
            var item = menus[i];
            if (item.list != null) {
                MenuUtils.pushMenuRoutes(item.list);
            }
            if (item.url != null) {
                MenuUtils.pushMenuRoute(item.menu, item)
            }
        }
    }
};

//通过菜单添加路由
MenuUtils.pushMenuRoutes(AppMenu);