<template>
    <div>
        <!-- 头部标签 -->
        <header-label :data="headerLabel"></header-label>
        <!-- 动画 -->
        <div class="wrapper wrapper-content animated fadeInRight"><!-- animated -->

            <div class="row">
                <div class="col-lg-3"><div class="ibox tt-from-table"><div class="ibox-content">
                    <div id="menu-tree"></div>
                </div></div></div>
                <div class="col-lg-9">

                    <!-- 表单 -->
                    <div class="row"><div class="col-lg-12"><div class="ibox tt-from-table">
                        <div class="ibox-content">
                            <form role="form" class="form-inline">

                                <tt-simple-input label="名称" v-model="conditions.name"></tt-simple-input>

                                <div class="btn-toolbar pull-right" role="toolbar">
                                    <div class="btn-group">
                                        <button @click="showAddModal()" class="btn btn-outline btn-primary" type="button">新增</button>
                                        <button @click="showUpdateModal(tableSelectData[0])" v-if="hasOneChecked" class="btn btn-outline btn-primary" type="button">修改</button>
                                        <button @click="deleteOne()" v-if="hasOneChecked" class="btn btn-outline btn-danger" type="button">删除</button>
                                    </div>
                                    <div class="btn-group">
                                        <button @click="getTableList" class="btn btn-primary" type="button">搜索</button>
                                    </div>
                                </div>
                            </form>
                            <div class="clearfix"></div>
                        </div>
                    </div></div></div>

                    <!-- 表格 -->
                    <div class="row"><div class="col-lg-12"><div class="ibox">
                        <div class="ibox-content">
                            <div class="table-responsive">
                                <tt-table v-bind:data="tableData" :selection = "true" v-model="tableSelectData">
                                    <template slot="tt-body-operation" scope="props">
                                        <button @click="showUpdateModal(props.row)" class="btn btn-table btn-primary btn-rounded" type="button">修改</button>
                                    </template>
                                </tt-table>
                            </div>
                            <!-- 分页 -->
                            <tt-pagination :count="pagination.count" @listener="getTablePaginationList" button-size="sm" class="pull-right"></tt-pagination>
                            <div class="clearfix"></div>

                        </div>
                    </div></div></div>
                </div>

            </div>
        </div>

        <!-- 添加弹出窗 -->
        <tt-modal id="form-modal" :title="fromModalData.title" size="sm">
            <form role="form" class="validation">
                <div class="row">
                    <div class="col-sm-12"><!--<div class="col-sm-6 b-r">-->
                        <h4 class="m-t-none m-b">基本信息</h4>
                        <tt-simple-input label="名称" v-model="fromModalData.data.name" required></tt-simple-input>
                        <tt-simple-tree-root-v2 label="父节点" v-model="fromModalData.data.pid" :data="tree.point" :option="{key:'id',value:'name'}"></tt-simple-tree-root-v2>
                        <tt-simple-input label="排序" v-model="fromModalData.data.order" required></tt-simple-input>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <button @click="fromModalData.submit" class="btn btn-sm btn-primary pull-right m-t-n-xs" type="button"><strong>确认</strong></button>
                        <button data-dismiss="modal"  class="btn btn-sm btn-default pull-right m-t-n-xs tt-modal-cancel" type="button"><strong>取消</strong></button>
                    </div>
                </div>
            </form>
        </tt-modal>

        <div class="clearfix"></div>
        <br />
        <br />
        <br />

    </div>
</template>

<script type="application/javascript">

    //路由配置
    RouteConfig.deploy({
        data:function () {
            return {
                headerLabel:{
                    name:"网点管理",
                    path:{
                        parent:[
                            {url:"/",name:"Home"},
                            {name:"System"}
                        ],
                        active:"Point"
                    }
                },
                conditions:{
                    begin:0,
                    offset:10
                },
                tableData:{
                    title:{
                        id:"网点id",
                        name:"名称",
                        pid:"父id",
                        order:"排序",
                        operation:{name:"操作",width:"60px"}
                    },
                    data:[]
                },
                tableSelectData:[],
                pagination:{},
                fromModalData:{
                    title:"",
                    data:{},
                    empty:null,
                    submit:function () {}
                },
                tree:{
                    point:[]
                }
            }
        },
        computed:{
            hasChecked:function () {
                return this.tableSelectData.length !== 0;
            },
            hasOneChecked:function () {
                return this.tableSelectData.length === 1;
            },
            fromModal:function () {
                return new ModalBuilder("#form-modal");
            }
        },
        created:function () {
            let self = this;
            this.getTableList();
            Server.point.getPointTree.execute(data => {
                self.tree.point = data.object;
            });
        },
        beforeMount:function () {
        },
        mounted:function () {
            this.updateTree();
        },
        methods: {
            getTablePaginationList:function (index,size) {
                let self = this;
                self.conditions.begin = (index - 1) * size;
                self.conditions.offset = size;
                self.getTableList();
            },
            getTableList:function () {
                let self = this;
                Server.point.list.param(self.conditions).execute(data => {
                    self.tableData.data = data.object.list;
                    self.pagination.count = data.object.count;
                    self.initFromEmpty();
                });
            },
            initFromEmpty:function () {
                let self = this;
                if (!self.fromModalData.empty){
                    let empty = self.tableData.data.length === 0?null:self.tableData.data[0];
                    self.fromModalData.empty = JsonUtils.setNull(empty);
                }
            },
            getSubmitFunc:function (func) {
                let self = this;
                return function () {
                    if (ValidationUtils.check(".validation")){
                        func.body(self.fromModalData.data).execute(() => {
                            self.fromModal.hide();
                            self.getTableList();
                        })
                    }
                };
            },
            deleteOne:function () {
                let self = this;
                SweetAlertUtils.show().sure(function () {
                    Server.point.deleteById.path("id",self.tableSelectData[0].id).execute(() => self.getTableList());
                });
            },
            showAddModal:function () {
                this.fromModalData.title = "添加";
                this.fromModalData.data = JsonUtils.copy(this.fromModalData.empty);
                this.fromModalData.data.pid = this.conditions.pid;
                this.fromModalData.submit = this.getSubmitFunc(Server.point.add);
                this.fromModal.show();
            },
            showUpdateModal:function (obj) {
                this.fromModalData.title = "修改";
                this.fromModalData.data = JsonUtils.copy(obj);
                this.fromModalData.submit = this.getSubmitFunc(Server.point.update);
                this.fromModal.show();
            },
            updateTree:function () {
                let self = this;
                Server.point.getPointTree.execute(data => {
                    let treeData = data.object;
                    App.changeListTreeForJsTree(treeData);
                    $('#menu-tree').jstree({
                        'core' : {
                            'data' :treeData
                        }
                    }).on('changed.jstree', function (e, data) {
                        self.conditions.pid = self.conditions.pid === data.node.id ? null : data.node.id;
                        self.pname = data.node.text;
                        self.getTableList();
                    });
                });
            }
        }
    });

</script>
