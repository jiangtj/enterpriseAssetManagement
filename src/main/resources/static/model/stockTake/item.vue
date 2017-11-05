<template xmlns:v-shiro="http://www.w3.org/1999/xhtml">
    <div>
        <!-- 头部标签 -->
        <header-label :data="headerLabel"></header-label>
        <!-- 动画 -->
        <div class="wrapper wrapper-content animated fadeInRight"><!-- animated -->

            <!-- 表单 -->
            <div class="row"><div class="col-lg-12"><div class="ibox tt-from-table">
                <div class="ibox-content">
                    <form role="form" class="form-inline validation">

                        <tt-simple-input label="任务id" v-model="conditions.stockTakeId" required></tt-simple-input>
                        <tt-simple-input label="uuid" v-model="conditions.uuid"></tt-simple-input>
                        <tt-simple-input label="资产编号" v-model="conditions.customsId"></tt-simple-input>
                        <tt-simple-input label="名称" v-model="conditions.name"></tt-simple-input>
                        <tt-simple-tree-root-v2 label="类型" v-model="conditions.assetsTypeId" :data="tree.assetType" :option="{key:'id',value:'name'}"></tt-simple-tree-root-v2>
                        <tt-simple-select label="网点" v-model="conditions.pointId" :data="Map.point" show-undefined></tt-simple-select>
                        <tt-simple-select label="状态" v-model="conditions.status" :data="Map.stockTakeItemStatus" show-undefined></tt-simple-select>

                        <div class="btn-toolbar pull-right" role="toolbar">
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
                                <button @click="updateToAbnormal(props.row)" v-shiro:permission="'stockTake:updateToAbnormal'"  v-if="props.row.status === 1" class="btn btn-table btn-danger btn-rounded" type="button">标记异常</button>
                            </template>
                        </tt-table>
                    </div>
                    <!-- 分页 -->
                    <tt-pagination :count="pagination.count" @listener="getTablePaginationList" button-size="sm" class="pull-right"></tt-pagination>
                    <div class="clearfix"></div>

                </div>
            </div></div></div>
        </div>

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
                    name:"盘点明显",
                    path:{
                        parent:[
                            {url:"/",name:"Home"},
                            {name:"StockTake"}
                        ],
                        active:"Item"
                    }
                },
                conditions:{
                    stockTakeId:null,
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
            }
        },
        created:function () {
            this.conditions.stockTakeId = App.$route.query.stockTakeId;
            if (this.conditions.stockTakeId) this.getTableList();
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
                if (ValidationUtils.check(".validation")) {
                    Server.stockTake.getItemList.param(self.conditions).execute(data => {
                        self.tableData.data = data.object.list;
                        self.pagination.count = data.object.count;
                        self.initFromEmpty();
                    });
                }
            },
            initFromEmpty:function () {
                let self = this;
                if (!self.fromModalData.empty){
                    let empty = self.tableData.data.length === 0?null:self.tableData.data[0];
                    self.fromModalData.empty = JsonUtils.setNull(empty);
                }
            },
            updateToAbnormal:function (obj) {
                let self = this;
                SweetAlertUtils.show().sure(function () {
                    Server.stockTake.updateToAbnormal.body({id:obj.id}).execute(() => self.getTableList());
                });
            }
        }
    });

</script>
