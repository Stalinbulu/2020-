package com.ibeetl.cms.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.beetl.sql.core.engine.PageQuery;
import org.jxls.common.Context;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.ReaderConfig;
import org.jxls.reader.XLSReadMessage;
import org.jxls.reader.XLSReadStatus;
import org.jxls.reader.XLSReader;
import org.jxls.util.JxlsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ibeetl.admin.console.web.dto.DictExcelImportData;
import com.ibeetl.admin.console.web.query.UserQuery;
import com.ibeetl.admin.core.annotation.Function;
import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.entity.CoreDict;
import com.ibeetl.admin.core.entity.CoreUser;
import com.ibeetl.admin.core.file.FileItem;
import com.ibeetl.admin.core.file.FileService;
import com.ibeetl.admin.core.web.JsonResult;
import com.ibeetl.admin.core.util.*;
import com.ibeetl.cms.entity.*;
import com.ibeetl.cms.service.*;
import com.ibeetl.cms.web.query.*;

/**
 * 出差申请 接口
 */
@Controller
public class CoreUserController{

    private final Log log = LogFactory.getLog(this.getClass());
    private static final String MODEL = "/cms/trip";


    @Autowired private CoreUserService tripService;
    
    @Autowired
    FileService fileService;
    /* 页面 */

    @GetMapping(MODEL + "/index.do")
    @Function("cms.trip.query")
    @ResponseBody
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/cms/trip/index.html") ;
        view.addObject("search", CoreUserQuery.class.getName());
        return view;
    }

    @GetMapping(MODEL + "/edit.do")
    @Function("cms.trip.edit")
    @ResponseBody
    public ModelAndView edit(Integer id) {
        ModelAndView view = new ModelAndView("/cms/trip/edit.html");
        CoreUser trip = tripService.queryById(id);
        view.addObject("trip", trip);
        return view;
    }

    @GetMapping(MODEL + "/add.do")
    @Function("cms.trip.add")
    @ResponseBody
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/cms/trip/add.html");
        return view;
    }

    /* ajax json */

    @PostMapping(MODEL + "/list.json")
    @Function("cms.trip.query")
    @ResponseBody
    public JsonResult<PageQuery> list(CoreUserQuery condtion)
    {
        PageQuery page = condtion.getPageQuery();
        tripService.queryByCondition(page);
        return JsonResult.success(page);
    }

    @PostMapping(MODEL + "/add.json")
    @Function("cms.trip.add")
    @ResponseBody
    public JsonResult add(@Validated(ValidateConfig.ADD.class)CoreUser trip)
    {
        tripService.save(trip);
        return new JsonResult().success();
    }

    @PostMapping(MODEL + "/edit.json")
    @Function("cms.trip.edit")
    @ResponseBody
    public JsonResult<String> update(@Validated(ValidateConfig.UPDATE.class)  CoreUser trip) {
        boolean success = tripService.update(trip);
        if (success) {
            return new JsonResult().success();
        } else {
            return JsonResult.failMessage("保存失败");
        }
    }


   
    @GetMapping(MODEL + "/view.json")
    @Function("cms.trip.query")
    @ResponseBody
    public JsonResult<CoreUser>queryInfo(Integer id) {
        CoreUser trip = tripService.queryById( id);
        return  JsonResult.success(trip);
    }

    @PostMapping(MODEL + "/delete.json")
    @Function("cms.trip.delete")
    @ResponseBody
    public JsonResult delete(String ids) {
        if (ids.endsWith(",")) {
            ids = StringUtils.substringBeforeLast(ids, ",");
        }
        List<Long> idList = ConvertUtil.str2longs(ids);
        tripService.batchDelCoreUser(idList);
        return new JsonResult().success();
    }
    

}
