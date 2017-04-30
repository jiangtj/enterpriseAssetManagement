//单页配置
const App = new Vue({
    el:"#wrapper",
    router:AppRouter,
    data:{
        baseUrl:baseUrl,
        user: sessionUser,
        permission: sessionPermission,
        menu:AppMenu,
        alwaysTrue:true
    },
    computed:{
        menuLevel1:function(){
            return this.getMenuLevelData(1)
        },
        menuLevel2:function(){
            return this.getMenuLevelData(2)
        }
    },
    methods:{
        logout:function () {
            new WebBuilder("/public/logout").post(function () {
                Web.go("/login");
            });
        },
        getMenuLevelData:function (level) {
            return $.map(this.menu, function (item) {
                if (item.level === level) return item;
            });
        },
        hasNextLevelMenu:function (id) {
            for (let i in this.menu){
                if (this.menu[i].pid === id) return true;
            }
            return false;
        },
        getNextMenu:function (id) {
            return $.map(this.menu, function (item) {
                if (item.pid === id) return item;
            });
        },
        updateTree:function () {
            let self = this;
            $('#sidebar-point-tree').jstree({
                'core' : {
                    'data' :function (node,callback) {
                        Server.point.getPublicPoint.setData({
                            pid:node.id==="#"?0:node.id
                        }).post(data => {
                            let list = $.map(data.object,(item,index) => {
                                item.parent = item.pid===0?"#":item.pid;
                                item.text = item.name;
                                item.icon = 'fa fa-folder';
                                return item;
                            });
                            callback.call(this,list)
                        });
                    }
                }
            }).on('changed.jstree', function (e, data) {
                self.conditions.pid = self.conditions.pid === data.node.id ? null : data.node.id;
                self.pname = data.node.text;
                self.getTableList();
            });
        }
    }
});