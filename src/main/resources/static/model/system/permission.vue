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
                            <template slot="tt-body-status" scope="props">
                                <tt-icon-check :checked="props.row.status === 1"></tt-icon-check>
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
                    name:"权限管理",
                    path:{
                        parent:[
                            {url:"/",name:"Home"},
                            {name:"System"}
                        ],
                        active:"Permission"
                    }
                },
                conditions:{
                    begin:0,
                    offset:10
                },
                tableData:{
                    title:{
                        $index:"序号",
                        id:"权限id",
                        name:"名称",
                        url:"url",
                        operation:{name:"操作",width:"120px"}
                    },
                    data:[]
                },
                tableSelectData:[],
                pagination:{},
                fromModalData:{
                    title:"",
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
                Server.permission.getList.setData(self.conditions).post(data => {
                    self.tableData.data = data.object.list;
                    self.pagination.count = data.object.count;
                });
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
                    Server.permission.delete.setData("ids="+ids).post(() => self.getTableList());
                });
            },
            showAddModal:function () {
                this.fromModalData.title = "添加新角色";
                this.fromModalData.data = {};
                this.fromModalData.submit = this.getSubmitFunc(Server.permission.add);
                this.fromModal.show();
            },
            showUpdateModal:function (obj) {
                this.fromModalData.title = "修改信息";
                this.fromModalData.data = JsonUtils.copy(obj);
                this.fromModalData.submit = this.getSubmitFunc(Server.permission.update);
                this.fromModal.show();
            }
        }
    });

</script>
