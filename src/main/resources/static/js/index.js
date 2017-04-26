//单页配置
const App = new Vue({
    el:"#wrapper",
    router:AppRouter,
    data:{
        baseUrl:baseUrl,
        user: sessionUser,
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
        }
    }
});