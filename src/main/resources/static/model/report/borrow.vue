<template>
    <div>
        <!-- 头部标签 -->
        <header-label :data="headerLabel"></header-label>
        <!-- 动画 -->
        <div class="wrapper wrapper-content animated fadeInRight">

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
                    name: "资产总揽",
                    path: {
                        parent: [
                            {url: "/", name: "Home"},
                            {name: "Report"}
                        ],
                        active: "Overall"
                    }
                },
                pieData:{
                    labels: ["正常","租借","维修","报废"],
                    datasets: [{
                        data: [300,50,100,100],
                        backgroundColor: ["#a3e1d4","#dedede","#b5b8cf","#b7cfc8"]
                    }]
                },
                myPieChart:null
            }
        },
        computed:{
        },
        created:function () {
            let self = this;
        },
        mounted:function () {
            let lineData = {
                labels: ["January", "February", "March", "April", "May", "June", "July"],
                datasets: [

                    {
                        label: "Data 1",
                        backgroundColor: 'rgba(26,179,148,0.5)',
                        borderColor: "rgba(26,179,148,0.7)",
                        pointBackgroundColor: "rgba(26,179,148,1)",
                        pointBorderColor: "#fff",
                        data: [28, 48, 40, 19, 86, 27, 90]
                    },{
                        label: "Data 2",
                        backgroundColor: 'rgba(220, 220, 220, 0.5)',
                        pointBorderColor: "#fff",
                        data: [65, 59, 80, 81, 56, 55, 40]
                    }
                ]
            };

            let lineOptions = {
                responsive: true
            };


            let ctx = document.getElementById("lineChart").getContext("2d");
            new Chart(ctx, {type: 'line', data: lineData, options:lineOptions});
        }
    });
</script>