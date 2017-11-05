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

                        <tt-simple-input label="用户名" v-model="conditions.name"></tt-simple-input>
                        <tt-simple-select label="角色" v-model="conditions.roleId" :data="Map.role" show-undefined></tt-simple-select>

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
                    </form>
                    <div class="clearfix"></div>
                </div>
            </div></div></div>

            <!-- 表格 -->
            <div class="row"><div class="col-lg-12"><div class="ibox">
                <div class="ibox-content">
                    <div class="table-responsive">
                        <tt-table v-bind:data="tableData" :selection = "true" v-model="tableSelectData">
                            <template slot="tt-body-roleName" scope="props">
                                {{props.row.role.name}}
                            </template>
                            <template slot="tt-body-operation" scope="props">
                                <button @click="showUpdateModal(props.row)" class="btn btn-table btn-primary btn-rounded" type="button">修改</button>
                                <button @click="showPasswordModal(props.row)" class="btn btn-table btn-danger btn-rounded" type="button">重置密码</button>
                            </template>
                        </tt-table>
                    </div>

                    <tt-pagination :count="pagination.count" @listener="getTablePaginationList" button-size="sm" class="pull-right"></tt-pagination>
                    <div class="clearfix"></div>

                </div>
            </div></div></div>
        </div>

        <!-- 添加弹出窗 -->
        <tt-modal id="form-modal" :title="fromModalData.title">
            <form role="form" class="validation">
                <div class="row">
                    <div class="col-sm-6 b-r">
                        <h4 class="m-t-none m-b">基本信息</h4>
                        <p>这里的信息很重要,不要乱填.</p>
                        <tt-simple-input label="用户名" v-model="fromModalData.data.name" required></tt-simple-input>
                        <tt-simple-input v-if="fromModalData.isCreate" label="密码" v-model="fromModalData.data.password" type="password" required minlength="6"></tt-simple-input>
                        <tt-simple-select label="角色" v-model="fromModalData.data.roleId" :data="Map.role" show-undefined required></tt-simple-select>
                    </div>
                    <div class="col-sm-6">
                        <h4>额外 More</h4>
                        <p>个性化的介绍.</p>
                        <tt-simple-tree-root-v2 label="网点" v-model="fromModalData.data.pointId" :data="tree.point" :option="{key:'id',value:'name'}" root-required></tt-simple-tree-root-v2>
                        <tt-simple-input label="描述&简介" v-model="fromModalData.data.description" type="textarea" row="5" minlength="6"></tt-simple-input>
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

        <!-- 密码弹出窗 -->
        <tt-modal id="password-modal" title="请输入" size="sm">
            <form role="form" class="validation">
                <div class="row">
                    <div class="col-sm-12">
                        <tt-simple-input type="password" label="密码" v-model="pointModalData.data.password"></tt-simple-input>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <button @click="pointModalData.submit" class="btn btn-sm btn-primary pull-right m-t-n-xs" type="button"><strong>确认</strong></button>
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
                    name:"用户管理",
                    path:{
                        parent:[
                            {url:"/",name:"Home"},
                            {name:"System"}
                        ],
                        active:"User"
                    }
                },
                conditions:{
                    begin:0,
                    offset:10
                },
                tableData:{
                    title:{
                        $index:"序号",
                        id:"用户id",
                        name:"名称",
                        description:"简介",
                        roleName:"角色名称",
                        operation:{name:"操作",width:"160px"}
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
                pointId:"15",
                pointModalData:{
                    data:{
                        pointId:"15"
                    },
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
            self.getTableList();
            Server.point.getPointTree.execute(data => {
                self.tree.point = data.object;
            });
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
                Server.user.list.param(self.conditions).execute(data => {
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
                    Server.user.delete.param("ids",ids).execute(() => self.getTableList());
                });
            },
            showAddModal:function () {
                this.fromModalData.title = "添加新用户";
                this.fromModalData.data = JsonUtils.copy(this.fromModalData.empty);
                this.fromModalData.isCreate = true;
                this.fromModalData.submit = this.getSubmitFunc(Server.user.add);
                this.fromModal.show();
            },
            showUpdateModal:function (obj) {
                this.fromModalData.title = "修改信息";
                this.fromModalData.data = JsonUtils.copy(obj);
                JsonUtils.clear(this.fromModalData.data,"password","role");
                this.fromModalData.isCreate = false;
                this.fromModalData.submit = this.getSubmitFunc(Server.user.update);
                this.fromModal.show();
            },
            showPasswordModal:function (obj) {
                //todo 接口待写
                let self = this;
                self.pointModalData.submit = function () {
                    ToastrUtils.show("成功","",1);
                    $("#password-modal").modal("hide");
                };
                $("#password-modal").modal("show");
            }
        }
    });

</script>
