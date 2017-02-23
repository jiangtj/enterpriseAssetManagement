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

                        <tt-simple-input label="用户名" v-model="selectModel.username"></tt-simple-input>
                        <tt-simple-input label="角色" v-model="selectModel.role"></tt-simple-input>

                        <div class="btn-toolbar pull-right" role="toolbar">
                            <div class="btn-group">
                                <button class="btn btn-primary" type="button">新增</button>
                                <button class="btn btn-primary" type="button">修改</button>
                                <button class="btn btn-danger" type="button">删除</button>
                            </div>
                            <div class="btn-group">
                                <button @click="getTableList" class="btn btn-primary" type="button">搜索</button>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </form>
                </div>
            </div></div></div>

            <!-- 表格 -->
            <div class="row"><div class="col-lg-12"><div class="ibox float-e-margins">
                <div class="ibox-content">
                    <div class="table-responsive">
                        <tt-table v-bind:data="tableDefaultData" :selection = "true" v-model="tableSelectData">
                            <template slot="tt-body-roleName" scope="props">
                                {{props.row.role.name}}
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
        <div>{{selectModel}}</div>

        <div class="clearfix"></div>

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
                        $index:"序号",
                        id:"用户id",
                        name:"名称",
                        password:"密码",
                        roleName:"角色名称",
                        operation:{name:"操作",width:"100px"}
                    },
                    data:[]
                },
                tableSelectData:[],
                selectModel:{}
            }
        },
        created:function () {
            this.getTableList(false);
        },
        mounted:function () {
        },
        methods: {
            clickButton:function (data) {
                alert(data.name+this.selectModel.username);
            },
            getTableList:function (flag) {
                if (flag == null) flag =true;
                var tableDefaultData = this.tableDefaultData;
                Web.post("user/getList",{
                    defaultHandling:flag,
                    success:function (data) {
                        tableDefaultData.data = data.object.list;
                    }
                })
            }
        }
    };

</script>
