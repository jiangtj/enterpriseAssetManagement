const AppMenu = [
    {
        code:"Home",
        name:"首页",
        icon:"fa-dashboard",
        url:"/",
        staticUrl:"/model/index.vue"
    },
    {
        code:"Asset",
        name:"资源管理",
        permission:"asset",
        icon:"fa-th-large",
        list:[
            {name:"资产添加",permission:"asset:add",url:"/asset/add",staticUrl:"/model/asset/add.vue"},
            {name:"资产列表",permission:"asset:getList",url:"/asset/get",staticUrl:"/model/asset/get.vue"},
            {name:"借还登记",permission:"asset:borrow:operation",url:"/asset/borrow",staticUrl:"/model/asset/borrow.vue"}
        ]
    },
    {
        code:"Report",
        name:"报表分析",
        permission:"report",
        icon:"fa-pie-chart",
        list:[
            {name:"资产总揽",permission:"report:overall",url:"/report/overall",staticUrl:"/model/report/overall.vue"},
            {name:"借还报表",permission:"report:borrow",url:"/report/borrow",staticUrl:"/model/report/borrow.vue"}
        ]
    },
    {
        code:"StockTake",
        name:"资产盘点",
        permission:"stockTake",
        icon:"fa-certificate",
        list:[
            {name:"任务",permission:"stockTake:getList",url:"/stockTake/task",staticUrl:"/model/stockTake/task.vue"},
            {name:"明细",permission:"stockTake:item:getList",url:"/stockTake/item",staticUrl:"/model/stockTake/item.vue"},
            {name:"任务",permission:"stockTake:handle",url:"/stockTake/handle",staticUrl:"/model/stockTake/handle.vue"}
        ]
    },
    {
        code:"System",
        name:"系统管理",
        permission:["sys","system-administrator-permission"],
        logical:Logical.OR,
        icon:"fa-cogs",
        list:[
            {name:"用户管理",permission:["sys:user","system-administrator-permission"],logical:Logical.OR,url:"/system/user",staticUrl:"/model/system/user.vue"},
            {name:"角色管理",role:"system-administrator-role",url:"/system/role",staticUrl:"/model/system/role.vue"},
            {name:"权限管理",role:"system-administrator-role",permission:"sys:develop",url:"/system/permission",staticUrl:"/model/system/permission.vue"},
            {name:"资源类型",permission:"sys:assetType",url:"/system/assetType",staticUrl:"/model/system/assetType.vue"},
            {name:"网点管理",permission:["sys:point","system-administrator-permission"],logical:Logical.OR,url:"/system/point",staticUrl:"/model/system/point.vue"},
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
        for (let i = 0; i < menus.length; i++) {
            let item = menus[i];
            item.isActive = false;
            if (item.url !== undefined && item.url !== null && item.url !== "") {
                if (!item.code) item.code = "menu#" + item.url;
                MenuUtils.pushMenuRoute(item.code, item)
            }
            if (item.list !== undefined) MenuUtils.pushMenuRoutes(item.list);
        }
    }
};

//通过菜单添加路由
MenuUtils.pushMenuRoutes(AppMenu);