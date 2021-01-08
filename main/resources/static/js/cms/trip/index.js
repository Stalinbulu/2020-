layui.define([ 'form', 'laydate', 'table' ], function(exports) {
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;
    var tripTable = null;
    var view ={
        init:function(){
            this.initTable();
            this.initSearchForm();
            this.initToolBar();
            window.dataReload = function(){
                Lib.doSearchForm($("#searchForm"),tripTable)
            }
        },
        initTable:function(){
            tripTable = table.render({
                elem : '#tripTable',
                height : Lib.getTableHeight(1),
                cellMinWidth: 100,
                method : 'post',
                url : Common.ctxPath + '/cms/trip/list.json' // 数据接口
                ,page : Lib.tablePage // 开启分页
                ,limit : 10,
                cols : [ [ // 表头
                    {
                        type : 'checkbox',
                        fixed:'left',
                    },
                {

                    field : 'id', 
                        title : '编号',
                    fixed:'left',
                        width : 60,
                },
                {

                    field : 'code', 
                        title : '原因',
                },
                {

                    field : 'name', 
                        title : '名称',
                },
                {

                    field : 'password', 
                        title : 'password',
                },
                {

                    field : 'createTime', 
                        title : 'createTime',
                },
                {

                    field : 'orgId', 
                        title : 'orgId',
                },
                {

                    field : 'state', 
                        title : '用户状态 1:启用 0:停用',
                },
                {

                    field : 'jobType1', 
                        title : 'jobType1',
                },
                {

                    field : 'updateTime', 
                        title : 'updateTime',
                },
                {

                    field : 'jobType0', 
                        title : 'jobType0',
                },

        ] ]

        });

            table.on('checkbox(tripTable)', function(obj){
                var trip = obj.data;
                if(obj.checked){
                    //按钮逻辑Lib.buttonEnable()
                }else{

                }
            })
        },

        initSearchForm:function(){
            Lib.initSearchForm( $("#searchForm"),tripTable,form);
        },
        initToolBar:function(){
            toolbar = {
                add : function() { // 获取选中数据
                    var url = "/cms/trip/add.do";
                    Common.openDlg(url,"出差申请管理>新增");
                },
                edit : function() { // 获取选中数目
                    var data = Common.getOneFromTable(table,"tripTable");
                    if(data==null){
                        return ;
                    }
                    var url = "/cms/trip/edit.do?id="+data.id;
                    Common.openDlg(url,"出差申请管理>"+data.name+">编辑");
                },
                del : function() {
                    layui.use(['del'], function(){
                        var delView = layui.del
                        delView.delBatch();
                    });
                }
        };
            $('.ext-toolbar').on('click', function() {
                var type = $(this).data('type');
                toolbar[type] ? toolbar[type].call(this) : '';
            });
        }
    }
    exports('index',view);

});