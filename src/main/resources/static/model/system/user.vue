<div xmlns:v-bind="http://www.w3.org/1999/XSL/Transform">

    <!-- 头部标签 -->
    <header-label></header-label>
    <!--<div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-10">
            <h2>用户管理</h2>
            <ol class="breadcrumb">
                <li><router-link to="/">Home</router-link></li>
                <li><a>System</a></li>
                <li class="active"><strong>User</strong></li>
            </ol>
        </div>
        <div class="col-lg-2"></div>
    </div>-->

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
                    <!--<tt-table v-bind:data="tableDefaultData">
                        <tt-table-column prop="id" name="id"></tt-table-column>
                        <tt-table-column prop="name" name="名称"></tt-table-column>
                        <tt-table-column prop="password" name="密码"></tt-table-column>
                    </tt-table>-->
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th></th>
                            <th>Project </th>
                            <th>Completed </th>
                            <th>Task</th>
                            <th>Date</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><input type="checkbox"  checked class="i-checks icheckbox_square-green" name="input[]"></td>
                            <td>Project<small>This is example of project</small></td>
                            <td><span class="pie">0.52/1.561</span></td>
                            <td>20%</td>
                            <td>Jul 14, 2013</td>
                            <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" class="i-checks" name="input[]"></td>
                            <td>Alpha project</td>
                            <td><span class="pie">6,9</span></td>
                            <td>40%</td>
                            <td>Jul 16, 2013</td>
                            <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" class="i-checks" name="input[]"></td>
                            <td>Betha project</td>
                            <td><span class="pie">3,1</span></td>
                            <td>75%</td>
                            <td>Jul 18, 2013</td>
                            <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" class="i-checks" name="input[]"></td>
                            <td>Gamma project</td>
                            <td><span class="pie">4,9</span></td>
                            <td>18%</td>
                            <td>Jul 22, 2013</td>
                            <td><a href="#"><i class="fa fa-check text-navy"></i></a></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div></div></div>
    </div>

    <my-awesome-list :items="items">
        <!-- 作用域插槽也可以在这里命名 -->
        <template slot="item" scope="props">
            <li class="my-fancy-item">{{ props.text }}</li>
        </template>
    </my-awesome-list>

    <script type="application/javascript">
        //美化复选框
        ICheckUtils.beautifyChecks();
    </script>

</div>


<!--
<script type="application/javascript">

    //头部标签数据
    headerLabel.setData({
        name:"用户管理",
        path:{
            parent:[
                {url:"/",name:"Home"},
                {name:"System"}
            ],
            active:"User"
        }
    });

    //定义表格数据
    RouteData.tableDefaultData={
        title:{
            id:"id",
            name:"名称",
            password:"密码"
        },
        data:[
            {id:1,name:"dfbdf",password:"fdg"},
            {id:22,name:"scvbs1",password:"s"},
            {id:88,name:"rg",password:"s"}
        ]
    };

</script>-->
