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

/*Vue.component('tt-table', {
    props: ['data'],
    template: '<table class="table table-striped">' +
    /!*'<thead><tr>' +
    '<slot name="table" v-bind:title="true"></slot>' +
    '</tr></thead>' +*!/
    '<tr v-for="item in data">' +
    '<slot name="td" v-bind:row="item"></slot>' +
    '</tr>' +
    '</table>'
});
Vue.component('tt-table-column', {
    props: ['prop','name'],
    template: '<td slot="td"><template scope="tableP">' +
    /!*'<span v-if="props.title">{{name}}</span>' +
    '<slot v-else v-bind:item="props.item">{{props.item[prop]}}</slot>' +*!/
    '<slot v-bind:row="tableP.row">{{tableP.row[prop]}}</slot>' +
    '</template></td>'
});*/
Vue.component('tt-table', {
    props: ['data'],
    template: '<table class="table table-striped">' +
    '<thead>' +
    '<slot name="tt-title">' +
    '<tr><slot v-for="(value,key) in data.title" v-bind:name="\'tt-title-\'+key"><th>{{value}}</th></slot></tr>' +
    '</slot>' +
    '</thead>' +
    '<tbody>' +
    '<slot name="tt-body">' +
    '<tr v-for="(item,index) in data.data"><td v-for="(value,key) in data.title">' +
    '<slot v-bind:name="\'tt-body-\'+key" v-bind:row="item" v-bind:index="index">' +
    '{{item[key]}}' +
    '</slot>' +
    '</td></tr>' +
    '</slot>' +
    '</tbody>' +
    '</table>'
});

Vue.component('my-awesome-list', {
    props: ['items'],
    template: '<ul> <slot name="item" v-for="item in items" :text="item.text"> <!-- fallback content here --> </slot> </ul>'
});