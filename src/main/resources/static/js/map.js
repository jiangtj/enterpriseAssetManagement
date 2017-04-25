//列表
const Map = new Vue({
    computed:{
        role:function () {
            return this.getMap("/public/map/role");
        },
        permission:function () {
            return this.getMap("/permission/getMap");
        },

        roleStatus:function () {
            return this.getMap("/public/map/dictionary/auth_role/status");
        },
        menuType:function () {
            return this.getMap("/public/map/dictionary/auth_menu/type");
        }
    },
    methods:{
        getMap:function (url) {
            let list = [];
            new WebBuilder(url)
                .setAsync(false)
                .get(function (data) {
                    if (Web.isSuccess(data)){
                        list = data.object;
                    }else {
                        ToastrUtils.showResult(data);
                    }
                });
            return list;
        }
    }
});