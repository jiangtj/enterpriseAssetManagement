<template>
    <div>
        <!-- 头部标签 -->
        <header-label :data="headerLabel"></header-label>
        <!-- 动画 -->
        <div class="wrapper wrapper-content animated fadeInRight">

            <div class="row"><div class="col-lg-12"><div class="ibox">
                <div class="ibox-content">
                    <form method="get" class="form-horizontal validation">

                        <div class="form-group">
                            <label class="col-sm-2 control-label">盘点任务id</label>
                            <div class="col-sm-10">
                                <select name="pointId" v-model="data.stockTakeId" class="form-control" required>
                                    <option :value="undefined">---- 请选择 ----</option>
                                    <option v-for="item in Map.stockTake" :value="item.key">{{ item.value }}</option>
                                </select>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">uuid</label>
                            <div class="col-sm-10">
                                <input name="uuid" v-model="data.uuid" type="text" class="form-control">
                                <span class="help-block m-b-none">uuid与之下资产信息选填其一.</span>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">资产编号</label>
                            <div class="col-sm-10">
                                <input name="customsId" v-model="data.customsId" type="text" class="form-control">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">资产名称</label>
                            <div class="col-sm-10"><input name="name" v-model="data.name" type="text" class="form-control"></div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">类型</label>
                            <div class="col-sm-10"><tt-simple-tree-children name="assetsTypeId" v-model="data.assetsTypeId" :data="getTypeMapById(0)" :func="getTypeMapById"></tt-simple-tree-children></div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">所属网点</label>
                            <div class="col-sm-10">
                                <select name="pointId" v-model="data.pointId" class="form-control">
                                    <option :value="undefined">---- 请选择 ----</option>
                                    <option v-for="item in Map.point" :value="item.key">{{ item.value }}</option>
                                </select>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-2">
                                <button @click="submit" class="btn btn-primary" type="button">提交</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div></div></div>

        </div>
    </div>
</template>

<script type="application/javascript">
    //路由配置
    RouteConfig.deploy({
        data: function () {
            return {
                headerLabel: {
                    name: "盘点",
                    path: {
                        parent: [
                            {url: "/", name: "Home"},
                            {name: "StockTake"}
                        ],
                        active: "Handle"
                    }
                },
                data:{
                    stockTakeId:null,
                    uuid:null,
                    name:null,
                    assetsTypeId:null,
                    pointId:null,
                    customsId:null
                }
            }
        },
        created:function () {
        },
        mounted:function () {
        },
        methods:{
            submit:function () {
                let self = this;
                if ((self.data.uuid === null || self.data.uuid === "")&&
                    (self.data.customsId === null || self.data.customsId === "")){
                    ToastrUtils.show("uuid与资产编号不能同时为空！","",9);
                    return;
                }
                if (ValidationUtils.check(".validation")) {
                    Server.stockTake.handle.body(self.data).execute(() => {
                        self.clear();
                    });
                }
            },
            clear:function () {
                this.data.uuid = null;
                this.data.customsId = null;
            },
            getTypeMapById:function (id) {
                let self;
                Server.assetType.getMapByPid.param("pid",id).setAsync(false).execute((data) => {
                    self = data.object;
                });
                return self;
            }
        }
    });
</script>