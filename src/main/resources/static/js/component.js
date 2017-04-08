const SlotsUtils = {
    get:function (slots,nodes) {
        if (slots != null){
            return [slots];
        }else {
            return nodes;
        }
    },
    getItem:function (slots,node) {
        return slots || node;
    }
};

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
    template: '<table class="table table-striped table-hover">' +
    '<thead>' +
    '<slot name="tt-title">' +
    '<tr>' +
    //复选框美化
    '<th v-if="selection">' +
    '<div class="checkbox checkbox-table tt-table-checkbox">' +
    '<input v-model="allSelected" @click="updateAllSelect" type="checkbox">' +
    '<label></label>' +
    '</div>' +
    '</th>' +
    //标题栏默认样式
    '<slot v-for="(item,key) in innerTableDate.title" v-bind:name="\'tt-title-\'+key">' +
    '<th :width="item.width">{{item.name}}</th>' +
    '</slot>' +
    '</tr>' +
    '</slot>' +
    '</thead>' +
    '<tbody>' +
    '<slot name="tt-body">' +
    '<tr v-for="(item,index) in innerTableDate.data">' +
    //复选框美化
    '<td v-if="selection">' +
    '<div class="checkbox checkbox-table tt-table-checkbox">' +
    '<input v-model="checkedData" v-bind:value="item" v-on:click="updateSelect" type="checkbox">' +
    '<label></label>' +
    '</div>' +
    '</td>' +
    //表格主体默认样式
    '<td v-for="(value,key) in innerTableDate.title">' +
    '<slot v-bind:name="\'tt-body-\'+key" v-bind:row="item" v-bind:index="index">' +
    '<div>{{item[key]}}</div>' +
    '</slot>' +
    '</td>' +
    '</tr>' +
    '</slot>' +
    '</tbody>' +
    '</table>',
    data:function () {
        return{
            checkedData:[]
        }
    },
    computed:{
        allSelected:function () {
            return this.checkedData.length !== 0;
        },
        innerTableDate:function () {
            let self = this;
            let temp = self.data || {};
            temp.title = temp.title || {error:{name:"data不能为空",width:null}};
            temp.data = temp.data || [];
            jQuery.each(temp.title,function (key,value) {
                if (self.isString(value)){
                    temp.title[key] = {name:value}
                }
                if (key === "$index"){
                    jQuery.each(temp.data,function (index,item) {
                        item[key] = index+1;
                    });
                }
            });
            return temp;
        }
    },
    created:function () {
    },
    mounted:function () {
        this.$emit('input',this.checkedData);
    },
    methods:{
        isString:function (str) {
            return Object.prototype.toString.call(str) === "[object String]"
        },
        updateAllSelect:function () {
            if (this.allSelected){
                this.checkedData = [];
            }else {
                this.checkedData = this.innerTableDate.data.slice(0);
            }
            this.$emit('input',this.checkedData)
        },
        updateSelect:function () {
            this.$emit('input',this.checkedData)
        }
    }
});

Vue.component('tt-pagination', {
    props: ['value','label','type','placeholder'],
    template: '<div class="btn-group">' +
    '<button type="button" class="btn btn-white"><i class="fa fa-chevron-left"></i></button>' +
    '<button class="btn btn-white">1</button>' +
    '<button class="btn btn-white  active">2</button>' +
    '<button class="btn btn-white">3</button>' +
    '<button class="btn btn-white">4</button>' +
    '<button type="button" class="btn btn-white"><i class="fa fa-chevron-right"></i> </button>' +
    '</div>',
    data:function(){
        return{
        }
    },
    computed: {
        baseType: function () {
            return this.type||"text";
        }
    },
    created:function () {
    },
    methods:{
        updateValue:function (value) {
            this.$emit('input',value)
        }
    }
});

Vue.component('tt-simple-input', {
    props: ['value','name','label','type','row','placeholder','required','minlength','maxlength'],
    render: function (createElement) {
        var self = this;
        return createElement('div',{
            class:{
                'form-group':true,
                'tt-from-input':true
            }
        },[
            createElement('label',self.label),
            createElement(self.baseType === 'textarea'?'textarea':'input',{
                class:{
                    'form-control':true
                },
                attrs:{
                    type:self.baseType,
                    name:self.innerName,
                    placeholder:self.placeholder,
                    rows:self.baseRow,
                    required:self.required,
                    minlength:self.minlength,
                    maxlength:self.maxlength
                },
                domProps:{
                    value:self.value
                },
                on:{
                    input:function(event){
                        self.updateValue(event.target.value)
                    }
                }
            })
        ])
    },
    data:function(){
        return{
        }
    },
    computed: {
        baseType: function () {
            return this.type||"text";
        },
        baseRow: function () {
            return this.row||"3";
        },
        innerName:function () {
            return this.name || this.label;
        }
    },
    created:function () {
    },
    methods:{
        updateValue:function (value) {
            this.$emit('input',value)
        }
    }
});

Vue.component('tt-modal', {
    props: ['size','close','title'],
    render:function(createElement){
        var self = this;
        //bodyTitle节点
        var bodyTitleElement = [];
        if (self.innerClose) bodyTitleElement.push(
            createElement('button',{
                class:{'close':true},
                attrs:{
                    'type':'button',
                    'data-dismiss':'modal',
                    'aria-label':'Close'
                }
            },[
                createElement('span',{
                    attrs:{'aria-hidden':true}
                },'×'/*&times;*/)
            ])
        );
        bodyTitleElement.push(createElement('div',{ class:{'col-sm-10':true}},[createElement('h3',self.title)]));
        //body节点
        var bodyElement = [];
        bodyElement.push(createElement('div',{class:{'row':true}},bodyTitleElement));
        bodyElement.push(self.$slots.default);
        //根节点创建
        return createElement('div',{
            class:{
                'modal':true,
                'fade':true,
                "bs-example-modal-lg":self.size === "lg",
                "bs-example-modal-sm":self.size === "sm"
            },
            attrs:{'aria-hidden':true}
        },[
            createElement('div',{
                class:{
                    'modal-dialog':true,
                    "modal-lg":self.size === "lg",
                    "modal-sm":self.size === "sm"
                }
            },[
                createElement('div',{
                    class:{'modal-content':true}
                },[
                    createElement('div',{
                        class:{'modal-body':true}
                    },bodyElement)
                ])
            ])
        ])
    },
    computed: {
        innerClose: function () {
            var temp = this.close === undefined?true:this.close;
            if (temp === "false") temp = false;
            return temp;
        }
    }
});