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
            Web.get(url,{
                defaultHandling:false,
                async: false,
                success:function (data) {
                    if (Web.isSuccess(data)){
                        list = data.object;
                    }else {
                        ToastrUtils.showResult(data);
                    }
                }
            });
            return list;
        }
    }
});