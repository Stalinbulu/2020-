queryByCondition
===


    select 
    @pageTag(){
    t.*
    @}
    from core_user t
    where 1=1  
    @//数据权限，该sql语句功能点,如果不考虑数据权限，可以删除此行  
    and #function("Holiday.query")#
    @if(!isEmpty(id)){
        and  t.ID =#id#
    @}
    @if(!isEmpty(code)){
        and  t.CODE =#code#
    @}
    @if(!isEmpty(name)){
        and  t.NAME =#name#
    @}
    @if(!isEmpty(createTime)){
        and  t.CREATE_TIME =#createTime#
    @}
    
    
    

batchDelCoreUserByIds
===

* 批量逻辑删除

    update core_user set del_flag = 1 where ID  in( #join(ids)#)
    
