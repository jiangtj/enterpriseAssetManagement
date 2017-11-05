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

                        <tt-simple-input label="uuid" v-model="conditions.uuid"></tt-simple-input>
                        <tt-simple-input label="资产编号" v-model="conditions.customsId"></tt-simple-input>
                        <tt-simple-input label="名称" v-model="conditions.name"></tt-simple-input>
                        <tt-simple-tree-root-v2 label="类型" v-model="conditions.assetsTypeId" :data="tree.assetType" :option="{key:'id',value:'name'}"></tt-simple-tree-root-v2>
                        <tt-simple-select label="网点" v-model="conditions.pointId" :data="Map.point" show-undefined></tt-simple-select>

                        <div class="btn-toolbar pull-right" role="toolbar">
                            <div class="btn-group">
                                <button @click="showUpdateModal(3,tableSelectData[0])" v-if="hasOneChecked" v-shiro:permission="'asset:updateStatus'" class="btn btn-outline btn-primary" type="button">{{tableSelectData[0].status === 3?'完成':'维修'}}</button>
                                <button @click="showUpdateModal(4,tableSelectData[0])" v-if="hasOneChecked" v-shiro:permission="'asset:updateStatus'" class="btn btn-outline btn-primary" type="button">{{tableSelectData[0].status === 4?'撤回报废':'报废'}}</button>
                                <button @click="openStockTake()" v-shiro:permission="'asset:stockTake:add'" class="btn btn-outline btn-primary" type="button">开启盘点</button>
                                <button @click="deleteAll()" v-if="hasChecked" v-shiro:permission="'asset:delete'" class="btn btn-outline btn-danger" type="button">删除</button>
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
                                <button @click="showQRCodeModal(props.row)" class="btn btn-table btn-primary btn-rounded" type="button">QRCode</button>
                                <button @click="showOperationRecordModal(props.row)" v-shiro:permission="'asset:record:getByUuid'" class="btn btn-table btn-primary btn-rounded" type="button">操作记录</button>
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
                        <tt-simple-input label="备注" v-model="fromModalData.data.remark" type="textarea" required></tt-simple-input>
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

        <!-- 盘点弹出窗 -->
        <tt-modal id="stock-take-modal" title="盘点">
            <form role="form" class="validation">
                <div class="row">
                    <div class="col-sm-12">
                        <tt-simple-input label="名称" v-model="stockTakeData.name" required></tt-simple-input>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <button @click="addStockTake" class="btn btn-sm btn-primary pull-right m-t-n-xs" type="button"><strong>确认</strong></button>
                        <button data-dismiss="modal"  class="btn btn-sm btn-default pull-right m-t-n-xs tt-modal-cancel" type="button"><strong>取消</strong></button>
                    </div>
                </div>
            </form>
        </tt-modal>

        <!-- QRCode -->
        <tt-modal id="QRCode-modal" title="二维码" size="sm">
            <div class="row">
                <div id="qrcodeCanvas" class="col-sm-12"></div>
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
                        uuid:"uuid",
                        customsId:"资产编号",
                        name:"名称",
                        price:"价格",
                        statusName:"状态",
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
                },
                operationRecordData:{
                    title:{
                        $index:"序号",
                        userId:"操作人id",
                        operationTypeName:"操作类型",
                        remark:"备注"
                    },
                    data:[]
                },
                stockTakeData:{
                    conditions:null,
                    name:null
                },
                tree:{
                    assetType:[]
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
            },
            stockTakeModal:function () {
                return new ModalBuilder("#stock-take-modal");
            },
            QRCodeModal:function () {
                return new ModalBuilder("#QRCode-modal");
            }
        },
        created:function () {
            this.getTableList();
            let self = this;
            Server.assetType.getTypeTree.execute(data => {
                self.tree.assetType = data.object;
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
                Server.asset.list.param(self.conditions).execute(data => {
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
                    Server.asset.delete.param("ids",ids).execute(() => self.getTableList());
                });
            },
            showUpdateModal:function (status,obj) {
                this.fromModalData.title = status === 3?"维修":"报废";
                obj.remark = null;
                obj.status = obj.status===status?1:status;
                this.fromModalData.data = JsonUtils.copy(obj);
                this.fromModalData.submit = this.getSubmitFunc(Server.asset.updateStatus);
                this.fromModal.show();
            },
            showOperationRecordModal:function (obj) {
                let self =this;
                Server.asset.getOperationRecordByUuid.param("uuid",obj.uuid).execute((data) =>{
                    self.operationRecordData.data = data.object;
                });
                self.operationRecordModal.show();
            },
            openStockTake:function () {
                let self = this;
                SweetAlertUtils.show("提醒","该操作将当前条件下的资产条目加入到待盘点中，是否继续").sure(function () {
                    self.stockTakeData.conditions = JsonUtils.copy(self.conditions);
                    self.stockTakeModal.show();
                });
            },
            addStockTake:function () {
                let self = this;
                if (ValidationUtils.check(".validation")){
                    Server.asset.addStockTake.body(self.stockTakeData).execute(()=>{
                        self.stockTakeModal.hide();
                    })
                }
            },
            showQRCodeModal:function (obj) {
                $('#qrcodeCanvas').html("");
                $('#qrcodeCanvas').qrcode(obj.uuid);
                this.QRCodeModal.show();
            }
        }
    });

</script>
