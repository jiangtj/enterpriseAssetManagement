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
                query:[],
                edit:[]
            }
        },
        menu:AppMenu,
        alwaysTrue:true,
        selectedPoint:[]
    },
    computed:{
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
        showMoreInfo:function () {
            $("#user-more-info").modal("show");
        },
        updateSidebarTree:function () {
            let self = this;
            Server.point.getQueryRootPoint.execute(data => {
                self.point.root.query = data.object;
                let temps = $.extend(true,[],data.object);
                self.changeListTreeToList(temps);
                $.each(Map.sessionPoint,function (index,item) {
                    if (item.id === self.user.pointId){
                        item.state = {};
                        item.state.selected = true;
                    }
                });
                self.changeListTreeForJsTree(temps);

                $('#sidebar-point-tree').jstree({
                    'core' : {
                        'data' : temps
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
        changeListTreeToList:function (array) {
            let self = this;
            if (!array) return;
            $.each(array,function (index,item) {
                if (item) {
                    Map.sessionPoint.push(item);
                    self.changeListTreeToList(item.nodes);
                }
            })
        },
        changeListTreeForJsTree:function (items) {
            let self = this;
            if (!items) return;
            $.each(items,function (index,item) {
                item.text = item.name;
                item.icon = 'fa fa-folder';
                item.children = item.nodes;
                self.changeListTreeForJsTree(item.nodes);
            })
        }
    }
});