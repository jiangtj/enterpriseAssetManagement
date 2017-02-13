Vue.component('header-label', {
    props: ['data'],
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
    +'</div>'
});

Vue.component('tt-table', {
    props: ['data',"selection"],
    template: '<table v-if="data != null" class="table table-striped">' +
    '<thead>' +
    '<slot name="tt-title">' +
    '<tr>' +
    '<th v-if="selection"><input type="checkbox"  checked class="i-checks icheckbox_square-green" name="input[]" /></th>' +
    '<slot v-for="(value,key) in data.title" v-bind:name="\'tt-title-\'+key"><th>{{value}}</th></slot>' +
    '</tr>' +
    '</slot>' +
    '</thead>' +
    '<tbody>' +
    '<slot name="tt-body">' +
    '<tr v-for="(item,index) in data.data">' +
    '<td v-if="selection"><input type="checkbox"  checked class="i-checks icheckbox_square-green" name="input[]" /></td>' +
    '<td v-for="(value,key) in data.title">' +
    '<slot v-bind:name="\'tt-body-\'+key" v-bind:row="item" v-bind:index="index">' +
    '<div v-if="key == \'$index\'">{{index}}</div>' +
    '<div v-else>{{item[key]}}</div>' +
    '</slot>' +
    '</td>' +
    '</tr>' +
    '</slot>' +
    '</tbody>' +
    '</table>',
    mounted:function () {
        //美化复选框
        ICheckUtils.beautifyChecks();
    }
});

Vue.component('my-awesome-list', {
    props: ['items'],
    template: '<ul> <slot name="item" v-for="item in items" :text="item.text"> <!-- fallback content here --> </slot> </ul>'
});