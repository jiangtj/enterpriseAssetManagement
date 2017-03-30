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
    '<div class="checkbox checkbox-success tt-table-checkbox">' +
    '<input v-model="allSelected" v-on:click="updateAllSelect" type="checkbox">' +
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
    '<div class="checkbox checkbox-success tt-table-checkbox">' +
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
            var self = this;
            var temp = self.data || {};
            temp.title = temp.title || {error:{name:"data不能为空",width:null}};
            temp.data = temp.data || [];
            jQuery.each(temp.title,function (key,value) {
                if (self.isString(value)){
                    temp.title[key] = {name:value}
                }
                if (key === "$index"){
                    jQuery.each(temp.data,function (index,item) {
                        item[key] = index;
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
    template: '<div class="form-group tt-from-input">' +
    '<label>{{label}}</label>' +
    '<textarea v-if="baseType == \'textarea\'" class="form-control" :rows="baseRow" :placeholder="placeholder" class="form-control"' +
    ':required="required" :minlength="minlength" :maxlength="maxlength"></textarea>' +
    '<input v-else :value="value" :name="innerName" @input="updateValue($event.target.value)" :type="baseType" :placeholder="placeholder" class="form-control"' +
    ':required="required" :minlength="minlength" :maxlength="maxlength">' +
    '</div>',
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
    template: '<div class="modal fade" v-bind:class="modalFormClass" aria-hidden="true">' +
    '<div class="modal-dialog" v-bind:class="modalDialogClass">' +
    '<div class="modal-content">' +
    '<div class="modal-body">' +
    '<div class="row">' +
    '<div class="col-sm-10"><h3>{{title}}</h3></div>' +
    '<button v-if="innerClose" type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>' +
    '</div>' +
    '<slot></slot>' +
    '</div>' +
    '</div>' +
    '</div>' +
    '</div>',
    computed: {
        innerClose: function () {
            var temp = this.close === undefined?true:this.close;
            if (temp === "false") temp = false;
            return temp;
        },
        modalFormClass:function () {
            return {
                "bs-example-modal-lg":this.size === "lg",
                "bs-example-modal-sm":this.size === "sm"
            }
        },
        modalDialogClass:function () {
            return {
                "modal-lg":this.size === "lg",
                "modal-sm":this.size === "sm"
            }
        }
    }
});