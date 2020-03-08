
layui.use(['jquery','layer'],function(){
    var $ = layui.jquery;
    var layer = layui.layer;

    /**
     * 这是查询日志权限
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
});