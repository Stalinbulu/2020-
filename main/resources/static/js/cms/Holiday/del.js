layui.define(['table', 'HolidayApi'], function(exports) {
    var HolidayApi = layui.HolidayApi;
    var table=layui.table;
    var view = {
        init:function(){
        },
        delBatch:function(){
            var data = Common.getMoreDataFromTable(table,"HolidayTable");
            if(data==null){
                return ;
            }
            Common.openConfirm("确认要删除这些申请假期?",function(){
            var ids =Common.concatBatchId(data,"id");
            HolidayApi.del(ids,function(){
                Common.info("删除成功");
                    dataReload();
                })
            })
        }
    }
    exports('del',view);
	
});