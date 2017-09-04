<template>
    <div>
        <!-- 头部标签 -->
        <header-label :data="headerLabel"></header-label>
        <!-- 动画 -->
        <div class="wrapper wrapper-content animated fadeInRight"><!-- animated -->

            <!-- 表单 -->
            <div class="row"><div class="col-lg-12"><div class="ibox tt-from-table">
                <div class="ibox-content">
                    <form role="form" class="form-inline">

                        <tt-simple-input label="角色名" v-model="conditions.name"></tt-simple-input>
                        <tt-simple-select label="状态" v-model="conditions.status" :data="Map.roleStatus" show-undefined></tt-simple-select>

                        <div class="btn-toolbar pull-right" role="toolbar">
                            <div class="btn-group">
                                <button @click="showAddModal()" class="btn btn-outline btn-primary" type="button">新增</button>
                                <button @click="showUpdateModal(tableSelectData[0])" v-if="hasOneChecked" class="btn btn-outline btn-primary" type="button">修改</button>
                                <button @click="deleteAll()" v-if="hasChecked" class="btn btn-outline btn-danger" type="button">删除</button>
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
                            <template slot="tt-body-status" scope="props">
                                <tt-icon-check :checked="props.row.status === 1"></tt-icon-check>
                            </template>
                            <template slot="tt-body-operation" scope="props">
                                <button @click="showPermissionModal(props.row)" class="btn btn-table btn-primary btn-rounded" type="button">权限</button>
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

        <!-- 添加弹出窗 -->
        <tt-modal id="form-modal" :title="fromModalData.title" size="sm">
            <form role="form" class="validation">
                <div class="row">
                    <div class="col-sm-12"><!--<div class="col-sm-6 b-r">-->
                        <h4 class="m-t-none m-b">基本信息</h4>
                        <tt-simple-input label="角色" v-model="fromModalData.data.name" required></tt-simple-input>
                        <tt-simple-select label="状态" v-model="fromModalData.data.status" :data="Map.roleStatus" show-undefined required></tt-simple-select>
                    </div>
                    <!--<div class="col-sm-6">
                        <h4>权限配置</h4>
                    </div>-->
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <button @click="fromModalData.submit" class="btn btn-sm btn-primary pull-right m-t-n-xs" type="button"><strong>确认</strong></button>
                        <button data-dismiss="modal"  class="btn btn-sm btn-default pull-right m-t-n-xs tt-modal-cancel" type="button"><strong>取消</strong></button>
                    </div>
                </div>
            </form>
        </tt-modal>

        <!-- 权限弹出框 -->
        <!--<tt-modal id="permission-modal" title="配置权限" size="sm">
            <form role="form" class="validation">
                <div class="row">
                    <div class="col-sm-12">&lt;!&ndash;<div class="col-sm-6 b-r">&ndash;&gt;
                        <div id="permission-tree"></div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <button @click="permissionModalData.submit" class="btn btn-sm btn-primary pull-right m-t-n-xs" type="button"><strong>确认</strong></button>
                        <button data-dismiss="modal"  class="btn btn-sm btn-default pull-right m-t-n-xs tt-modal-cancel" type="button"><strong>取消</strong></button>
                    </div>
                </div>
            </form>
        </tt-modal>-->
        <tt-modal id="permission-modal" title="配置权限">
            <div class="row">
                <div class="col-sm-12">
                    <p>请选择当前角色所需的权限（点击选择，>>全选）</p>
                    <form id="form" action="#" class="wizard-big">
                        <select class="form-control dual_select" multiple>
                            <option v-for="item in permissionModalData.items" :value="item.id" :selected="item.isSelected">
                                {{item.code+"("+item.name+")"}}
                            </option>
                        </select>
                    </form>
                </div>
            </div>
            <div class="row tt-row-footer">
                <div class="col-sm-12">
                    <button @click="permissionModalData.submit" class="btn btn-sm btn-primary pull-right" type="button"><strong>确认</strong></button>
                    <button data-dismiss="modal"  class="btn btn-sm btn-default pull-right tt-modal-cancel" type="button"><strong>取消</strong></button>
                </div>
            </div>
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
                    name:"角色管理",
                    path:{
                        parent:[
                            {url:"/",name:"Home"},
                            {name:"System"}
                        ],
                        active:"Role"
                    }
                },
                conditions:{
                    begin:0,
                    offset:10
                },
                tableData:{
                    title:{
                        $index:"序号",
                        id:"角色id",
                        name:"名称",
                        status:"状态",
                        operation:{name:"操作",width:"130px"}
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
                permissionModalData:{
                    data:{},
                    items:[],
                    submit:function () {}
                },
                pointModalData:{
                    data:{},
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
            },
            permissionModal:function () {
                return new ModalBuilder("#permission-modal");
            },
            dualSelect:function () {
                return $('.dual_select').bootstrapDualListbox({
                    selectorMinimalHeight: 200
                });
            }
        },
        created:function () {
            this.getTableList();
        },
        beforeMount:function () {
        },
        mounted:function () {
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
                Server.role.list.param(self.conditions).execute(data => {
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
            deleteAll:function () {
                let self = this;
                SweetAlertUtils.show().sure(function () {
                    let ids = $.map(self.tableSelectData,item => item.id);
                    Server.role.delete.param("ids",ids).execute(() => self.getTableList());
                });
            },
            showAddModal:function () {
                this.fromModalData.title = "添加新角色";
                this.fromModalData.data = JsonUtils.copy(this.fromModalData.empty);
                this.fromModalData.submit = this.getSubmitFunc(Server.role.add);
                this.fromModal.show();
            },
            showUpdateModal:function (obj) {
                this.fromModalData.title = "修改信息";
                this.fromModalData.data = JsonUtils.copy(obj);
                this.fromModalData.submit = this.getSubmitFunc(Server.role.update);
                this.fromModal.show();
            },
            showPermissionModal:function (obj) {
                let self = this;
                //self.updatePermissionTree();
                Server.role.getPermission.param({roleId:obj.id}).execute(data => {
                    let temp = data.object.all;
                    $.each(temp,function (index,item) {
                        $.each(data.object.role,function (ri,rt) {
                            if (item.code === rt) item.isSelected = true;
                        });
                        if (!item.isSelected) item.isSelected = false;
                    });
                    self.permissionModalData.items = temp;
                    Vue.nextTick(function () {
                        self.dualSelect.bootstrapDualListbox('refresh');
                    });
                });
                self.permissionModalData.data = {id:obj.id};
                self.permissionModalData.submit = function () {
                    Server.role.updatePermission.body({
                        roleId:obj.id,
                        permissionIds:(self.dualSelect.val()||"").toString()
                    }).execute(() => self.permissionModal.hide())
                };
                self.permissionModal.show();
            }
        }
    });

</script>
