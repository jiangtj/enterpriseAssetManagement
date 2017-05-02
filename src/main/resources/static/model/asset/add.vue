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
                            <label class="col-sm-2 control-label">资产名称</label>
                            <div class="col-sm-10"><input v-model="data.name" type="text" class="form-control" required></div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">价格</label>
                            <div class="col-sm-10"><input v-model="data.price" type="text" class="form-control" required></div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">类型</label>
                            <div class="col-sm-10"><tt-simple-tree-children v-model="data.assetsTypeId" :data="getTypeMapById(0)" :func="getTypeMapById"></tt-simple-tree-children></div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">所属网点</label>
                            <div class="col-sm-10">
                                <select v-model="data.pointId" class="form-control" required>
                                    <option :value="undefined">---- 请选择 ----</option>
                                    <option v-for="item in Map.point" :value="item.key">{{ item.value }}</option>
                                </select>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">资产编号</label>
                            <div class="col-sm-10">
                                <input v-model="data.customsId" type="text" class="form-control" required>
                                <span class="help-block m-b-none">资产编号是由用户提供的，便于系统内部uuid编号丢失时，定位物品，因此请尽量保持其唯一性.</span>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-2">
                                <button @click="add" class="btn btn-primary" type="button">提交</button>
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
                    name: "资产添加",
                    path: {
                        parent: [
                            {url: "/", name: "Home"},
                            {name: "Asset"}
                        ],
                        active: "Add"
                    }
                },
                data:{
                    name:null,
                    price:null,
                    assetsTypeId:null,
                    pointId:null,
                    customsId:null,
                }
            }
        },
        methods:{
            add:function () {
                let self = this;
                if (ValidationUtils.check(".validation")){
                    Server.asset.add.setData(self.data).post(() => {
                        self.clear();
                    })
                }
            },
            clear:function () {
                let self = this;
                self.data.customsId = null;
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