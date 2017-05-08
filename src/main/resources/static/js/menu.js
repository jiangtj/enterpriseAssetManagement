//todo 将menu数据存入数据库
/*const AppMenu = [
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
            {menu:"Permission",name:"权限管理",url:"/system/permission",staticUrl:"/model/system/permission.vue"},
            {menu:"Menu",name:"菜单管理",url:"/system/menu",staticUrl:"/model/system/menu.vue"},
        ]
    }
];*/

const Permission = {
    superRole:(sessionUser.id === 1),
    source:sessionPermission,
    name:{},
    url:{},
    put:function (key,value) {
        Permission[key][value] = true;
    },
    checked:function (id) {
        for (let i in Permission.source){
            if (Permission.source[i].id === id) return true;
        }
        return false;
    },
    hasName:function (name) {
        return Permission.name[name]||Permission.superRole
    }
};
jQuery.each(sessionPermission,function (item) {
    Permission.put("name",item.name);
    Permission.put("url",item.url);
});

function getAppMenu() {
    let innerData = null;
    Server.menu.getPublicMenu.setAsync(false).post(data => {
        innerData = data.object;
    });
    return innerData;
}

const AppMenu = getAppMenu();

if (sessionUser.id !== 1){
    for (let i = 0; i < AppMenu.length; i++) {
        let item = AppMenu[i];
        if (item.permissionId !== undefined && item.permissionId !== null){
            if (!Permission.checked(item.permissionId)){
                AppMenu.splice(i,1);
                i--;
            }
        }
    }
}

//定义路由
const MenuRoutes = {};

//菜单工具类
const MenuUtils = {
    pushMenuRoute: function (key, item) {
        MenuRoutes[key] = item;
    },
    pushMenuRoutes: function (menus) {
        for (let i = 0; i < menus.length; i++) {
            let item = menus[i];
            /*if (item.list !== undefined) {
                MenuUtils.pushMenuRoutes(item.list);
            }*/
            if (item.url !== undefined && item.url !== null && item.url !== "") {
                MenuUtils.pushMenuRoute(item.menu, item)
            }
        }
    }
};

//通过菜单添加路由
MenuUtils.pushMenuRoutes(AppMenu);