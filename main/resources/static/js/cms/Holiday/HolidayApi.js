/*访问后台的代码*/
layui.define([], function(exports) {
    var api={
            updateHoliday:function(form,callback){
                Lib.submitForm("/cms/Holiday/edit.json",form,{},callback)
            },
            addHoliday:function(form,callback){
                Lib.submitForm("/cms/Holiday/add.json",form,{},callback)
            },
            del:function(ids,callback){
                Common.post("/cms/Holiday/delete.json",{"ids":ids},function(){
                    callback();
                })
            }
		
    };
    exports('HolidayApi',api);
});