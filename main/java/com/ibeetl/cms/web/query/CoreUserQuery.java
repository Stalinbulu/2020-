package com.ibeetl.cms.web.query;

import org.apache.commons.lang3.StringUtils;
import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.util.Tool;
import com.ibeetl.admin.core.util.enums.CoreDictType;
import com.ibeetl.admin.core.web.query.PageParam;
import java.util.Date;
/**
 *出差申请查询
 */
public class CoreUserQuery extends PageParam {
    @Query(name = "编号", display = true)
    private Integer id;
    @Query(name = "原因", display = true)
    private String code;
    @Query(name = "名称", display = true)
    private String name;
    public Integer getId(){
        return  id;
    }
    public void setId(Integer id ){
        this.id = id;
    }
    public String getCode(){
        return  code;
    }
    public void setCode(String code ){
        this.code = code;
    }
    public String getName(){
        return  name;
    }
    public void setName(String name ){
        this.name = name;
    }
 
}
