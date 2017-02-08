//头部标签
const headerLabel = {
    data:null,
    setDefault:function () {
        headerLabel.data = {
            name:"首页",
            path:{
                parent:[],
                active:"Home"
            }
        }
    },
    setData:function (data) {
        headerLabel.data = data;
    }
};
headerLabel.setDefault();
Vue.component('header-label', {
    template: '<div v-if="data != null" class="row wrapper border-bottom white-bg page-heading">'
    +'<div class="col-lg-10">'
    +'<h2>{{data.name}}</h2>'
    +'<ol v-if="data.path != null" class="breadcrumb">'
    +'<li v-for="item in data.path.parent">'
    +'<a v-if="item.url == null">{{item.name}}</a>'
    +'<router-link v-else v-bind:to="item.url">{{item.name}}</router-link>'
    +'</li>'
    +'<li class="active"><strong>{{data.path.active}}</strong></li>'
    +'</ol>'
    +'</div>'
    +'<div class="col-lg-2"></div>'
    +'</div>',
    data:function () {
        return headerLabel;
    }
});