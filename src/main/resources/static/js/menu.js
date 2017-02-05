//todo 将menu数据存入数据库
var AppMenu = [
    {
        name:"首页",
        icon:"fa-cogs",
        url:"/"
    },
    {
        name:"资源管理",
        icon:"fa-cogs",
        list:[
            {name:"111111111111",url:"/bar"}
        ]
    },
    {
        name:"系统管理",
        icon:"fa-cogs",
        list:[
            {name:"用户管理",url:"/foo"},
            {name:"角色管理",url:"/bar"},
            // {name:"权限菜单",url:"#"},
            {name:"权限管理",url:"/foo"}
        ]
    }
];