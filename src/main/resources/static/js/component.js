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
    props: ['value','data','selection'],
    template: '<table v-if="data != null" class="table table-striped table-hover">' +
    '<thead>' +
    '<slot name="tt-title">' +
    '<tr>' +
    //复选框美化
    '<th v-if="selection">' +
    '<div class="checkbox checkbox-primary tt-from-checkbox">' +
    '<input v-model="allSelected" v-on:click="updateAllSelect" type="checkbox" class="tt-from-checkbox-input">' +
    '<label class="tt-from-checkbox-label"></label>' +
    '</div>' +
    '</th>' +
    //标题栏默认样式
    '<slot v-for="(item,key) in data.title" v-bind:name="\'tt-title-\'+key">' +
    '<th v-if="isString(item)">{{item}}</th>' +
    '<th v-else :width="item.width">{{item.name}}</th>' +
    '</slot>' +
    '</tr>' +
    '</slot>' +
    '</thead>' +
    '<tbody>' +
    '<slot name="tt-body">' +
    '<tr v-for="(item,index) in data.data">' +
    //复选框美化
    '<td v-if="selection">' +
    '<div class="checkbox checkbox-primary tt-from-checkbox">' +
    '<input v-model="checkedData" v-bind:value="item" v-on:click="updateSelect" type="checkbox" class="tt-from-checkbox-input">' +
    '<label class="tt-from-checkbox-label"></label>' +
    '</div>' +
    '</td>' +
    //表格主体默认样式
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
    data:function () {
        return{
            tableDate:this.data,
            checkedData:[]
        }
    },
    computed:{
        allSelected:function () {
            return this.checkedData.length != 0;
        }
    },
    mounted:function () {
        this.$emit('input',this.checkedData);
    },
    methods:{
        isString:function (str) {
            return Object.prototype.toString.call(str) == "[object String]"
        },
        updateAllSelect:function () {
            if (this.allSelected){
                this.checkedData = [];
            }else {
                this.checkedData = this.tableDate.data.slice(0);
            }
            this.$emit('input',this.checkedData)
        },
        updateSelect:function () {
            this.$emit('input',this.checkedData)
        }
    }
});