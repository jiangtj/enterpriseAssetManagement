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

                        <tt-simple-input label="uuid" v-model="conditions.uuid"></tt-simple-input>
                        <tt-simple-input label="资产编号" v-model="conditions.customsId"></tt-simple-input>
                        <tt-simple-input label="名称" v-model="conditions.name"></tt-simple-input>
                        <tt-simple-tree-root label="类型" v-model="conditions.assetsTypeId" :data="getTypeMapById"></tt-simple-tree-root>
                        <tt-simple-select label="网点" v-model="conditions.pointId" :data="Map.point" show-undefined></tt-simple-select>

                        <div class="btn-toolbar pull-right" role="toolbar">
                            <div class="btn-group">
                                <button @click="showUpdateModal(tableSelectData[0])" v-if="hasOneChecked && PermissionName('permission:update')" class="btn btn-outline btn-primary" type="button">修改</button>
                                <button @click="deleteAll()" v-if="hasChecked && PermissionName('permission:delete')" class="btn btn-outline btn-danger" type="button">删除</button>
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
                            <template slot="tt-body-operation" scope="props">
                                <button @click="showOperationRecordModal(props.row)"  v-if="PermissionName('asset:getOperationRecordByUuid')" class="btn btn-table btn-primary btn-rounded" type="button">操作记录</button>
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
                        <tt-simple-input v-if="!quick" label="名称" v-model="fromModalData.data.name" required></tt-simple-input>
                        <tt-simple-input label="url" v-model="fromModalData.data.url" required></tt-simple-input>
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

        <!-- 操作记录弹出窗 -->
        <tt-modal id="operation-record-modal" title="记录">
            <form role="form" class="validation">
                <div class="row">
                    <div class="col-sm-12"><!--<div class="col-sm-6 b-r">-->
                        <tt-table v-bind:data="operationRecordData"></tt-table>
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
                    name:"资产列表",
                    path:{
                        parent:[
                            {url:"/",name:"Home"},
                            {name:"Asset"}
                        ],
                        active:"Get"
                    }
                },
                conditions:{
                    begin:0,
                    offset:10
                },
                tableData:{
                    title:{
                        $index:"序号",
                        id:"uuid",
                        customsId:"资产编号",
                        name:"名称",
                        price:"价格",
                        status:"状态",
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
                operationRecordData:{
                    title:{
                        $index:"序号",
                        userId:"操作人id",
                        operationType:"操作类型"
                    },
                    data:[]
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
            operationRecordModal:function () {
                return new ModalBuilder("#operation-record-modal");
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
                Server.asset.getList.setData(self.conditions).post(data => {
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
                    Server.asset.delete.setData("ids="+ids).post(() => self.getTableList());
                });
            },
            showUpdateModal:function (obj) {
                this.fromModalData.title = "修改";
                this.fromModalData.data = JsonUtils.copy(obj);
                this.fromModalData.submit = this.getSubmitFunc(Server.asset.update);
                this.quick = false;
                this.fromModal.show();
            },
            showOperationRecordModal:function (obj) {
                let self =this;
                Server.asset.getOperationRecordByUuid.setData("uuid="+obj.uuid).post((data) =>{
                    self.operationRecordData.data = data.object;
                });
                self.operationRecordModal.show();
            },
            getTypeMapById:function (id) {
                let self;
                Server.assetType.getMapByPid.setData("pid="+id).setAsync(false).post((data) => {
                    self = data.object;
                });
                return self;
            }
        }
    });

</script>
