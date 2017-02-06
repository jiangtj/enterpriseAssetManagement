
const routes = [];

function pushRoute(item){
    var object = {
        menu: item,
        path: item.url,
        component: function (resolve) {
            //var obj = object;//对象引用
            var url = item.staticUrl;//获取url
            //alert(obj.menu.name);
            var view = "";
            $.ajax({
                type: "get",
                //url: obj.menu.staticUrl,
                url:url,
                async: false,
                dataType: "text",
                success: function (data) {
                    view = data;
                }
            });
            resolve({
                template:view
            })
        }
    };
    routes.push(object);
}

function getRoutes(items) {
    for (var i = 0; i < items.length ; i++){
        var item = items[i];
        if (item.list != null){
            getRoutes(item.list);
        }
        if (item.url != null) {
            pushRoute(item)
        }
    }
}

getRoutes(AppMenu);

const router = new VueRouter({
    routes:routes
});

var App = new Vue({
    el:"#wrapper",
    router:router,
    data:{
        baseUrl:baseUrl,
        user: sessionUser,
        menu:AppMenu,
        alwaysTrue:true
    }
});