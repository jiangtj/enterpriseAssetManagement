<template xmlns:v-shiro="http://www.w3.org/1999/xhtml">
    <div>
        <!-- 头部标签 -->
        <header-label :data="headerLabel"></header-label>
        <!-- 动画 -->
        <div class="wrapper wrapper-content animated fadeInRight"><!-- animated -->

            <!-- 表单 -->
            <div class="row"><div class="col-lg-12"><div class="ibox tt-from-table">
                <div class="ibox-content">
                    <form role="form" class="form-inline">

                        <tt-simple-input label="名称" v-model="conditions.name"></tt-simple-input>
                        <tt-simple-select label="状态" v-model="conditions.status" :data="Map.stockTakeStatus" show-undefined></tt-simple-select>

                        <div class="btn-toolbar pull-right" role="toolbar">
                            <div class="btn-group">
                                <button @click="showUpdateModal(tableSelectData[0])" v-shiro:permission="'stockTake:update'" v-if="hasOneChecked" class="btn btn-outline btn-primary" type="button">修改</button>
                                <button @click="deleteAll()" v-shiro:permission="'stockTake:delete'" v-if="hasChecked" class="btn btn-outline btn-danger" type="button">删除</button>
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
                                <button @click="updateAmount(props.row)" v-shiro:permission="'stockTake:updateAmount'" class="btn btn-table btn-primary btn-rounded" type="button">更新</button>
                                <button @click="routerPushToItem(props.row)" v-shiro:permission="'stockTake:getItemList'" class="btn btn-table btn-primary btn-rounded" type="button">明细</button>
                                <button @click="close(props.row)" v-shiro:permission="'stockTake:close'"  v-if="props.row.status === 1" class="btn btn-table btn-danger btn-rounded" type="button">关闭</button>
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
                        <!--<h4 class="m-t-none m-b">基本信息</h4>-->
                        <tt-simple-input label="名称" v-model="fromModalData.data.name" required></tt-simple-input>
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
                    name:"盘点任务",
                    path:{
                        parent:[
                            {url:"/",name:"Home"},
                            {name:"StockTake"}
                        ],
                        active:"Task"
                    }
                },
                conditions:{
                    begin:0,
                    offset:10
                },
                tableData:{
                    title:{
                        $index:"序号",
                        id:"任务id",
                        name:"名称",
                        allAmount:"总数",
                        handlingAmount:"待处理数",
                        normalAmount:"正常数",
                        abnormalAmount:"异常数",
                        operation:{name:"操作",width:"140px"}
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
                Server.stockTake.list.param(self.conditions).execute(data => {
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
                    Server.stockTake.delete.param("ids=",ids).execute(() => self.getTableList());
                });
            },
            showUpdateModal:function (obj) {
                this.fromModalData.title = "修改";
                this.fromModalData.data ={name:obj.name,id:obj.id};
                this.fromModalData.submit = this.getSubmitFunc(Server.stockTake.update);
                this.fromModal.show();
            },
            updateAmount:function (obj) {
                Server.stockTake.updateAmount.body({id:obj.id}).execute((data) => {
                    $.extend(true,obj,data.object);
                })
            },
            routerPushToItem:function (obj) {
                App.$router.push({ path: '/stockTake/item', query: { stockTakeId: obj.id }})
            },
            close:function (obj) {
                let self = this;
                SweetAlertUtils.show().sure(function () {
                    Server.stockTake.close.body({id:obj.id}).execute((data) => {
                        $.extend(true, obj, data.object);
                    })
                });
            }
        }
    });

</script>
