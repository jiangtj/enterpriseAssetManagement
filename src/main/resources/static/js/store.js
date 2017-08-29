//列表
const Map = new Vue({
    data:{
        sessionPoint:[],
        selectedPoint:[]
    },
    computed:{
        point:function () {
            let self = this;
            return $.map(self.selectedPoint,function (id) {
                for (let i in self.sessionPoint){
                    if (self.sessionPoint[i].id === parseInt(id))
                        return {
                            "key":self.sessionPoint[i].id,
                            "value":self.sessionPoint[i].name
                        }
                }
            });
        },

        role:function () {
            return this.getMap("/public/map/role");
        },
        permission:function () {
            return this.getMap("/permission/getMap");
        },
        stockTake:function () {
            return this.getMap("/stockTake/getAvailableMap");
        },

        roleStatus:function () {
            return this.getMap("/public/map/dictionary/auth_role/status");
        },
        menuType:function () {
            return this.getMap("/public/map/dictionary/auth_menu/type");
        },
        assetStatus:function () {
            return this.getMap("/public/map/dictionary/assets_item/status");
        },
        stockTakeStatus:function () {
            return this.getMap("/public/map/dictionary/assets_stock_take/status");
        },
        stockTakeItemStatus:function () {
            return this.getMap("/public/map/dictionary/assets_stock_take_item/status");
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