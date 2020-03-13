
layui.use(['jquery','layer'],function(){
    var $ = layui.jquery;
    var layer = layui.layer;

    /**
     * 这是查询日志权限(查询创建账号记录)
     */
    $("#createAccountLogs").on("click",function(){
        $.ajax({
            type:"get",
            url:"/createAccountLogsAJAX",
            success:function (data,message) {
                if(data.code == 1000){
                    window.location.href="/createAccountLogsPage";
                }else if(data.code == -1){
                    layer.msg(data.message,{icon:2,anim:6})
                }
            },
            error:function(data){
                layer.msg("网络错误稍后重试",{icon:2,anim:6})
            }
        });
    });

    /**
     * 这是查询用户信息权限
     */
    $("#selectUserInfo").on("click",function(){
        $.ajax({
            type:"get",
            url:"/selectUserInfoAJAX",
            success:function (data,message) {
                if(data.code == 1000){
                    window.location.href="/selectUserInfoPage";
                }else if(data.code == -1){
                    layer.msg(data.message,{icon:2,anim:6})
                }
            },
            error:function(data){
                layer.msg("网络错误稍后重试",{icon:2,anim:6})
            }
        });
    });

    /**
     * 这是查询处理违规账号权限
     */
    $("#manageUser").on("click",function(){
        $.ajax({
            type:"get",
            url:"/manageUserAJAX",
            success:function (data,message) {
                if(data.code == 1000){
                    window.location.href="/manageUserPage";
                }else if(data.code == -1){
                    layer.msg(data.message,{icon:2,anim:6})
                }
            },
            error:function(data){
                layer.msg("网络错误稍后重试",{icon:2,anim:6})
            }
        });
    });
    /**
     * 这是查询日志权限(查看用户记录)
     */
    $("#userLogs").on("click",function(){
        $.ajax({
            type:"get",
            url:"/createAccountLogsAJAX",
            success:function (data,message) {
                if(data.code == 1000){
                    window.location.href="/userLogsPage";
                }else if(data.code == -1){
                    layer.msg(data.message,{icon:2,anim:6})
                }
            },
            error:function(data){
                layer.msg("网络错误稍后重试",{icon:2,anim:6})
            }
        });
    });
});