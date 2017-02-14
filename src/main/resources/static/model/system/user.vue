<template>
    <div>

        <!-- 头部标签 -->
        <header-label :data="headerLabel"></header-label>

        <!-- 动画 -->
        <div class="wrapper wrapper-content animated fadeInRight">

            <!-- 表单 -->
            <div class="row"><div class="col-lg-12"><div class="ibox tt-from-table">
                <!--<div class="ibox-title">
                    <h5>Custom responsive table </h5>
                </div>-->
                <div class="ibox-content">
                    <form role="form" class="form-inline">
                        <div class="form-group tt-from-input">
                            <label>Email address</label>
                            <input type="text" placeholder="Enter email" class="form-control">
                        </div>
                        <div class="form-group tt-from-input">
                            <label>Password</label>
                            <input type="text" placeholder="Password" class="form-control">
                        </div>
                        <button class="btn btn-white pull-right" type="submit">Sign in</button>
                        <div class="clearfix"></div>
                    </form>
                </div>
            </div></div></div>

            <!-- 表格 -->
            <div class="row"><div class="col-lg-12"><div class="ibox float-e-margins">
                <div class="ibox-content">
                    <div class="table-responsive">
                        <tt-table v-bind:data="tableDefaultData" :selection = "true" v-model="tableSelectData">
                            <th slot="tt-title-id">wo</th>
                            <template slot="tt-body-id" scope="props">
                                {{props.index}}-{{props.row.id}}
                            </template>
                            <template slot="tt-body-operation" scope="props">
                                <button @click="clickButton(props.row)">展示名称</button>
                            </template>
                        </tt-table>
                    </div>
                </div>
            </div></div></div>
        </div>

        <div>{{tableSelectData}}</div>

    </div>
</template>

<script type="application/javascript">

    //路由配置
    RouteConfig = {
        data:function () {
            return {
                headerLabel:{
                    name:"用户管理",
                    path:{
                        parent:[
                            {url:"/",name:"Home"},
                            {name:"System"}
                        ],
                        active:"User"
                    }
                },
                tableDefaultData:{
                    title:{
                        id:"id",
                        name:"名称",
                        password:"密码",
                        $index:"序号",
                        operation:{name:"操作",width:"100px"}
                    },
                    data:[
                        {id:1,name:"dfbdf",password:"fdg"},
                        {id:22,name:"scvbs1",password:"s"},
                        {id:88,name:"rg",password:"s"}
                    ]
                },
                tableSelectData:[]
            }
        },
        mounted:function () {
        },
        methods: {
            clickButton:function (data) {
                alert(data.name);
            }
        }
    };

</script>
