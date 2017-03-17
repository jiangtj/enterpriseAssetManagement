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
    render:function (createElement) {
        var self = this;
        if (self.data == null){
            return createElement('div','请先绑定数据data！');
        }

        //标题checkbox
        var checkboxHead = [
            createElement('div',{class:['checkbox', 'checkbox-success','tt-table-checkbox']},[
                createElement('input',{
                    attrs:{
                        value:self.allSelected,
                        type:'checkbox'
                    },
                    domProps:{
                        /*checked:self.allSelected*/
                    },
                    on:{
                        click:self.updateAllSelect
                    }
                }),
                createElement('label')
            ])
        ];

        //标题tr
        var tableHeadTr = [];
        if (self.selection) tableHeadTr.push(createElement('th',checkboxHead));
        tableHeadTr.push(jQuery.map(self.data.title,function (item,key) {
            if (self.isString(item)){
                item = {name:item}
            }
            return SlotsUtils.getItem(self.$scopedSlots['tt-title' + key],
                createElement('th',{
                    attrs:{width:item.width}
                },item.name)
            )
        }));


        var getTableBodyTr = function (item,index) {
            //复选框
            self.checkedElement[index] = false;
            //主体checkbox
            var checkboxBody = [
                createElement('div',{class:['checkbox', 'checkbox-success','tt-table-checkbox']},[
                    createElement('input',{
                        attrs:{
                            type:'checkbox',
                            value:item
                        },
                        domProps:{
                            checked:self.checkedElement[index]
                        },
                        on:{
                            click:function (event) {
                                self.updateSelect(index,event.target.checked);
                            }
                        }
                    }),
                    createElement('label')
                ])
            ];

            //主体tr
            var tableBodyTr = [];
            if (self.selection) tableBodyTr.push(createElement('td',checkboxBody));
            tableBodyTr.push(jQuery.map(self.data.title,function (value,key) {
                if (key == '$index') item[key] = index;
                return createElement('td',SlotsUtils.get(self.$scopedSlots['tt-body' + key],[
                    createElement('div',item[key])
                ]));
            }));

            return createElement('tr',tableBodyTr);
        };

        return createElement('table',{
            class:{
                'table':true,
                'table-striped':true,
                'table-hover':true
            }
        },[
            createElement('thead',SlotsUtils.get(self.$scopedSlots['tt-title'],[
                createElement('tr',tableHeadTr)
            ])),
            createElement('tbody',SlotsUtils.get(self.$scopedSlots['tt-body'],jQuery.map(self.data.data,getTableBodyTr)))
        ]);
    },
    data:function () {
        return{
            tableDate:this.data,
            checkedElement:[]
        }
    },
    computed:{
        allSelected:function () {
            return this.checkedData.length != 0;
        },
        checkedData:function () {
            var temp = [];
            for (var i in this.checkedElement){
                if (this.checkedElement[i]) temp.push(this.data.data[i]);
            }
            return temp;
        },
        count:function () {
            if (this.data == null){
                return 0;
            }
            return this.data.data.length;
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
            this.changeCheckedStatus(this.allSelected);
            this.$emit('input',this.getCheckedItem())
        },
        updateSelect:function (index) {
            this.checkedElement[index] = !this.checkedElement[index];
            this.$emit('input',this.getCheckedItem())
        },
        changeCheckedStatus:function (flag) {
            for (var i = 0;i<this.count;i++){
                this.checkedElement[i] = flag;
            }
        },
        getCheckedItem:function () {
            var temp = [];
            for (var i = 0;i<this.count;i++){
                if (this.checkedElement[i]) temp.push(this.data.data[i]);
            }
            return temp;
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
            createElement(self.baseType == 'textarea'?'textarea':'input',{
                class:{
                    'form-control':true
                },
                attrs:{
                    type:self.baseType,
                    name:self.innerName,
                    value:self.value,
                    placeholder:self.placeholder,
                    rows:self.baseRow,
                    required:self.required,
                    minlength:self.minlength,
                    maxlength:self.maxlength
                },
                domProps:{
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
        bodyTitleElement.push(createElement('div',{ class:{'col-sm-10':true}},[createElement('h3',self.title)]));
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
        //body节点
        var bodyElement = [];
        bodyElement.push(createElement('div',{class:{'row':true}},bodyTitleElement));
        bodyElement.push(self.$slots.default);
        //根节点创建
        return createElement('div',{
            class:{
                'modal':true,
                'fade':true,
                "bs-example-modal-lg":self.size == "lg",
                "bs-example-modal-sm":self.size == "sm"
            },
            attrs:{'aria-hidden':true}
        },[
            createElement('div',{
                class:{
                    'modal-dialog':true,
                    "modal-lg":self.size == "lg",
                    "modal-sm":self.size == "sm"
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
            var temp = this.close == null?true:this.close;
            if (temp == "false") temp = false;
            return temp;
        }
    }
});