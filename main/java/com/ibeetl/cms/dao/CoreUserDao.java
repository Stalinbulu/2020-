package com.ibeetl.cms.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.core.engine.PageQuery;

import  com.ibeetl.cms.entity.*;

/**
 * 出差申请 Dao
 */
@SqlResource("cms.trip")
public interface CoreUserDao extends BaseMapper<CoreUser>{
    public PageQuery<CoreUser> queryByCondition(PageQuery query);
    public void batchDelCoreUserByIds( List<Long> ids);
}