//todo 将menu数据存入数据库
const AppMenu = [
    {
        no:"Home",
        name:"首页",
        icon:"fa-dashboard",
        url:"/",
        staticUrl:"/model/index.vue"
    },
    {
        no:"RRR",
        name:"资源管理",
        icon:"fa-th-large",
        list:[
            {no:"ac",name:"111111111111",url:"/bar",staticUrl:"/model/xxxx/aaa.vue"}
        ]
    },
    {
        no:"XXX",
        name:"报表分析",
        icon:"fa-pie-chart",
        list:[
            {no:"sa",name:"22222",url:"/bar",staticUrl:"/model/xxxx/aaa.vue"}
        ]
    },
    {
        no:"System",
        name:"系统管理",
        icon:"fa-cogs",
        list:[
            {no:"User",name:"用户管理",url:"/system/user",staticUrl:"/model/system/user.vue"},
            {no:"Role",name:"角色管理",url:"/system/role",staticUrl:"/model/system/role.vue"},
            // {name:"权限菜单",url:"#",staticUrl:"/model/system/aaa.vue"},
            {no:"Permission",name:"权限管理",url:"/system/permission",staticUrl:"/model/system/permission.vue"}
        ]
    }
];