//todo 整合菜单为单个递归组件
Vue.component("tt-menu-root",{
    props: ['data'],
    render:function (createElement) {
        let self = this;
        //权限
        if (!Shiro.requiresRoles(self.data.role,self.data.logical) ||
            !Shiro.requiresPermissions(self.data.permission,self.data.logical)){
            return null;
        }

        let elements = [];
        if (!self.isEmpty(self.data.icon)) elements.push(createElement("i",{class:["fa",self.data.icon]}));
        elements.push(createElement("span",{class:["nav-label"]},self.data.name));
        if (!self.isEmpty(self.data.list)) elements.push(createElement("span",{class:["fa","arrow"]}));
        return createElement(self.isEmpty(self.data.url)?"a":"router-link",{attrs:{to:self.data.url}},elements)
    },
    computed:{
    },
    methods:{
        isEmpty:function (value) {
            return value === null || value === undefined || value === "";
        }
    }
});

Vue.component("tt-menu-second",{
    props: ['data'],
    render:function (createElement) {
        let self = this;

        if (self.isEmpty(self.data)) return null;

        return createElement("ul",{class:["nav","nav-second-level","collapse"]},$.map(this.data,function (item) {

            if (Shiro.requiresRoles(self.data.role,self.data.logical) &&
                Shiro.requiresPermissions(self.data.permission,self.data.logical)){

                let elements = [];
                if (!self.isEmpty(item.icon)) elements.push(createElement("i",{class:["fa",self.data.icon]}));
                //elements.push(createElement("span",item.name));
                elements.push(item.name);
                return createElement("li",{class:{'active':item.isActive}},[
                    createElement(self.isEmpty(item.url)?"a":"router-link",{attrs:{to:item.url}},elements)
                ])

            }
        }));
    },
    computed:{
    },
    methods:{
        isEmpty:function (value) {
            return value === null || value === undefined || value === "";
        }
    }
});

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
            this.$emit('input',this.checkedData);
        },
        updateSelect:function () {
            this.$emit('input',this.checkedData);
        }
    },
    watch:{
        "data.data":{
            handler:function () {
                this.checkedData = [];
                this.$emit('input',this.checkedData);
            },
            deep:true
        }
    }
});

//size每页个数，count总条目数，index选择页数，button-count按钮个数，button-size按钮大小，listener点击事件监听器
Vue.component('tt-pagination', {
    props: ['size','count','index',"button-count","button-size"],
    render: function (createElement) {
        let self = this;

        //数字按钮
        let numberButton = [];
        //首页，上一页
        numberButton.push(createElement("button",{
            class:["btn","btn-white"],
            attrs:{type:"button",disabled:self.innerIndex === 1},
            on:{click:self.getUpdateIndexFunc(1)}
        },[
            createElement("i",{class:["fa","fa-angle-double-left"]})
        ]));
        numberButton.push(createElement("button",{
            class:["btn","btn-white"],
            attrs:{type:"button",disabled:self.innerIndex === 1},
            on:{click:self.getUpdateIndexFunc(self.innerIndex - 1)}
        },[
            createElement("i",{class:["fa","fa-angle-left"]})
        ]));
        //数字
        for (let i = 0;i < self.innerButtonCount;i++){
            numberButton.push(createElement("button",{
                class:["btn",i+self.offset === self.innerIndex?"btn-primary":"btn-white"],
                attrs:{type:"button",disabled:i+self.offset===self.innerIndex},
                on:{click:self.getUpdateIndexFunc(i+self.offset)}
            },i+self.offset))
        }
        //下一页尾页
        numberButton.push(createElement("button",{
            class:["btn","btn-white"],
            attrs:{type:"button",disabled:self.innerIndex === self.maxPageSize},
            on:{click:self.getUpdateIndexFunc(self.innerIndex + 1)}
        },[
            createElement("i",{class:["fa","fa-angle-right"]})
        ]));
        numberButton.push(createElement("button",{
            class:["btn","btn-white"],
            attrs:{type:"button",disabled:self.innerIndex === self.maxPageSize},
            on:{click:self.getUpdateIndexFunc(self.maxPageSize)}
        },[
            createElement("i",{class:["fa","fa-angle-double-right"]})
        ]));

        //输入按钮
        let inputButton = [];
        inputButton.push(createElement("input",{
            class:["form-control"],
            style:{width:"40px"},attrs:{type:"text"},
            domProps:{
                value:self.inputIndex
            },
            on:{
                input:function(event){
                    let temp = parseInt(event.target.value);
                    if (isNaN(temp)) temp = self.inputIndex;
                    if (temp > self.maxPageSize) temp = self.maxPageSize;
                    if (temp <= 0) temp = 1;
                    self.inputIndex = temp;
                    event.target.value=self.inputIndex;
                }
            }
        }));
        inputButton.push(createElement("span",{class:["input-group-addon"]},"/"+self.maxPageSize));
        inputButton.push(createElement("span",{class:["input-group-btn"]},[
            createElement("button",{
                class:["btn","btn-primary"],
                attrs:{type:"button"},
                on:{click:self.goToInputIndex}
            },"Go!")
        ]));

        return createElement("form",{class:["tt-form-all-inline"],attrs:{role:"from"}},[
            createElement("div",{class:["btn-group",self.buttonGroupClass],style:{marginRight:"2px"}},numberButton),
            createElement("div",{class:["input-group",self.inputGroupClass]},inputButton)
        ]);
    },
    data:function () {
        return {
            //每页个数
            innerSize:parseInt(this.size||10),
            //总条目数
            innerCount:parseInt(this.count||0),
            //选择页码
            innerIndex:this.index||1,
            //待跳转页码
            inputIndex:this.index||1,
            //样式
            sizeClass:{
                lg:{
                    button:"btn-group-lg",
                    input:"input-group-lg"
                },
                sm:{
                    button:"btn-group-sm",
                    input:"input-group-sm"
                },
                xs:{
                    button:"btn-group-xs",
                    input:"input-group-xs"
                }
            }
        }
    },
    computed: {
        //最大页数
        maxPageSize:function () {
            let temp = Math.ceil(this.innerCount/this.innerSize);
            if (temp === 0) temp=1;
            return temp;
        },
        //按钮数
        innerButtonCount: function () {
            let temp = this.buttonCount||5;
            return Math.min(temp,this.maxPageSize);
        },
        //偏移量
        offset:function () {
            let temp = parseInt(this.innerIndex||1);
            let innerOffset = Math.ceil(this.innerButtonCount/2);
            let maxIndex = this.maxPageSize + innerOffset -this.innerButtonCount;
            if (temp > maxIndex) temp = maxIndex;
            temp = temp - innerOffset + 1;
            if (temp <= 0) temp = 1;
            return temp;
        },
        //样式
        buttonGroupClass:function () {
            if (this.buttonSize) return this.sizeClass[this.buttonSize].button;
            return undefined;
        },
        inputGroupClass:function () {
            if (this.buttonSize) return this.sizeClass[this.buttonSize].input;
            return undefined;
        }
    },
    created:function () {
    },
    methods:{
        getUpdateIndexFunc:function (i) {
            let self = this;
            return function(){
                self.updateIndex(i);
            };
        },
        goToInputIndex:function () {
            this.updateIndex(this.inputIndex);
        },
        updateIndex:function (i) {
            let self = this;
            self.innerIndex = i;
            self.inputIndex = self.innerIndex;
            this.$emit('listener',self.innerIndex,self.innerSize);
        },
        getUpdateSizeFunc:function (size) {
            let self = this;
            return function(){
                self.innerSize = size;
                alert(size);
            };
        }
    },
    watch:{
        count:function (value) {
            this.innerCount = parseInt(value);
        },
        size:function (value) {
            this.innerSize = parseInt(value);
        }
    }
});

Vue.component('tt-simple-input', {
    props: ['value','name','label','type','row','placeholder','required','minlength','maxlength','disabled'],
    render: function (createElement) {
        let self = this;
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
                    disabled:self.disabled,
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
            this.$emit('input',value);
        }
    }
});

Vue.component("tt-simple-select",{
    props: ['data','value','name','label','show-undefined','required'],
    template:'<div class="form-group tt-from-input">' +
    '<label>{{label}}</label>' +
    '<select :value="value" @input="updateValue($event.target.value)" class="form-control" :name="innerName" :required="required">' +
    '<option v-if="showOthers" :value="undefined">---- 请选择 ----</option>' +
    '<option v-for="item in data" :value="item.key">{{ item.value }}</option>' +
    '</select>' +
    '</div>',
    computed: {
        innerName:function () {
            return this.name || this.label;
        },
        showOthers:function () {
            return this.showUndefined !== undefined
        }
    },
    methods:{
        updateValue:function (value) {
            this.$emit('input',value)
        }
    }
});
Vue.component("tt-multi-select",{
    props: ['data','value','name','label','show-undefined','required','multiple'],
    template:'<div class="form-group tt-from-input">' +
    '<label>{{label}}</label>' +
    '<select :id="\'chosen-\'+id" :value="value" @input="updateValue($event.target.value)" class="form-control chosen-select" :name="innerName" :required="required">' +
    '<option v-if="showOthers" :value="undefined">---- 请选择 ----</option>' +
    '<option v-for="item in data" :value="item.key">{{ item.value }}</option>' +
    '</div>' +
    '</div>',
    data:function () {
        return {
            id:new Date().getTime()
        }
    },
    computed: {
        innerName:function () {
            return this.name || this.label;
        },
        showOthers:function () {
            return this.showUndefined !== undefined
        }
    },
    mounted:function () {
        let self = this;
       $('.chosen-select').chosen({width: "100%"}).change(function () {
           let value = $("#chosen-"+self.id).val();
           self.$emit('input',value)
       });
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
        let self = this;
        //bodyTitle节点
        let bodyTitleElement = [];
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
        bodyTitleElement.push(createElement('div',{ class:{'col-sm-12':true}, style:{width:"80%"}},[createElement('h3',self.title)]));
        //body节点
        let bodyElement = [];
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
            let temp = this.close === undefined?true:this.close;
            if (temp === "false") temp = false;
            return temp;
        }
    }
});

Vue.component("tt-icon-check",{
    props: ['shape','black','checked'],
    template:'<i class="fa" :class="innerClass"></i>',
    computed: {
        innerShape:function () {
            return this.shape||"square";
        },
        innerClass:function () {
            let temp = "fa";
            if (this.checked) temp+="-check";
            temp=temp+"-"+this.innerShape;
            if (!this.black) temp+="-o";
            return [temp];
        }
    }
});

Vue.component("tt-simple-tree-root",{
    props: ['data','value','label'],
    template:'<div class="form-group tt-from-input">' +
    '<label>{{label}}</label>' +
    '<tt-simple-tree-children :data="data(0)" :func="data" v-model="object"></tt-simple-tree-children>' +
    '</div>',
    data:function () {
        return {
            object:this.value
        }
    },
    watch:{
        object:function (value) {
            this.$emit('input',value);
        }
    }
});
Vue.component("tt-simple-tree-children",{
    props: ['data','value','func'],
    template:'<div v-if="data.length !== 0">' +
    '<select v-model="object1" class="form-control">' +
    '<option :value="null">---- 请选择 ----</option>' +
    '<option v-for="item in data" :value="item.key">{{ item.value }}</option>' +
    '</select>' +
    '<tt-simple-tree-children v-if="hasTree" :data="func(object1)" :func="func" :value="object2" @input="updateValue"></tt-simple-tree-children>' +
    '</div>',
    data:function () {
        return {
            object1:this.value,
            object2:null
        }
    },
    computed:{
        hasTree:function () {
            return this.object1 !== null && this.object1 !== undefined;
        }
    },
    methods:{
        updateValue:function (value) {
            this.object2 = value;
            if (value === null) this.$emit('input',this.object1);
        }
    },
    watch:{
        object1:function (value) {
            this.object2=null;
            this.$emit('input',value);
        },
        object2:function (value) {
            this.$emit('input',value?value:this.object1);
        },
        value:function (value) {
            if (value === null) this.object1=value;
        }
    }
});

//解析树形数据，data传入树形数据，nodes为data节点名称（若为函数则传入id调用），监听select值并调用父组件函数返回值
Vue.component("tt-simple-tree-root-v2",{
    props: ['label','value','data','nodes','option','required','root-required'],
    template:'<div class="form-group tt-from-input">' +
    '<label>{{label}}</label>' +
    '<tt-simple-tree-children-v2 :data="tree" :nodes="innerNodes" :inner-key="innerKey" :inner-value="innerValue" ' +
    ':pid="null" :call="call" :default-value="defaultValue" :inner-level="0" :required="required" :root-required="rootRequired"></tt-simple-tree-children-v2>' +
    '</div>',
    data:function () {
        return {
            object:null,
            //tree:this.data||[],
            innerNodes:this.nodes||"nodes",
            innerKey:"key",
            innerValue:"value",
        }
    },
    computed:{
        defaultValue:function () {
            if (!this.value) return null;
            let temp = this.getDefaultValue(this.tree,this.value);
            if (!temp) return null;
            return temp.toString().split(":");
        },
        tree:function () {
            return this.data||[];
        }
    },
    created:function () {
        if (this.option){
            this.innerKey = this.option.key||"key";
            this.innerValue = this.option.value||"value"
        }
    },
    methods:{
        call:function (object,pre) {
            if (!this.defaultValue||!object||pre){
                this.$emit('input',object);
                return;
            }
            if (this.defaultValue.indexOf(object.toString()) !== -1){
                return;
            }
            this.$emit('input',object);
        },
        getDefaultValue:function (tree,value) {
            let self =this;
            for (let i in tree) {
                if (tree[i][self.innerNodes]) {
                    let temp = self.getDefaultValue(tree[i][self.innerNodes],value);
                    if (temp) {
                        return tree[i][self.innerKey] + ":" +temp;
                    }
                }
                if (tree[i][self.innerKey].toString() === value.toString()) {
                    return value;
                }
            }
        }
    }
});
Vue.component("tt-simple-tree-children-v2",{
    props: ['data','nodes',"inner-key","inner-value",'pid','call','default-value','inner-level','required','root-required'],
    template:'<div v-if="data.length !== 0">' +
    '<select v-model="selectModel" class="form-control" :required="required||rootRequired">' +
    '<option :value="null">---- 请选择 ----</option>' +
    '<option v-for="item in data" :value="item">{{ item[innerValue] }}</option>' +
    '</select>' +
    '<tt-simple-tree-children-v2 v-if="hasNext" :data="selectModel[nodes]" :nodes="nodes" :inner-key="innerKey" :inner-value="innerValue" ' +
    ':pid="selectModel[innerKey]" :call="call" :default-value="defaultValue" :inner-level="level" :required="required"></tt-simple-tree-children-v2>' +
    '</div>',
    data:function () {
        return {
            selectModel:null
        }
    },
    computed:{
        hasNext:function () {
            if (!this.selectModel) return false;
            return this.selectModel[this.nodes];
        },
        level:function () {
            return (this.innerLevel + 1);
        }
    },
    created:function () {
        this.changeByDefault(this.defaultValue);
    },
    methods:{
        changeByDefault:function (value) {
            let self = this;
            if (!value) {
                self.selectModel = null;
                return;
            }
            if (value.length > self.innerLevel){
                for (let i in self.data) {
                    if (self.data[i][self.innerKey].toString() === value[self.innerLevel]) {
                        self.selectModel = self.data[i];
                        return;
                    }
                }
            }
            self.selectModel = null;
        }
    },
    watch:{
        defaultValue:function (value) {
            this.changeByDefault(value);
        },
        selectModel:function (item) {
            if (item === null) {
                this.call(this.pid,true);
                return;
            }
            this.call(item[this.innerKey]);
        },
        data:function (value) {
            this.selectModel = null;
        }
    }
});