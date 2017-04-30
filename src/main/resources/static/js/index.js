//单页配置
const App = new Vue({
    el:"#wrapper",
    router:AppRouter,
    data:{
        baseUrl:baseUrl,
        user: sessionUser,
        permission: sessionPermission,
        point: sessionPoint,
        menu:AppMenu,
        alwaysTrue:true,
        selectedPoint:[]
    },
    computed:{
        menuLevel1:function(){
            return this.getMenuLevelData(1)
        },
        menuLevel2:function(){
            return this.getMenuLevelData(2)
        }
    },
    created:function () {
        if (this.user.pointId) this.selectedPoint.push(this.user.pointId);
    },
    mounted:function () {
        this.updateSidebarTree();
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
        updateSidebarTree:function () {
            let self = this;
            $('#sidebar-point-tree').jstree({
                'core' : {
                    'data' :function (node,callback) {
                        /*Server.point.getPublicPoint.setData({
                            pid:node.id==="#"?0:node.id
                        }).post(data => {
                        });*/
                        //todo 根节点节点判断有误，待修复
                        let max=99;
                        $.each(self.point,function (index,item) {
                            if (max > item.level) max = item.level;
                        });
                        let list = $.map(self.point,(item,index) => {
                            item.parent = item.level===max?"#":item.pid;
                            item.text = item.name;
                            item.icon = 'fa fa-folder';
                            if ($.inArray(item.id,self.selectedPoint) !== -1){
                                item.state = {};
                                item.state.selected = true;
                            }
                            return item;
                        });
                        callback.call(this,list);
                    }
                },
                "checkbox" : {
                    "keep_selected_style" : false,
                    "three_state":false
                },
                "plugins": ["checkbox"]
            }).on('changed.jstree', function (e, data) {
                self.selectedPoint = data.selected;
            });
        }
    }
});