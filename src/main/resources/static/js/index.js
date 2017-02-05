
const Foo = { template: '<div>foo</div>' }
const Bar = { template: '<div>bar</div>' }

const routes = [
    { path: '/', component:{ template: '<div>1111111</div>' } },
    { path: '/foo', component: Foo },
    { path: '/bar', component: Bar }
];

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