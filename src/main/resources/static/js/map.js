//列表
const Map = new Vue({
    computed:{
        role:function () {
            return this.getMap("/public/map/role");
        }
    },
    methods:{
        getMap:function (url) {
            var list = [];
            new WebBuilder(url)
                .setDefaultHandling(false)
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