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
                            <label class="col-sm-2 control-label">借/还人id</label>
                            <div class="col-sm-10">
                                <select id="chosen-user" v-model="data.userId" class="form-control select2-ajax" required>
                                    <option>---- 请选择 ----</option>
                                </select>
                                <!--<input name="userId" v-model="data.userId" type="text" class="form-control" required>-->
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">预期/归还时间</label>
                            <div class="col-sm-10">
                                <!--<span class="input-group-addon"><i class="fa fa-calendar"></i></span>-->
                                <input id="expect-return-time" name="time" type="text" class="form-control datepicker">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">uuid</label>
                            <div class="col-sm-10">
                                <input name="uuid" v-model="data.asset.uuid" type="text" class="form-control">
                                <span class="help-block m-b-none">uuid与之下资产信息选填其一.</span>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">资产编号</label>
                            <div class="col-sm-10">
                                <input name="customsId" v-model="data.asset.customsId" type="text" class="form-control">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">资产名称</label>
                            <div class="col-sm-10"><input name="name" v-model="data.asset.name" type="text" class="form-control"></div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">类型</label>
                            <div class="col-sm-10"><tt-simple-tree-children name="assetsTypeId" v-model="data.asset.assetsTypeId" :data="getTypeMapById(0)" :func="getTypeMapById"></tt-simple-tree-children></div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">所属网点</label>
                            <div class="col-sm-10">
                                <select name="pointId" v-model="data.asset.pointId" class="form-control">
                                    <option :value="undefined">---- 请选择 ----</option>
                                    <option v-for="item in Map.point" :value="item.key">{{ item.value }}</option>
                                </select>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">备注</label>
                            <div class="col-sm-10">
                                <input name="customsId" v-model="data.remark" type="text" class="form-control">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-2">
                                <button @click="assetBorrow" class="btn btn-primary" type="button">借用</button>
                                <button @click="assetReturn" class="btn btn-default" type="button">归还</button>
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
                    name: "借还登记",
                    path: {
                        parent: [
                            {url: "/", name: "Home"},
                            {name: "Asset"}
                        ],
                        active: "Borrow"
                    }
                },
                data:{}
            }
        },
        created:function () {
            this.clear();
        },
        mounted:function () {
            let self = this;
            $('.datepicker').datepicker({
                todayBtn: "linked",
                format: 'yyyy-mm-dd',
                keyboardNavigation: false,
                forceParse: false,
                calendarWeeks: true,
                autoclose: true
            });
            $('.select2-ajax').select2({
                ajax: {
                    url: Web.buildUrl("/borrow/users"),
                    data: function (params) {
                        if (!params.term) return;
                        return {
                            name: params.term
                        };
                    },
                    processResults: function (data) {
                        if (!data.object) return {};
                        return {
                            results:$.map(data.object,function (item) {
                                return {
                                    id:item.key,
                                    text:item.value
                                }
                            })
                        }
                    }
                }
            }).on('select2:select', function (e) {
                let data = e.params.data;
                self.data.userId = data.id;
            });
        },
        methods:{
            assetBorrow:function () {
                let self = this;
                if ((self.data.asset.uuid === null || self.data.asset.uuid === "")&&
                    (self.data.asset.customsId === null || self.data.asset.customsId === "")){
                    ToastrUtils.show("uuid与资产编号不能同时为空！","",9);
                    return;
                }
                self.data.expectReturnTime = $('#expect-return-time').val();
                if (ValidationUtils.check(".validation")) {
                    Server.asset.borrowAsset.body(self.data).execute(() => {
                        self.clear();
                    });
                }
            },
            assetReturn:function () {
                let self = this;
                if ((self.data.asset.uuid === null || self.data.asset.uuid === "")&&
                    (self.data.asset.customsId === null || self.data.asset.customsId === "")){
                    ToastrUtils.show("uuid与资产编号不能同时为空！","",9);
                    return;
                }
                let date = $('.datepicker').datepicker('getDate');
                self.data.returnTime = date.getTime();
                if (ValidationUtils.check(".validation")) {
                    Server.asset.returnAsset.body(self.data).execute(() => {
                        self.clear();
                    });
                }
            },
            clear:function () {
                this.data = {
                    userId:null,
                    expectReturnTime:null,
                    remark:null,
                    returnTime:null,
                    asset:{
                        uuid:null,
                        name:null,
                        assetsTypeId:null,
                        pointId:null,
                        customsId:null,
                    }
                }
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