<template>
    <div>
        <!-- 头部标签 -->
        <header-label :data="headerLabel"></header-label>
        <!-- 动画 -->
        <div class="wrapper wrapper-content animated fadeInRight">

            <!-- 表单 -->
            <div class="row"><div class="col-lg-12"><div class="ibox tt-from-table">
                <div class="ibox-content">
                    <form role="form" class="form-inline validation">

                        <tt-simple-input id="time1" class="datepicker" label="开始时间" v-model="conditions.time1" required></tt-simple-input>
                        <tt-simple-input id="time2" class="datepicker " label="结束时间" v-model="conditions.time2" required></tt-simple-input>

                        <div class="btn-toolbar pull-right" role="toolbar">
                            <div class="btn-group">
                                <button @click="getTableList" class="btn btn-primary" type="button">搜索</button>
                            </div>
                        </div>
                    </form>
                    <div class="clearfix"></div>
                </div>
            </div></div></div>

            <!-- 图表 -->
            <div class="row"><div class="col-lg-12"><div class="ibox">
                <div class="ibox-content">
                    <div>
                        <canvas id="lineChart" height="140"></canvas>
                    </div>
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
                    name: "借还报表",
                    path: {
                        parent: [
                            {url: "/", name: "Home"},
                            {name: "Report"}
                        ],
                        active: "Borrow"
                    }
                },
                conditions:{},
                chartData:{
                    labels: ["null"],
                    datasets: [
                        {
                            label: "借用数",
                            backgroundColor: 'rgba(26,179,148,0.5)',
                            borderColor: "rgba(26,179,148,0.7)",
                            pointBackgroundColor: "rgba(26,179,148,1)",
                            pointBorderColor: "#fff",
                            data: [0]
                        },{
                            label: "归还数",
                            backgroundColor: 'rgba(220, 220, 220, 0.5)',
                            pointBorderColor: "#fff",
                            data: [0]
                        }
                    ]
                },
                chart:null
            }
        },
        computed:{
        },
        created:function () {
            let self = this;
        },
        mounted:function () {
            let self = this;

            $('.datepicker input').datepicker({
                todayBtn: "linked",
                format: 'yyyy-mm-dd',
                keyboardNavigation: false,
                forceParse: false,
                calendarWeeks: true,
                autoclose: true
            });

            let lineOptions = {
                responsive: true
            };
            let ctx = document.getElementById("lineChart").getContext("2d");
            self.chart = new Chart(ctx, {type: 'line', data: self.chartData, options:lineOptions});
        },
        methods: {
            getTableList: function () {
                let self = this;
                if (ValidationUtils.check(".validation")) {
                    self.conditions = {
                        //startTime: $('#time1 input').datepicker('getDate').getTime(),
                        startTime: $('#time1 input').val(),
                        endTime: $('#time2 input').val()
                    };
                    Server.report.getBorrow.param(self.conditions).execute(data => {
                        self.chartData.labels = $.map(data.object,(item) => item.date);
                        self.chartData.datasets[0].data = $.map(data.object,(item) => item.borrowNum);
                        self.chartData.datasets[1].data = $.map(data.object,(item) => item.returnNum);
                        self.chart.update();
                    });
                }
            },
        }
    });
</script>