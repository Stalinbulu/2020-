/*访问后台的代码*/
layui.define([], function(exports) {
    var api={
            updateTrip:function(form,callback){
                Lib.submitForm("/cms/trip/edit.json",form,{},callback)
            },
            addTrip:function(form,callback){
                Lib.submitForm("/cms/trip/add.json",form,{},callback)
            },
            del:function(ids,callback){
                Common.post("/cms/trip/delete.json",{"ids":ids},function(){
                    callback();
                })
            }
		
    };
    exports('tripApi',api);
});