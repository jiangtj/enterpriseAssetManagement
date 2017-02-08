<div>

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

    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-lg-12">
                <div class="ibox from-table-margins">
                    <!--<div class="ibox-title">
                        <h5>Custom responsive table </h5>
                    </div>-->
                    <div class="ibox-content">
                        <form role="form" class="form-inline">
                            <div class="form-group">
                                <label for="exampleInputEmail2">Email address</label>
                                <input type="email" placeholder="Enter email" id="exampleInputEmail2"
                                       class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword2">Password</label>
                                <input type="password" placeholder="Password" id="exampleInputPassword2"
                                       class="form-control">
                            </div>
                            <button class="btn btn-white" type="submit">Sign in</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <!--<div class="ibox-title">
                        <h5>Custom responsive table </h5>
                    </div>-->
                    <div class="ibox-content">
                        <div class="table-responsive">
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
                </div>
            </div>
        </div>
    </div>

    <script type="application/javascript">
        //美化复选框
        ICheckUtils.beautifyChecks();

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
        })

    </script>

</div>