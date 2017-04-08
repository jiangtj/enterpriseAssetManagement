<template>
    <div>
        <!-- 头部标签 -->
        <header-label :data="headerLabel"></header-label>

        <!-- 动画 -->
        <div class="wrapper wrapper-content animated fadeInRight">

            <!-- 表单 -->
            <div class="row"><div class="col-lg-12"><div class="ibox tt-from-table">
                <div class="ibox-content">
                    <form role="form" class="form-inline">
                        <div class="form-group tt-from-input">
                            <label for="exampleInputEmail2">Email address</label>
                            <input type="email" placeholder="Enter email" id="exampleInputEmail2"
                                   class="form-control">
                        </div>
                        <div class="form-group tt-from-input">
                            <label for="exampleInputPassword2">Password</label>
                            <input type="password" placeholder="Password" id="exampleInputPassword2"
                                   class="form-control">
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
                        <tt-table v-bind:data="tableDefaultData">
                            <th slot="tt-title-id">wo</th>
                            <template slot="tt-body-id" scope="props">
                                {{props.index}}-{{props.row.id}}
                            </template>
                        </tt-table>
                    </div>
                </div>
            </div></div></div>
        </div>
    </div>
</template>

<script type="application/javascript">

    //路由配置
    RouteConfig.deploy({
        data:function () {
            return {
                headerLabel:{
                    name:"角色管理",
                    path:{
                        parent:[
                            {url:"/",name:"Home"},
                            {name:"System"}
                        ],
                        active:"Role"
                    }
                },
                tableDefaultData:{
                    title:{
                        id:"id",
                        name:"角色名",
                        password:"权限值"
                    },
                    data:[
                        {id:1,name:"aaa",password:"fff"},
                        {id:22,name:"bbb",password:"ggg"},
                        {id:88,name:"ccc",password:"aaa"},
                        {id:88,name:"ddd",password:"hyhh"}
                    ]
                }
            }
        },
        created:function () {
        },
        mounted:function () {
        },
        methods: {
        }
    });

</script>
