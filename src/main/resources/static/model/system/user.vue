<template>
    <div>

        <!-- 头部标签 -->
        <header-label :data="headerLabel"></header-label>

        <!-- 动画 -->
        <div class="wrapper wrapper-content animated fadeInRight"><!-- animated -->

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
                                <button data-toggle="modal" data-target="#modal-form" class="btn btn-outline btn-primary" type="button">新增</button>
                                <button class="btn btn-outline btn-primary" type="button">修改</button>
                                <button class="btn btn-outline btn-danger" type="button">删除</button>
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

        <!-- 添加弹出窗 -->
        <tt-modal id="modal-form" title="添加新用户">
            <form role="form" class="validation">
                <div class="row">
                    <div class="col-sm-6 b-r">
                        <h4 class="m-t-none m-b">基本信息</h4>
                        <p>这里的信息很重要,不要乱填.</p>
                        <tt-simple-input label="用户名" v-model="modal.name" required></tt-simple-input>
                        <tt-simple-input label="密码" v-model="modal.password" type="password" required minlength="6"></tt-simple-input>
                    </div>
                    <div class="col-sm-6">
                        <h4>额外 More</h4>
                        <p>个性化的介绍,以后填也可以的.</p>
                        <tt-simple-input label="描述&简介" v-model="modal.describe" type="textarea" row="5" minlength="6"></tt-simple-input>
                        <!--<p class="text-center">
                            <a href=""><i class="fa fa-sign-in big-icon"></i></a>
                        </p>-->
                    </div>
                </div>
                <div class="row">
                    <button @click="put" class="btn btn-sm btn-primary pull-right m-t-n-xs" type="button"><strong>确认</strong></button>
                </div>
            </form>
        </tt-modal>

        <select class="form-control m-b" name="account">
            <option>option 1</option>
            <option>option 2</option>
            <option>option 3</option>
            <option>option 4</option>
        </select>
        <select class="form-control m-b" name="account">
            <option v-for="item in roleMap" :value="item.key">
                {{ item.value }}
            </option>
        </select>
        <!--<select class="form-control m-b" name="account">
            <option v-for="item in App.roleMap" :value="item.key">
                {{ item.value }}
            </option>
        </select>-->
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
                selectModel:{},
                modal:{},
                roleMap:[]
            }
        },
        created:function () {
            this.getTableList(false);
            this.roleMap = App.roleMap;
        },
        mounted:function () {
        },
        methods: {
            clickButton:function (data) {
                alert(data.name+this.selectModel.username);
            },
            getTableList:function (flag) {
                var tableDefaultData = this.tableDefaultData;
                Web.post("user/getList",{
                    defaultHandling:flag,
                    success:function (data) {
                        tableDefaultData.data = data.object.list;
                    }
                })
            },
            put:function () {
                if (ValidationUtils.check(".validation")){
                    Web.post("/user/add",this.modal,function () {
                        $("#modal-form").modal("hide");
                    })
                }
            }
        }
    };

</script>
