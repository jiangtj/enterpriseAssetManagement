<template>
    <div>
        <div class="wrapper wrapper-content animated fadeInRight">
            <div class="row">
                <div class="col-lg-12">
                    <div class="text-center m-t-lg">
                        <h1>
                            企业固定资产管理系统
                        </h1>
                        <small>
                            从购置、领用、盘点、借用归还、维修到报废进行全方位准确监管，以“快捷”、“精准”和功能全面为优势的管理类系统.
                        </small>
                        <h3>
                            <a href="/swagger-ui.html">接口文档</a>
                        </h3>
                        <h3>
                            <a href="https://wrapbootstrap.com/theme/inspinia-responsive-admin-theme-WB0R5L90S">前端页面模版地址</a>
                        </h3>
                    </div>
                    <div class="text-center m-t-lg">
                    </div>
                </div>
            </div>


            <div class="row">
                <div v-for="item in borrow" class="col-lg-3">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <span class="label label-success pull-right">{{item.expectReturnTime}}</span>
                            <h5>{{item.asset.assetsTypeName}}</h5>
                        </div>
                        <div class="ibox-content">
                            <h1 class="no-margins">{{item.asset.name}}</h1>
                            <!--<div class="stat-percent font-bold text-success">在借</div>-->
                            <small>{{item.remark}}</small>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>租借</h5>
                        </div>
                        <div @click="showBorrowModal" class="ibox-content">
                            <h1 class="no-margins"><i class="fa fa-plus"></i></h1>
                            <div class="stat-percent font-bold text-success"></div>
                            <small>点击租借物资</small>
                        </div>
                    </div>
                </div>
            </div>

        </div>



        <!-- 租借弹出窗 -->
        <tt-modal id="index-borrow-modal" :title="borrowModalData.title" size="sm">
            <form role="form" class="validation">
                <div class="row">
                    <div class="col-sm-12"><!--<div class="col-sm-6 b-r">-->
                        <h4 class="m-t-none m-b">基本信息</h4>
                        <div class="form-group tt-from-input">
                            <label>预期时间</label>
                            <div>
                                <input id="expect-return-time" name="time" type="text" class="form-control datepicker">
                            </div>
                        </div>
                        <tt-simple-input label="uuid" v-model="borrowModalData.data.asset.uuid" required></tt-simple-input>
                    </div>
                    <!--<div class="col-sm-6">
                        <h4>权限配置</h4>
                    </div>-->
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <button @click="borrowModalData.submit" class="btn btn-sm btn-primary pull-right m-t-n-xs" type="button"><strong>确认</strong></button>
                        <button data-dismiss="modal"  class="btn btn-sm btn-default pull-right m-t-n-xs tt-modal-cancel" type="button"><strong>取消</strong></button>
                    </div>
                </div>
            </form>
        </tt-modal>

    </div>
</template>


<script type="application/javascript">
    //路由配置
    RouteConfig.deploy({
        data: function () {
            return {
                borrow:[],
                borrowModalData:{
                    title:"租借",
                    data:{
                        uuid:null,
                        asset:{

                        }
                    },
                    empty:null,
                    submit:function () {}
                }
            }
        },
        computed:{
            borrowModal:function () {
                return new ModalBuilder("#index-borrow-modal");
            }
        },
        created:function () {
            this.getBorrowItemInfo();
        },
        mounted:function () {
            $('.datepicker').datepicker({
                todayBtn: "linked",
                format: 'yyyy-mm-dd',
                keyboardNavigation: false,
                forceParse: false,
                calendarWeeks: true,
                autoclose: true
            })
        },
        methods: {
            getBorrowItemInfo:function () {
                let self = this;
                Server.borrow.me.execute(data => self.borrow = data.object);
            },
            showBorrowModal:function () {
                let self = this;
                self.borrowModalData.submit = function () {
                    self.borrowModalData.data.expectReturnTime = $('#expect-return-time').val();
                    Server.borrow.borrowBySelf.body(self.borrowModalData.data).execute(data => {
                        self.getBorrowItemInfo();
                        self.borrowModal.hide();
                    });
                };
                self.borrowModal.show();
            }
        }
    });
</script>