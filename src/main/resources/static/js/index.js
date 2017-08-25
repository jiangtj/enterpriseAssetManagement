//单页配置
const App = new Vue({
    el:"#wrapper",
    router:AppRouter,
    data:{
        baseUrl:baseUrl,
        user: sessionUser,
        permission: sessionPermission,
        point:{
            root:{
                query:null,
                edit:null
            }
        },
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
        let self = this;
        if (this.user.pointId) {
            this.selectedPoint.push(this.user.pointId);
            Map.selectedPoint.push(this.user.pointId);
            this.point.root.edit = this.user.pointId;
        }
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
            Server.point.getQueryRootPoint.post(data => {
                self.point.root.query = data.object;
                let temps = $.extend(true,{},data.object);
                self.changeForJsTree(temps);

                $('#sidebar-point-tree').jstree({
                    'core' : {
                        'data' : [temps]
                            /*function (node,callback) {
                            Server.point.getPointByPid.setData({
                                pid:node.id==="#"?self.point.root.query.id:node.id
                            }).post(data => {
                                let list = $.map(data.object,(item,index) => {

                                    if (item.id === item.pid) return undefined;

                                    item.parent = item.pid===self.point.root.query.id?"#":item.pid;
                                    item.text = item.name;
                                    item.children = true;
                                    item.icon = 'fa fa-folder';
                                    return item;
                                });
                                callback.call(this,list)
                            });
                        }*/
                    },
                    "checkbox" : {
                        "keep_selected_style" : false,
                        "three_state":false
                    },
                    "plugins": ["checkbox"]
                }).on('changed.jstree', function (e, data) {
                    self.selectedPoint = data.selected;
                    Map.selectedPoint = data.selected;
                });

            });
        },
        changeForJsTree:function (item) {
            let self = this;
            if (!item) return;
            item.text = item.name;
            item.icon = 'fa fa-folder';
            item.children = item.nodes;
            $.each(item.nodes,function (index,item) {
                self.changeForJsTree(item);
            })
        }
    }
});