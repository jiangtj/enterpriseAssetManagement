const Logical = {
    AND:1,//default
    OR:2
};

const Shiro = {
    roles:{},
    stringPermissions:{},
    init:function (roles,stringPermissions) {
        jQuery.each(roles,function (index,item) {
            Shiro.roles[item.name] = true;
        });
        jQuery.each(stringPermissions,function (index,item) {
            Shiro.stringPermissions[item.name] = true;
        });
    },
    compareObject:function (object,value,logical) {
        //value is str
        if (!jQuery.isArray(value)){
            return Shiro[object][value] === true;
        }
        //logical.OR
        if (logical === logical.OR) {
            jQuery.each(value,function (index,item) {
                if (Shiro[object][item]){
                    return true;
                }
            });
            return false;
        }
        //logical.AND
        jQuery.each(value,function (index,item) {
            if (!Shiro[object][item]){
                return false;
            }
        });
        return true;
    },
    requiresRoles:function (value,logical) {
        Shiro.compareObject("roles",value,logical)
    },
    requiresPermissions:function (value,logical) {
        Shiro.compareObject("stringPermissions",value,logical)
    }
};

Shiro.init(sessionRole,sessionPermission);