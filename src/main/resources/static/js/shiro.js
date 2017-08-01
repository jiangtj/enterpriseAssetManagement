const Logical = {
    AND:1,//default
    OR:2
};

const Shiro = {
    roles:{},
    stringPermissions:{},
    init:function (roles,stringPermissions) {
        jQuery.each(roles,function (index,item) {
            Shiro.roles[item] = true;
        });
        jQuery.each(stringPermissions,function (index,item) {
            Shiro.stringPermissions[item] = true;
        });
    },
    compareObject:function (object,value,logical) {
        //null
        if (value === undefined || value === null) return true;
        //value is str
        if (!jQuery.isArray(value)){
            return Shiro[object][value] === true;
        }
        //logical.OR
        if (logical === Logical.OR) {
            for (let i in value){
                if (Shiro[object][value[i]]){
                    return true;
                }
            }
            return false;
        }
        //logical.AND
        for (let i in value){
            if (!Shiro[object][value[i]]){
                return false;
            }
        }
        return true;
    },
    requiresRoles:function (value,logical) {
        return Shiro.compareObject("roles",value,logical)
    },
    requiresPermissions:function (value,logical) {
        return Shiro.compareObject("stringPermissions",value,logical)
    }
};

const ShiroVue = function (el, binding) {
    let innerLogical = binding.modifiers.or?Logical.OR:Logical.AND;
    let flag = true;
    switch (binding.arg) {
        case "role":
            flag = Shiro.requiresRoles(binding.value,innerLogical);
            break;
        case "permission":
            flag = Shiro.requiresPermissions(binding.value,innerLogical);
            break;
    }
    el.style.display = flag?"inherit":"none";
};

if (Vue !== undefined) Vue.directive("shiro",ShiroVue);

Shiro.init(sessionRole,sessionPermission);