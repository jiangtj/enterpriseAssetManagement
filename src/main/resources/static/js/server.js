const Server = {
    user:{
        getList:new WebBuilder("/user/getList"),
        add:new WebBuilder("/user/add"),
        delete:new WebBuilder("/user/delete"),
        update:new WebBuilder("/user/update")
    }
};