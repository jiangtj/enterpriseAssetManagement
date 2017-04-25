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
                                <button @click="showAddModal()"  class="btn btn-outline btn-primary" type="button">新增</button>
                                <button @click="showUpdateModal(tableSelectData[0])" v-if="hasOneChecked" class="btn btn-outline btn-primary" type="button">修改</button>
                                <button @click="deleteAll()" v-if="hasChecked" class="btn btn-outline btn-danger" type="button">删除</button>
                            </div>
                            <div class="btn-group">
                                <button @click="getTableList" class="btn btn-primary" type="button">搜索</button>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </form>
                </div>
            </div></div></div>

            <!-- 表格 -->
            <div class="row"><div class="col-lg-12"><div class="ibox">
                <div class="ibox-content">
                    <div class="table-responsive">
                        <tt-table v-bind:data="tableData" :selection = "true" v-model="tableSelectData">
                            <template slot="tt-body-permission" scope="props">
                                {{props.row.permission?props.row.permission.name:'未绑定权限'}}
                            </template>
                            <template slot="tt-body-type" scope="props">
                                <tt-icon-check :checked="props.row.type === 1"></tt-icon-check>
                            </template>
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
        <tt-modal id="form-modal" :title="fromModalData.title">
            <form role="form" class="validation">
                <div class="row">
                    <div class="col-sm-6 b-r"><!--<div class="col-sm-6 b-r">-->
                        <h4 class="m-t-none m-b">基本信息</h4>
                        <tt-simple-input label="名称" v-model="fromModalData.data.name" required></tt-simple-input>
                        <tt-simple-input label="编号" v-model="fromModalData.data.menu" required></tt-simple-input>
                        <tt-simple-input label="排序" v-model="fromModalData.data.order" required></tt-simple-input>
                        <tt-simple-select label="类型" v-model="fromModalData.data.type" :data="Map.menuType" required></tt-simple-select>
                    </div>
                    <div class="col-sm-6">
                        <h4 class="m-t-none m-b">额外</h4>
                        <tt-simple-tree-root label="父节点" v-model="fromModalData.data.pid" :data="getMenuMapById"></tt-simple-tree-root>
                        <tt-simple-input label="图标" v-model="fromModalData.data.icon"></tt-simple-input>
                        <tt-simple-input label="访问路径" v-model="fromModalData.data.url"></tt-simple-input>
                        <tt-simple-input label="静态资源" v-model="fromModalData.data.staticUrl"></tt-simple-input>
                        <tt-simple-select label="权限" v-model="fromModalData.permissionId" :data="Map.permission" show-undefined></tt-simple-select>
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
                    name:"菜单管理",
                    path:{
                        parent:[
                            {url:"/",name:"Home"},
                            {name:"System"}
                        ],
                        active:"Menu"
                    }
                },
                conditions:{
                    begin:0,
                    offset:10
                },
                tableData:{
                    title:{
                        id:"菜单id",
                        name:"名称",
                        pid:"父id",
                        level:"级别",
                        order:"排序",
                        type:"是否菜单",
                        permission:"关联权限",
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
            this.getTableList();
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
                Server.menu.getList.setData(self.conditions).post(data => {
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
                        func.setData(self.fromModalData.data).post(() => {
                            self.fromModal.hide();
                            self.getTableList();
                        })
                    }
                };
            },
            deleteAll:function () {
                let self = this;
                SweetAlertUtils.show().sure(function () {
                    let ids = $.map(self.tableSelectData,item => item.id);
                    Server.menu.delete.setData("ids="+ids).post(() => self.getTableList());
                });
            },
            showAddModal:function () {
                this.fromModalData.title = "添加";
                this.fromModalData.data = JsonUtils.copy(this.fromModalData.empty);
                this.fromModalData.submit = this.getSubmitFunc(Server.menu.add);
                this.fromModal.show();
            },
            showUpdateModal:function (obj) {
                this.fromModalData.title = "修改";
                this.fromModalData.data = JsonUtils.copy(obj);
                this.fromModalData.submit = this.getSubmitFunc(Server.menu.update);
                this.fromModal.show();
            },
            getMenuMapById:function (id) {
                let self;
                Server.menu.getMapById.setData("pid="+id).setAsync(false).post((data) => {
                    self = data.object;
                });
                return self;
            },
            updateTree:function () {
                let self = this;
                $('#menu-tree').jstree({
                    'core' : {
                        'data' :function (node,callback) {
                            Server.menu.getMenu.setData({pid:node.id==="#"?0:node.id}).post(data => {
                                let list = $.map(data.object,(item,index) => {
                                    item.parent = item.pid===0?"#":item.pid;
                                    item.text = item.name;
                                    item.children = true;
                                    return item;
                                });
                                callback.call(this,list)
                            });
                        }
                    }
                }).on('changed.jstree', function (e, data) {
                    self.conditions.pid = self.conditions.pid === data.node.id ? null : data.node.id;
                    self.getTableList();
                });
            }
        }
    });

</script>
