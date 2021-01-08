layui.define(['table', 'tripApi'], function(exports) {
    var tripApi = layui.tripApi;
    var table=layui.table;
    var view = {
        init:function(){
        },
        delBatch:function(){
            var data = Common.getMoreDataFromTable(table,"tripTable");
            if(data==null){
                return ;
            }
            Common.openConfirm("确认要删除这些出差申请?",function(){
            var ids =Common.concatBatchId(data,"id");
            tripApi.del(ids,function(){
                Common.info("删除成功");
                    dataReload();
                })
            })
        }
    }
    exports('del',view);
	
});