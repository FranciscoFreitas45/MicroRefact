package org.jeecgframework.web.system.controller.core;
 import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.dao.jdbc.JdbcDao;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.common.model.json.ValidForm;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.enums.StoreUploadFilePathEnum;
import org.jeecgframework.core.extend.hqlsearch.parse.ObjectParseUtil;
import org.jeecgframework.core.extend.hqlsearch.parse.PageValueConvertRuleEnum;
import org.jeecgframework.core.extend.hqlsearch.parse.vo.HqlRuleEnum;
import org.jeecgframework.core.extend.swftools.SwfToolsUtil;
import org.jeecgframework.core.util.FileUtils;
import org.jeecgframework.core.util.JSONHelper;
import org.jeecgframework.core.util.ListUtils;
import org.jeecgframework.core.util.MutiLangSqlCriteriaUtil;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.PropertiesUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.SetListSort;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.YouBianCodeUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
import org.jeecgframework.web.cgform.exception.BusinessException;
import org.jeecgframework.web.system.manager.ClientManager;
import org.jeecgframework.web.system.manager.ClientSort;
import org.jeecgframework.web.system.pojo.base.Client;
import org.jeecgframework.web.system.pojo.base.DataLogDiff;
import org.jeecgframework.web.system.pojo.base.DictEntity;
import org.jeecgframework.web.system.pojo.base.TSDatalogEntity;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleFunction;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.service.MutiLangServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import Interface.UserService;
import Interface.SystemService;
import Interface.MutiLangServiceI;
import Interface.ClientManager;
import DTO.AjaxJson;
import DTO.TSDepart;
import DTO.DataGrid;
import DTO.TSRoleFunction;
import DTO.ComboTree;
import DTO.TSDatalogEntity;
import DTO.TreeGrid;
import DTO.TreeGridModel;
import DTO.TSRole;
@Controller
@RequestMapping("/systemController")
public class SystemController extends BaseController{

 private  Logger logger;

 private  UserService userService;

 private  SystemService systemService;

 private  MutiLangServiceI mutiLangService;

@Resource
 private  ClientManager clientManager;


@RequestMapping(params = "aouTypeGroup")
public ModelAndView aouTypeGroup(TSTypegroup typegroup,HttpServletRequest req){
    if (typegroup.getId() != null) {
        typegroup = systemService.getEntity(TSTypegroup.class, typegroup.getId());
        req.setAttribute("typegroup", typegroup);
    }
    return new ModelAndView("system/type/typegroup");
}


@RequestMapping(params = "delType")
@ResponseBody
public AjaxJson delType(TSType type,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    type = systemService.getEntity(TSType.class, type.getId());
    message = "类型: " + mutiLangService.getLang(type.getTypename()) + "被删除 成功";
    systemService.delete(type);
    // 刷新缓存
    systemService.refleshTypesCach(type);
    systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "commonWebUpload")
public ModelAndView commonWebUpload(HttpServletRequest req){
    return new ModelAndView("common/upload/uploadView2");
}


@RequestMapping(params = "datagridDepart")
public void datagridDepart(HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
    CriteriaQuery cq = new CriteriaQuery(TSDepart.class, dataGrid);
    this.systemService.getDataGridReturn(cq, true);
    TagUtil.datagrid(response, dataGrid);
    ;
}


@RequestMapping(params = "saveDepart")
@ResponseBody
public AjaxJson saveDepart(TSDepart depart,HttpServletRequest request){
    String message = null;
    // 设置上级部门
    String pid = request.getParameter("TSPDepart.id");
    if (pid.equals("")) {
        depart.setTSPDepart(null);
    }
    AjaxJson j = new AjaxJson();
    if (StringUtil.isNotEmpty(depart.getId())) {
        userService.saveOrUpdate(depart);
        message = MutiLangUtil.paramUpdSuccess("common.department");
        systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
    } else {
        // update-start--Author:zhoujf  Date:20150615 for：组织机构管理编码规则生成
        // String orgCode = systemService.generateOrgCode(depart.getId(), pid);
        // depart.setOrgCode(orgCode);
        if (oConvertUtils.isNotEmpty(pid)) {
            TSDepart paretDept = systemService.findUniqueByProperty(TSDepart.class, "id", pid);
            String localMaxCode = getMaxLocalCode(paretDept.getOrgCode());
            depart.setOrgCode(YouBianCodeUtil.getSubYouBianCode(paretDept.getOrgCode(), localMaxCode));
        } else {
            String localMaxCode = getMaxLocalCode(null);
            depart.setOrgCode(YouBianCodeUtil.getNextYouBianCode(localMaxCode));
        }
        // update-start--Author:zhoujf  Date:20150615 for：组织机构管理编码规则生成
        userService.save(depart);
        message = MutiLangUtil.paramAddSuccess("common.department");
        systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "typeGrid")
public void typeGrid(HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
    String typegroupid = request.getParameter("typegroupid");
    String typename = request.getParameter("typename");
    CriteriaQuery cq = new CriteriaQuery(TSType.class, dataGrid);
    cq.eq("TSTypegroup.id", typegroupid);
    cq.like("typename", typename);
    // update-begin--Author:zhangjiaqiang  Date:20160904 for：TASK #1338 【功能改造】字典表，没有创建时间，列表按照创建时间排序
    // update-begin-Author:taoyan date:20180620 for:TASK #2826 【新功能】字典值不支持排序，增加一个排序字段
    cq.addOrder("orderNum", SortDirection.asc);
    // update-end-Author:taoyan date:20180620 for:TASK #2826 【新功能】字典值不支持排序，增加一个排序字段
    // update-end--Author:zhangjiaqiang  Date:20160904 for：TASK #1338 【功能改造】字典表，没有创建时间，列表按照创建时间排序
    cq.add();
    this.systemService.getDataGridReturn(cq, true);
    // add-start--Author:zhangguoming  Date:20140928 for：处理多语言
    MutiLangUtil.setMutiLangValueForList(dataGrid.getResults(), "typename");
    // add-end--Author:zhangguoming  Date:20140928 for：处理多语言
    TagUtil.datagrid(response, dataGrid);
}


public boolean downFtpFile(String path,OutputStream out){
    // TODO 获取ftp连接 待封装
    PropertiesUtil ftpConfig = new PropertiesUtil("sysConfig.properties");
    Properties prop = ftpConfig.getProperties();
    String ftpUrl = prop.getProperty("ftp.url");
    String port = prop.getProperty("ftp.port");
    String userName = prop.getProperty("ftp.userName");
    String passWord = prop.getProperty("ftp.passWord");
    boolean success = false;
    FTPClient ftp = new FTPClient();
    try {
        ftp.setControlEncoding("UTF-8");
        // 连接
        ftp.connect(ftpUrl, Integer.valueOf(port));
        // 登录ftp
        ftp.login(userName, passWord);
        int replyCode = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(replyCode)) {
            ftp.disconnect();
            return success;
        }
        path = path.replace("\\", "/");
        String fileName = path.substring(path.lastIndexOf("/") + 1);
        ftp.changeWorkingDirectory(path.substring(0, path.lastIndexOf("/")));
        ftp.retrieveFile(new String(fileName.getBytes("UTF-8"), "iso-8859-1"), out);
        ftp.logout();
        success = true;
    } catch (SocketException e) {
        logger.info(e.getMessage());
    } catch (IOException e) {
        logger.info(e.getMessage());
    } finally {
        if (ftp.isConnected()) {
            try {
                ftp.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    return success;
}


@RequestMapping(params = "addorupdateRole")
public ModelAndView addorupdateRole(TSRole role,HttpServletRequest req){
    if (role.getId() != null) {
        role = systemService.getEntity(TSRole.class, role.getId());
        req.setAttribute("role", role);
    }
    return new ModelAndView("system/role/role");
}


public void savep(String roleid,String functionid,String ids){
    String hql = "from TRoleFunction t where" + " t.TSRole.id=" + roleid + " " + "and t.TFunction.functionid=" + functionid;
    TSRoleFunction rFunction = systemService.singleResult(hql);
    if (rFunction != null) {
        rFunction.setOperation(ids);
        systemService.saveOrUpdate(rFunction);
    }
}


public boolean delFtpFile(String url,int port,String userName,String passWord,String path){
    boolean success = false;
    FTPClient ftp = new FTPClient();
    try {
        ftp.setControlEncoding("UTF-8");
        // 连接
        ftp.connect(url, port);
        // 登录
        ftp.login(userName, passWord);
        int replyCode = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(replyCode)) {
            ftp.disconnect();
            return success;
        }
        String fileName = path.substring(path.lastIndexOf("/") + 1);
        ftp.changeWorkingDirectory(path.substring(0, path.lastIndexOf("/")));
        ftp.deleteFile(new String(fileName.getBytes("UTF-8"), "iso-8859-1"));
        ftp.logout();
        success = true;
    } catch (SocketException e) {
        logger.info(e.getMessage());
    } catch (IOException e) {
        logger.info(e.getMessage());
    } finally {
        if (ftp.isConnected()) {
            try {
                ftp.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    return success;
}


@RequestMapping(params = "goTypeGrid")
public ModelAndView goTypeGrid(HttpServletRequest request){
    String typegroupid = request.getParameter("typegroupid");
    request.setAttribute("typegroupid", typegroupid);
    return new ModelAndView("system/type/typeListForTypegroup");
}


@RequestMapping(params = "getDataVersion")
@ResponseBody
public AjaxJson getDataVersion(String tableName,String dataId){
    AjaxJson j = new AjaxJson();
    String hql = "from TSDatalogEntity where tableName = ? and dataId = ? order by versionNumber desc";
    List<TSDatalogEntity> datalogEntities = this.systemService.findHql(hql, new Object[] { tableName, dataId });
    if (datalogEntities.size() > 0) {
        j.setObj(datalogEntities);
    }
    return j;
}


@RequestMapping("/ftpUploader")
@ResponseBody
public AjaxJson ftpUploader(HttpServletRequest request,HttpServletResponse response){
    AjaxJson j = new AjaxJson();
    String msg = "啥都没干-没传参数吧！";
    String upFlag = request.getParameter("isup");
    String delFlag = request.getParameter("isdel");
    PropertiesUtil ftpConfig = new PropertiesUtil("sysConfig.properties");
    Properties prop = ftpConfig.getProperties();
    String ftpUrl = prop.getProperty("ftp.url");
    String port = prop.getProperty("ftp.port");
    String userName = prop.getProperty("ftp.userName");
    String passWord = prop.getProperty("ftp.passWord");
    try {
        // 如果是上传操作
        if ("1".equals(upFlag)) {
            String fileName = null;
            // 上传业务名称
            String bizType = request.getParameter("bizType");
            // 根据业务名称判断上传路径
            String bizPath = StoreUploadFilePathEnum.getPath(bizType);
            String nowday = new SimpleDateFormat("yyyyMMdd").format(new Date());
            // ftp存放地址
            String path = bizPath + File.separator + nowday;
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            // 获取上传文件对象
            MultipartFile mf = multipartRequest.getFile("file");
            // 获取文件名
            fileName = mf.getOriginalFilename();
            if (uploadFtpFile(ftpUrl, Integer.valueOf(port), userName, passWord, path, fileName, mf.getInputStream())) {
                msg = "上传成功";
                j.setObj(path + File.separator + fileName);
            // 1、将文件路径赋值给obj,前台可获取之,随表单提交,然后数据库中存储该路径
            // 2、demo这里用的是AjaxJson对象,开发者可自定义返回对象,但是用t标签的时候路径属性名需为  obj或 filePath 或自己在标签内指定若在标签内指定则action返回路径的名称应保持一致
            } else {
                msg = "ftp上传失败";
            }
            j.setMsg(msg);
        } else if ("1".equals(delFlag)) {
            // 如果是删除操作
            String path = request.getParameter("path");
            path = path.replace("\\", "/");
            if (delFtpFile(ftpUrl, Integer.valueOf(port), userName, passWord, path)) {
                msg = "--------成功删除文件---------" + path;
            } else {
                j.setSuccess(false);
                msg = "没删除成功--请重新试试";
            }
        } else {
            throw new BusinessException("没有传参指定上传还是删除操作！");
        }
    } catch (IOException e) {
        j.setSuccess(false);
        logger.info(e.getMessage());
    } catch (BusinessException b) {
        j.setSuccess(false);
        logger.info(b.getMessage());
    }
    logger.info("-----systemController/filedeal.do------------" + msg);
    j.setMsg(msg);
    return j;
}


@RequestMapping(params = "depart")
public ModelAndView depart(){
    return new ModelAndView("system/depart/departList");
}


public List<Client> getClinetList(List<Client> onlines,DataGrid dataGrid){
    Collections.sort(onlines, new ClientSort());
    List<Client> result = new ArrayList<Client>();
    for (int i = (dataGrid.getPage() - 1) * dataGrid.getRows(); i < onlines.size() && i < dataGrid.getPage() * dataGrid.getRows(); i++) {
        result.add(onlines.get(i));
    }
    return result;
}


@RequestMapping(params = "checkType")
@ResponseBody
public ValidForm checkType(HttpServletRequest request){
    ValidForm v = new ValidForm();
    String typecode = oConvertUtils.getString(request.getParameter("param"));
    String code = oConvertUtils.getString(request.getParameter("code"));
    String typeGroupCode = oConvertUtils.getString(request.getParameter("typeGroupCode"));
    StringBuilder hql = new StringBuilder("FROM ").append(TSType.class.getName()).append(" AS entity WHERE 1=1 ");
    // update--begin--author:zhoujf Date:20180605 for:TASK #2745 【Sql 注入问题修改】
    hql.append(" AND entity.TSTypegroup.typegroupcode =  ?");
    hql.append(" AND entity.typecode =  ?");
    // List<Object> types = this.systemService.findByQueryString(hql.toString());
    List<Object> types = this.systemService.findHql(hql.toString(), typeGroupCode, typecode);
    // update--end--author:zhoujf Date:20180605 for:TASK #2745 【Sql 注入问题修改】
    // update--begin--author:zhoujf Date:20180605 for:TASK #2772 【数据字典】数据字典项录入字典code重复校验问题
    if (types.size() > 0 && (code == null || !code.equals(typecode))) {
        v.setInfo("类型已存在");
        v.setStatus("n");
    }
    // update--end--author:zhoujf Date:20180605 for:TASK #2772 【数据字典】数据字典项录入字典code重复校验问题
    return v;
}


@RequestMapping(params = "saveType")
@ResponseBody
public AjaxJson saveType(TSType type,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    if (StringUtil.isNotEmpty(type.getId())) {
        message = "类型: " + mutiLangService.getLang(type.getTypename()) + "被更新成功";
        userService.saveOrUpdate(type);
        systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
    } else {
        message = "类型: " + mutiLangService.getLang(type.getTypename()) + "被添加成功";
        userService.save(type);
        systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
    }
    // 刷新缓存
    systemService.refleshTypesCach(type);
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "saveOperate")
@ResponseBody
public AjaxJson saveOperate(HttpServletRequest request){
    AjaxJson j = new AjaxJson();
    String fop = request.getParameter("fp");
    String roleid = request.getParameter("roleid");
    // 录入操作前清空上一次的操作数据
    clearp(roleid);
    String[] fun_op = fop.split(",");
    String aa = "";
    String bb = "";
    // 只有一个被选中
    if (fun_op.length == 1) {
        bb = fun_op[0].split("_")[1];
        aa = fun_op[0].split("_")[0];
        savep(roleid, bb, aa);
    } else {
        // 至少2个被选中
        for (int i = 0; i < fun_op.length; i++) {
            // 操作id
            String cc = fun_op[i].split("_")[0];
            if (i > 0 && bb.equals(fun_op[i].split("_")[1])) {
                aa += "," + cc;
                if (i == (fun_op.length - 1)) {
                    savep(roleid, bb, aa);
                }
            } else if (i > 0) {
                savep(roleid, bb, aa);
                // 操作ID
                aa = fun_op[i].split("_")[0];
                if (i == (fun_op.length - 1)) {
                    // 权限id
                    bb = fun_op[i].split("_")[1];
                    savep(roleid, bb, aa);
                }
            } else {
                // 操作ID
                aa = fun_op[i].split("_")[0];
            }
            // 权限id
            bb = fun_op[i].split("_")[1];
        }
    }
    return j;
}


@Autowired
public void setUserService(UserService userService){
    this.userService = userService;
}


@RequestMapping(params = "datagridOnline")
public void datagridOnline(Client tSOnline,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
    List<Client> onlines = new ArrayList<Client>();
    onlines.addAll(clientManager.getAllClient());
    dataGrid.setTotal(onlines.size());
    dataGrid.setResults(getClinetList(onlines, dataGrid));
    TagUtil.datagrid(response, dataGrid);
}


@Autowired
public void setSystemService(SystemService systemService){
    this.systemService = systemService;
}


@RequestMapping(params = "typeGroupGrid")
public void typeGroupGrid(HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid,TSTypegroup typegroup){
    CriteriaQuery cq = new CriteriaQuery(TSTypegroup.class, dataGrid);
    // add-start--Author:zhangguoming  Date:20140929 for：多语言条件添加
    String typegroupname = request.getParameter("typegroupname");
    if (oConvertUtils.isNotEmpty(typegroupname)) {
        typegroupname = typegroupname.trim();
        List<String> typegroupnameKeyList = systemService.findByQueryString("select typegroupname from TSTypegroup");
        if (typegroupname.lastIndexOf("*") == -1) {
            typegroupname = typegroupname + "*";
        }
        MutiLangSqlCriteriaUtil.assembleCondition(typegroupnameKeyList, cq, "typegroupname", typegroupname);
    }
    String typegroupcode = request.getParameter("typegroupcode");
    if (oConvertUtils.isNotEmpty(typegroupcode)) {
        cq.eq("typegroupcode", typegroupcode);
        cq.add();
    }
    this.systemService.getDataGridReturn(cq, true);
    MutiLangUtil.setMutiLangValueForList(dataGrid.getResults(), "typegroupname");
    // add-end--Author:zhangguoming  Date:20140929 for：多语言条件添加
    TagUtil.datagrid(response, dataGrid);
}


public UserService getUserService(){
    return userService;
}


@RequestMapping(params = "datagridRole")
public void datagridRole(HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
    CriteriaQuery cq = new CriteriaQuery(TSRole.class, dataGrid);
    this.systemService.getDataGridReturn(cq, true);
    TagUtil.datagrid(response, dataGrid);
}


@RequestMapping(value = "downloadFile", method = RequestMethod.GET)
public void downloadFile(HttpServletResponse response,HttpServletRequest request){
    String ctxPath = request.getSession().getServletContext().getRealPath("/");
    String dbpath = request.getParameter("filePath");
    String downLoadPath = ctxPath + dbpath;
    response.setContentType("application/x-msdownload;charset=utf-8");
    String fileName = dbpath.substring(dbpath.lastIndexOf("/") + 1);
    String userAgent = request.getHeader("user-agent").toLowerCase();
    if (userAgent.contains("msie") || userAgent.contains("like gecko")) {
        fileName = URLEncoder.encode(fileName, "UTF-8");
    } else {
        fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
    }
    response.setHeader("Content-disposition", "attachment; filename=" + fileName);
    InputStream inputStream = null;
    OutputStream outputStream = null;
    try {
        inputStream = new BufferedInputStream(new FileInputStream(downLoadPath));
        outputStream = response.getOutputStream();
        byte[] buf = new byte[1024];
        int len;
        while ((len = inputStream.read(buf)) > 0) {
            outputStream.write(buf, 0, len);
        }
        response.flushBuffer();
    } catch (Exception e) {
        logger.info("--通过流的方式获取文件异常--" + e.getMessage());
    } finally {
        if (inputStream != null) {
            inputStream.close();
        }
        if (outputStream != null) {
            outputStream.close();
        }
    }
}


@RequestMapping(params = "operate")
public ModelAndView operate(HttpServletRequest request){
    String roleid = request.getParameter("roleid");
    request.setAttribute("roleid", roleid);
    return new ModelAndView("system/role/functionList");
}


@RequestMapping(params = "druid")
public ModelAndView druid(){
    return new ModelAndView(new RedirectView("druid/index.html"));
}


@RequestMapping(params = "delTypeGroup")
@ResponseBody
public AjaxJson delTypeGroup(TSTypegroup typegroup,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    typegroup = systemService.getEntity(TSTypegroup.class, typegroup.getId());
    // add-begin--Author:zhangguoming  Date:20140929 for：数据字典修改
    message = "类型分组: " + mutiLangService.getLang(typegroup.getTypegroupname()) + " 被删除 成功";
    if (ListUtils.isNullOrEmpty(typegroup.getTSTypes())) {
        systemService.delete(typegroup);
        systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        // 刷新缓存
        systemService.refleshTypeGroupCach();
    } else {
        message = "类型分组: " + mutiLangService.getLang(typegroup.getTypegroupname()) + " 下有类型信息，不能删除！";
    }
    // add-end--Author:zhangguoming  Date:20140929 for：数据字典修改
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "setAuthority")
@ResponseBody
public List<ComboTree> setAuthority(TSRole role,HttpServletRequest request,ComboTree comboTree){
    CriteriaQuery cq = new CriteriaQuery(TSFunction.class);
    if (comboTree.getId() != null) {
        cq.eq("TFunction.functionid", oConvertUtils.getInt(comboTree.getId(), 0));
    }
    if (comboTree.getId() == null) {
        cq.isNull("TFunction");
    }
    cq.add();
    List<TSFunction> functionList = systemService.getListByCriteriaQuery(cq, false);
    List<ComboTree> comboTrees = new ArrayList<ComboTree>();
    Integer roleid = oConvertUtils.getInt(request.getParameter("roleid"), 0);
    // 已有权限菜单
    List<TSFunction> loginActionlist = new ArrayList<TSFunction>();
    role = this.systemService.get(TSRole.class, roleid);
    if (role != null) {
        List<TSRoleFunction> roleFunctionList = systemService.findByProperty(TSRoleFunction.class, "TSRole.id", role.getId());
        if (roleFunctionList.size() > 0) {
            for (TSRoleFunction roleFunction : roleFunctionList) {
                TSFunction function = (TSFunction) roleFunction.getTSFunction();
                loginActionlist.add(function);
            }
        }
    }
    ComboTreeModel comboTreeModel = new ComboTreeModel("id", "functionName", "TSFunctions");
    comboTrees = systemService.ComboTree(functionList, comboTreeModel, loginActionlist, false);
    return comboTrees;
}


@RequestMapping(params = "updateAuthority")
public String updateAuthority(HttpServletRequest request){
    Integer roleid = oConvertUtils.getInt(request.getParameter("roleid"), 0);
    String rolefunction = request.getParameter("rolefunctions");
    TSRole role = this.systemService.get(TSRole.class, roleid);
    List<TSRoleFunction> roleFunctionList = systemService.findByProperty(TSRoleFunction.class, "TSRole.id", role.getId());
    systemService.deleteAllEntitie(roleFunctionList);
    String[] roleFunctions = null;
    if (rolefunction != "") {
        roleFunctions = rolefunction.split(",");
        for (String s : roleFunctions) {
            TSRoleFunction rf = new TSRoleFunction();
            TSFunction f = this.systemService.get(TSFunction.class, Integer.valueOf(s));
            rf.setTSFunction(f);
            rf.setTSRole(role);
            this.systemService.save(rf);
        }
    }
    return "system/role/roleList";
}


@RequestMapping(params = "diffDataVersion")
public ModelAndView diffDataVersion(HttpServletRequest request,String id1,String id2){
    // update-begin-author:taoyan date:20180528 for:TASK #2745 【Sql 注入问题修改】全是搜索 "'+ c
    TSDatalogEntity datalogEntity1 = this.systemService.getEntity(TSDatalogEntity.class, id1);
    TSDatalogEntity datalogEntity2 = this.systemService.getEntity(TSDatalogEntity.class, id2);
    // update-end-author:taoyan date:20180528 for:TASK #2745 【Sql 注入问题修改】全是搜索 "'+ c
    if (datalogEntity1 != null && datalogEntity2 != null) {
        // 正则用于去掉头尾的[]字符(如存在)
        Integer version1 = datalogEntity1.getVersionNumber();
        Integer version2 = datalogEntity2.getVersionNumber();
        Map<String, Object> map1 = null;
        Map<String, Object> map2 = null;
        if (version1 < version2) {
            map1 = JSONHelper.toHashMap(datalogEntity1.getDataContent().replaceAll("^\\[|\\]$", ""));
            map2 = JSONHelper.toHashMap(datalogEntity2.getDataContent().replaceAll("^\\[|\\]$", ""));
        } else {
            map1 = JSONHelper.toHashMap(datalogEntity2.getDataContent().replaceAll("^\\[|\\]$", ""));
            map2 = JSONHelper.toHashMap(datalogEntity1.getDataContent().replaceAll("^\\[|\\]$", ""));
        }
        Map<String, Object> mapAll = new HashMap<String, Object>();
        mapAll.putAll(map1);
        mapAll.putAll(map2);
        Set<String> set = mapAll.keySet();
        List<DataLogDiff> dataLogDiffs = new LinkedList<DataLogDiff>();
        String value1 = null;
        String value2 = null;
        for (String string : set) {
            DataLogDiff dataLogDiff = new DataLogDiff();
            dataLogDiff.setName(string);
            // update-begin--Author:	jg_huangxg Date: 20150723 for：修复key找不到的bug
            // update-begin--Author:	dangzhenghui Date: 20170613 for：日期格式化
            if (map1.containsKey(string)) {
                if ("createDate".equals(string) && StringUtil.isNotEmpty(map1.get(string))) {
                    java.util.Date date = new Date((String) map1.get(string));
                    SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    value1 = simpledateformat.format(date);
                } else {
                    value1 = map1.get(string).toString();
                }
                // update-begin--Author:	dangzhenghui Date: 20170613 for：日期格式化
                if (value1 == null) {
                    dataLogDiff.setValue1("");
                } else {
                    dataLogDiff.setValue1(value1);
                }
            } else {
                dataLogDiff.setValue1("");
            }
            // update-begin--Author:	dangzhenghui Date: 20170613 for：日期格式化
            if (map2.containsKey(string)) {
                if ("createDate".equals(string) && StringUtil.isNotEmpty(map2.get(string))) {
                    java.util.Date date = new Date((String) map2.get(string));
                    SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    value2 = simpledateformat.format(date);
                } else {
                    value2 = map2.get(string).toString();
                }
                // update-begin--Author:	dangzhenghui Date: 20170613 for：日期格式化
                if (value2 == null) {
                    dataLogDiff.setValue2("");
                } else {
                    dataLogDiff.setValue2(value2);
                }
            } else {
                dataLogDiff.setValue2("");
            }
            // update-end--Author:	jg_huangxg Date: 20150723 for：修复key找不到的bug
            if (value1 == null && value2 == null) {
                dataLogDiff.setDiff("N");
            } else {
                if (value1 != null && value2 != null) {
                    if (value1.equals(value2)) {
                        // 相同
                        dataLogDiff.setDiff("N");
                    } else {
                        dataLogDiff.setDiff("Y");
                    }
                } else {
                    dataLogDiff.setDiff("Y");
                }
            }
            dataLogDiffs.add(dataLogDiff);
        }
        if (version1 < version2) {
            request.setAttribute("versionNumber1", datalogEntity1.getVersionNumber());
            request.setAttribute("versionNumber2", datalogEntity2.getVersionNumber());
        } else {
            request.setAttribute("versionNumber1", datalogEntity2.getVersionNumber());
            request.setAttribute("versionNumber2", datalogEntity1.getVersionNumber());
        }
        request.setAttribute("dataLogDiffs", dataLogDiffs);
    }
    return new ModelAndView("system/dataLog/diffDataVersion");
}


@RequestMapping(params = "setOperate")
@ResponseBody
public List<TreeGrid> setOperate(HttpServletRequest request,TreeGrid treegrid){
    String roleid = request.getParameter("roleid");
    CriteriaQuery cq = new CriteriaQuery(TSFunction.class);
    if (treegrid.getId() != null) {
        cq.eq("TFunction.functionid", oConvertUtils.getInt(treegrid.getId(), 0));
    }
    if (treegrid.getId() == null) {
        cq.isNull("TFunction");
    }
    cq.add();
    List<TSFunction> functionList = systemService.getListByCriteriaQuery(cq, false);
    List<TreeGrid> treeGrids = new ArrayList<TreeGrid>();
    Collections.sort(functionList, new SetListSort());
    TreeGridModel treeGridModel = new TreeGridModel();
    treeGridModel.setRoleid(roleid);
    treeGrids = systemService.treegrid(functionList, treeGridModel);
    return treeGrids;
}


@RequestMapping(params = "formTree")
@ResponseBody
public List<ComboTree> formTree(HttpServletRequest request,ComboTree rootCombotree){
    String typegroupCode = request.getParameter("typegroupCode");
    TSTypegroup group = ResourceUtil.getCacheTypeGroup(typegroupCode.toLowerCase());
    List<ComboTree> comboTrees = new ArrayList<ComboTree>();
    for (TSType tsType : ResourceUtil.getCacheTypes(typegroupCode.toLowerCase())) {
        ComboTree combotree = new ComboTree();
        combotree.setId(tsType.getTypecode());
        combotree.setText(tsType.getTypename());
        comboTrees.add(combotree);
    }
    rootCombotree.setId(group.getTypegroupcode());
    rootCombotree.setText(group.getTypegroupname());
    rootCombotree.setChecked(false);
    rootCombotree.setChildren(comboTrees);
    return new ArrayList<ComboTree>() {

        {
            add(rootCombotree);
        }
    };
}


@RequestMapping(params = "popDataContent")
public ModelAndView popDataContent(ModelMap modelMap,String id,HttpServletRequest request){
    TSDatalogEntity datalogEntity = this.systemService.get(TSDatalogEntity.class, id);
    modelMap.put("dataContent", datalogEntity.getDataContent());
    return new ModelAndView("system/dataLog/popDataContent");
}


@RequestMapping(params = "openViewFile")
public ModelAndView openViewFile(HttpServletRequest request){
    String inputFile = request.getParameter("path");
    String extend = FileUtils.getExtend(inputFile);
    if (FileUtils.isPicture(extend)) {
        request.setAttribute("realpath", "img/server/" + inputFile);
        return new ModelAndView("common/upload/imageView");
    } else {
        String swfPath = FileUtils.getSwfPath(inputFile);
        request.setAttribute("swfpath", "img/server/" + swfPath + "?down=true");
        return new ModelAndView("common/upload/swfView");
    }
}


@Autowired
public void setMutiLangService(MutiLangServiceI mutiLangService){
    this.mutiLangService = mutiLangService;
}


public boolean uploadFtpFile(String url,int port,String userName,String passWord,String path,String fileName,InputStream file){
    boolean success = false;
    FTPClient ftp = new FTPClient();
    try {
        ftp.setControlEncoding("UTF-8");
        // 连接
        ftp.connect(url, port);
        // 登录
        ftp.login(userName, passWord);
        ftp.setFileType(FTP.BINARY_FILE_TYPE);
        int replyCode = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(replyCode)) {
            ftp.disconnect();
            return success;
        }
        // 创建文件夹
        String[] dirs = path.replace(File.separator, "/").split("/");
        if (// 目录路径都为英文,故不需要转码
        dirs != null && dirs.length > 0)
            for (String dir : dirs) {
                ftp.makeDirectory(dir);
                ftp.changeWorkingDirectory(dir);
            }
        ftp.storeFile(new String(fileName.getBytes("UTF-8"), "iso-8859-1"), file);
        file.close();
        ftp.logout();
        success = true;
    } catch (SocketException e) {
        logger.info(e.getMessage());
    } catch (IOException e) {
        logger.info(e.getMessage());
    } finally {
        if (ftp.isConnected()) {
            try {
                ftp.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    return success;
}


@RequestMapping(params = "delRole")
@ResponseBody
public AjaxJson delRole(TSRole role,String ids,HttpServletRequest request){
    String message = null;
    message = "角色: " + role.getRoleName() + "被删除成功";
    AjaxJson j = new AjaxJson();
    role = systemService.getEntity(TSRole.class, role.getId());
    userService.delete(role);
    systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "typeListJson")
@ResponseBody
public AjaxJson typeListJson(String typeGroupName,HttpServletRequest request){
    AjaxJson ajaxJson = new AjaxJson();
    JSONArray typeArray = new JSONArray();
    try {
        String dicTable = request.getParameter("dicTable");
        if (oConvertUtils.isEmpty(dicTable)) {
            List<TSType> typeList = ResourceUtil.getCacheTypes(typeGroupName.toLowerCase());
            JSONObject headJson = new JSONObject();
            headJson.put("typecode", "");
            headJson.put("typename", "");
            typeArray.add(headJson);
            if (typeList != null && !typeList.isEmpty()) {
                for (TSType type : typeList) {
                    JSONObject typeJson = new JSONObject();
                    typeJson.put("typecode", type.getTypecode());
                    // update-begin-author:taoyan 数据字典加载国际化---
                    String typename = type.getTypename();
                    if (MutiLangUtil.existLangKey(typename)) {
                        typename = MutiLangUtil.doMutiLang(typename, "");
                    }
                    typeJson.put("typename", typename);
                    // update-end-author:taoyan 数据字典加载国际化---
                    typeArray.add(typeJson);
                }
            }
        } else {
            String dicText = request.getParameter("dicText");
            List<DictEntity> list = systemService.queryDict(dicTable, typeGroupName, dicText);
            if (list != null && list.size() > 0) {
                for (DictEntity type : list) {
                    JSONObject typeJson = new JSONObject();
                    typeJson.put("typecode", type.getTypecode());
                    String typename = type.getTypename();
                    if (MutiLangUtil.existLangKey(typename)) {
                        typename = MutiLangUtil.doMutiLang(typename, "");
                    }
                    typeJson.put("typename", typename);
                    typeArray.add(typeJson);
                }
            }
        }
        ajaxJson.setObj(typeArray);
    } catch (Exception e) {
        logger.debug(e.getMessage());
        ajaxJson.setSuccess(false);
        ajaxJson.setMsg(e.getMessage());
    }
    return ajaxJson;
}


@RequestMapping(params = "role")
public ModelAndView role(){
    return new ModelAndView("system/role/roleList");
}


@RequestMapping(params = "typeGroupList")
public ModelAndView typeGroupList(HttpServletRequest request){
    return new ModelAndView("system/type/typeGroupList");
}


@RequestMapping("/filedeal")
@ResponseBody
public AjaxJson filedeal(HttpServletRequest request,HttpServletResponse response){
    AjaxJson j = new AjaxJson();
    String msg = "啥都没干-没传参数吧！";
    String upFlag = request.getParameter("isup");
    String delFlag = request.getParameter("isdel");
    // 是否将文件转换成swf
    String swfTransform = request.getParameter("swfTransform");
    // String ctxPath = request.getSession().getServletContext().getRealPath("");
    // demo中设置为D://upFiles,实际项目应因事制宜
    String ctxPath = ResourceUtil.getConfigByName("webUploadpath");
    // update-begin--Author:scott  Date:20180726 for：增加全局开关，是否默认开启swf在线文档预览转换功能--------------
    // 默认上传文件是否转换为swf，实现在线预览功能开关
    String globalSwfTransformFlag = ResourceUtil.getConfigByName("swf.transform.flag");
    // update-end--Author:scott  Date:20180726 for：增加全局开关，是否默认开启swf在线文档预览转换功能--------------
    logger.debug("----ctxPath-----" + ctxPath);
    try {
        // 如果是上传操作
        if ("1".equals(upFlag)) {
            String fileName = null;
            // 上传业务名称
            String bizType = request.getParameter("bizType");
            logger.debug("---bizType----" + bizType);
            // 根据业务名称判断上传路径
            String bizPath = StoreUploadFilePathEnum.getPath(bizType);
            String nowday = new SimpleDateFormat("yyyyMMdd").format(new Date());
            logger.debug("---nowday----" + nowday);
            File file = new File(ctxPath + File.separator + bizPath + File.separator + nowday);
            if (!file.exists()) {
                // 创建文件根目录
                file.mkdirs();
            }
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            // 获取上传文件对象
            MultipartFile mf = multipartRequest.getFile("file");
            // update-begin-author:taoyan date:20180601 for:TASK #2739 【上传组件】webupload上传组件的问题
            // 获取文件名
            String orgName = mf.getOriginalFilename();
            fileName = orgName.substring(0, orgName.lastIndexOf(".")) + "_" + System.currentTimeMillis() + orgName.substring(orgName.indexOf("."));
            // update-end-author:taoyan date:20180601 for:TASK #2739 【上传组件】webupload上传组件的问题
            String savePath = file.getPath() + File.separator + fileName;
            String fileExt = FileUtils.getExtend(fileName);
            if ("txt".equals(fileExt)) {
                FileUtils.uploadTxtFile(mf, savePath);
            } else {
                File savefile = new File(savePath);
                FileCopyUtils.copy(mf.getBytes(), savefile);
            }
            msg = "上传成功";
            j.setMsg(msg);
            String dbpath = bizPath + File.separator + nowday + File.separator + fileName;
            logger.debug("---dbpath----" + dbpath);
            if (dbpath.contains("\\")) {
                dbpath = dbpath.replace("\\", "/");
            }
            j.setObj(dbpath);
            // 1、将文件路径赋值给obj,前台可获取之,随表单提交,然后数据库中存储该路径
            // 2、demo这里用的是AjaxJson对象,开发者可自定义返回对象,但是用t标签的时候路径属性名需为  obj或 filePath 或自己在标签内指定若在标签内指定则action返回路径的名称应保持一致
            if ("true".equals(globalSwfTransformFlag) && "true".equals(swfTransform)) {
                // 转换swf
                SwfToolsUtil.convert2SWF(savePath);
            }
        // 如果是删除操作
        } else if ("1".equals(delFlag)) {
            String path = request.getParameter("path");
            String delpath = ctxPath + File.separator + path;
            File fileDelete = new File(delpath);
            if (!fileDelete.exists() || !fileDelete.isFile()) {
                msg = "警告: " + delpath + "不存在!";
                logger.info(msg);
                // 不存在前台也给他删除
                j.setSuccess(true);
            } else {
                if (fileDelete.delete()) {
                    msg = "--------成功删除文件---------" + delpath;
                    logger.info(msg);
                    // 删除swf/pdf文件
                    if ("true".equals(globalSwfTransformFlag) && "true".equals(swfTransform)) {
                        try {
                            String swfPath = FileUtils.getSwfPath(delpath);
                            new File(swfPath).delete();
                            logger.info("--------成功删除swf文件---------" + swfPath);
                            if (!delpath.endsWith("pdf")) {
                                String pdfPath = delpath.substring(0, delpath.lastIndexOf(".") + 1) + "pdf";
                                new File(pdfPath).delete();
                                logger.info("--------成功删除pdf文件---------" + pdfPath);
                            }
                        } catch (Exception e) {
                            logger.info("swf文件ORpdf文件未删除成功");
                        }
                    }
                } else {
                    j.setSuccess(false);
                    msg = "没删除成功--jdk的问题还是你文件的问题请重新试试";
                    logger.info(msg);
                }
            }
        } else {
            throw new BusinessException("没有传参指定上传还是删除操作！");
        }
    } catch (IOException e) {
        j.setSuccess(false);
        logger.info(e.getMessage());
    } catch (BusinessException b) {
        j.setSuccess(false);
        logger.info(b.getMessage());
    }
    logger.debug("-----systemController/filedeal.do------------" + msg);
    j.setMsg(msg);
    return j;
}


@RequestMapping(params = "addorupdateType")
public ModelAndView addorupdateType(TSType type,HttpServletRequest req){
    String typegroupid = req.getParameter("typegroupid");
    req.setAttribute("typegroupid", typegroupid);
    TSTypegroup typegroup = systemService.findUniqueByProperty(TSTypegroup.class, "id", typegroupid);
    String typegroupname = typegroup.getTypegroupname();
    // update--begin--author:zhoujf Date:20180605 for:TASK #2772 【数据字典】数据字典项录入字典code重复校验问题
    req.setAttribute("typegroup", typegroup);
    // update--end--author:zhoujf Date:20180605 for:TASK #2772 【数据字典】数据字典项录入字典code重复校验问题
    req.setAttribute("typegroupname", mutiLangService.getLang(typegroupname));
    if (StringUtil.isNotEmpty(type.getId())) {
        type = systemService.getEntity(TSType.class, type.getId());
        req.setAttribute("type", type);
    }
    return new ModelAndView("system/type/type");
}


@RequestMapping(params = "saveTypeGroup")
@ResponseBody
public AjaxJson saveTypeGroup(TSTypegroup typegroup,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    if (StringUtil.isNotEmpty(typegroup.getId())) {
        message = "类型分组: " + mutiLangService.getLang(typegroup.getTypegroupname()) + "被更新成功";
        userService.saveOrUpdate(typegroup);
        systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
    } else {
        message = "类型分组: " + mutiLangService.getLang(typegroup.getTypegroupname()) + "被添加成功";
        userService.save(typegroup);
        systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
    }
    // 刷新缓存
    systemService.refleshTypeGroupCach();
    j.setMsg(message);
    return j;
}


public void clearp(String roleid){
    String hql = "from TRoleFunction t where" + " t.TSRole.id=" + roleid;
    List<TSRoleFunction> rFunctions = systemService.findByQueryString(hql);
    if (rFunctions.size() > 0) {
        for (TSRoleFunction tRoleFunction : rFunctions) {
            tRoleFunction.setOperation(null);
            systemService.saveOrUpdate(tRoleFunction);
        }
    }
}


@RequestMapping(params = "typeList")
public ModelAndView typeList(HttpServletRequest request){
    String typegroupid = request.getParameter("typegroupid");
    TSTypegroup typegroup = systemService.getEntity(TSTypegroup.class, typegroupid);
    request.setAttribute("typegroup", typegroup);
    return new ModelAndView("system/type/typeList");
}


@RequestMapping(params = "dataDiff")
public ModelAndView dataDiff(HttpServletRequest request){
    return new ModelAndView("system/dataLog/dataDiff");
}


public String getMaxLocalCode(String parentCode){
    if (oConvertUtils.isEmpty(parentCode)) {
        parentCode = "";
    }
    int localCodeLength = parentCode.length() + YouBianCodeUtil.zhanweiLength;
    StringBuilder sb = new StringBuilder();
    sb.append("SELECT org_code FROM t_s_depart");
    // -update-begin--author:scott--date:20160414--for:数据库兼容性修改---
    if (ResourceUtil.getJdbcUrl().indexOf(JdbcDao.DATABSE_TYPE_SQLSERVER) != -1) {
        sb.append(" where LEN(org_code) = ").append(localCodeLength);
    } else {
        sb.append(" where LENGTH(org_code) = ").append(localCodeLength);
    }
    // -update-end--author:scott--date:20160414--for:数据库兼容性修改---
    if (oConvertUtils.isNotEmpty(parentCode)) {
        sb.append(" and  org_code like '").append(parentCode).append("%'");
    } else {
        // -update-begin--author:LiShaoQing --date:20180104--for:只取非供应商节点---
        sb.append(" and LEFT(org_code,1)='A'");
    // -update-end--author:LiShaoQing --date:20180104--for:只取非供应商节点---
    }
    // update-begin-Alex 20160310 for:去除LIMIT,解决数据库兼容性问题
    sb.append(" ORDER BY org_code DESC");
    List<Map<String, Object>> objMapList = systemService.findForJdbc(sb.toString(), 1, 1);
    String returnCode = null;
    if (objMapList != null && objMapList.size() > 0) {
        returnCode = (String) objMapList.get(0).get("org_code");
    }
    // update-end-Alex 20160310 for:去除LIMIT,解决数据库兼容性问题
    return returnCode;
}


@RequestMapping(params = "typeGridTree")
@ResponseBody
// add-begin-end--Author:zhangguoming  Date:20140928 for：数据字典修改，该方法启用，数据字典不在已树结构展示了
@Deprecated
public List<TreeGrid> typeGridTree(HttpServletRequest request,TreeGrid treegrid){
    CriteriaQuery cq;
    List<TreeGrid> treeGrids = new ArrayList<TreeGrid>();
    if (treegrid.getId() != null) {
        cq = new CriteriaQuery(TSType.class);
        cq.eq("TSTypegroup.id", treegrid.getId().substring(1));
        cq.add();
        List<TSType> typeList = systemService.getListByCriteriaQuery(cq, false);
        for (TSType obj : typeList) {
            TreeGrid treeNode = new TreeGrid();
            treeNode.setId("T" + obj.getId());
            treeNode.setText(obj.getTypename());
            treeNode.setCode(obj.getTypecode());
            treeGrids.add(treeNode);
        }
    } else {
        cq = new CriteriaQuery(TSTypegroup.class);
        // add-begin--Author:zhangguoming  Date:20140807 for：添加字典查询条件
        String typegroupcode = request.getParameter("typegroupcode");
        if (typegroupcode != null) {
            // begin--Author:JueYue  Date:2014-8-23 for：修改查询拼装
            HqlRuleEnum rule = PageValueConvertRuleEnum.convert(typegroupcode);
            Object value = PageValueConvertRuleEnum.replaceValue(rule, typegroupcode);
            ObjectParseUtil.addCriteria(cq, "typegroupcode", rule, value);
            // end--Author:JueYue  Date:2014-8-23 for：修改查询拼装
            cq.add();
        }
        String typegroupname = request.getParameter("typegroupname");
        if (typegroupname != null && typegroupname.trim().length() > 0) {
            typegroupname = typegroupname.trim();
            List<String> typegroupnameKeyList = systemService.findByQueryString("select typegroupname from TSTypegroup");
            MutiLangSqlCriteriaUtil.assembleCondition(typegroupnameKeyList, cq, "typegroupname", typegroupname);
        }
        // add-end--Author:zhangguoming  Date:20140807 for：添加字典查询条件
        List<TSTypegroup> typeGroupList = systemService.getListByCriteriaQuery(cq, false);
        for (TSTypegroup obj : typeGroupList) {
            TreeGrid treeNode = new TreeGrid();
            treeNode.setId("G" + obj.getId());
            treeNode.setText(obj.getTypegroupname());
            treeNode.setCode(obj.getTypegroupcode());
            treeNode.setState("closed");
            treeGrids.add(treeNode);
        }
    }
    MutiLangUtil.setMutiTree(treeGrids);
    return treeGrids;
}


@RequestMapping(params = "delTypeGridTree")
@ResponseBody
public AjaxJson delTypeGridTree(String id,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    if (id.startsWith("G")) {
        // 分组
        TSTypegroup typegroup = systemService.getEntity(TSTypegroup.class, id.substring(1));
        message = "数据字典分组: " + mutiLangService.getLang(typegroup.getTypegroupname()) + "被删除 成功";
        systemService.delete(typegroup);
    } else {
        TSType type = systemService.getEntity(TSType.class, id.substring(1));
        message = "数据字典类型: " + mutiLangService.getLang(type.getTypename()) + "被删除 成功";
        systemService.delete(type);
    }
    systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
    // 刷新缓存
    systemService.refleshTypeGroupCach();
    j.setMsg(message);
    return j;
}


@RequestMapping(value = "showOrDownByurlFTP", method = RequestMethod.GET)
public void showOrDownByurlFTP(HttpServletResponse response,HttpServletRequest request){
    // 是否下载否则展示图片
    String flag = request.getParameter("down");
    String dbpath = request.getParameter("dbPath");
    if ("1".equals(flag)) {
        response.setContentType("application/x-msdownload;charset=utf-8");
        String fileName = dbpath.substring(dbpath.lastIndexOf(File.separator) + 1);
        String userAgent = request.getHeader("user-agent").toLowerCase();
        if (userAgent.contains("msie") || userAgent.contains("like gecko")) {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } else {
            fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
        }
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
    } else {
        response.setContentType("image/jpeg;charset=utf-8");
    }
    OutputStream outputStream = null;
    try {
        outputStream = response.getOutputStream();
        downFtpFile(dbpath, outputStream);
        response.flushBuffer();
    } catch (Exception e) {
        logger.info("--通过流的方式获取文件异常--" + e.getMessage());
    } finally {
        if (outputStream != null) {
            outputStream.close();
        }
    }
}


@RequestMapping(params = "delDepart")
@ResponseBody
public AjaxJson delDepart(TSDepart depart,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    depart = systemService.getEntity(TSDepart.class, depart.getId());
    message = "部门: " + depart.getDepartname() + "被删除 成功";
    systemService.delete(depart);
    systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
    return j;
}


@RequestMapping(params = "saveRole")
@ResponseBody
public AjaxJson saveRole(TSRole role,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    if (role.getId() != null) {
        message = "角色: " + role.getRoleName() + "被更新成功";
        userService.saveOrUpdate(role);
        systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
    } else {
        message = "角色: " + role.getRoleName() + "被添加成功";
        userService.saveOrUpdate(role);
        systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "typeGroupTabs")
public ModelAndView typeGroupTabs(HttpServletRequest request){
    List<TSTypegroup> typegroupList = systemService.loadAll(TSTypegroup.class);
    request.setAttribute("typegroupList", typegroupList);
    return new ModelAndView("system/type/typeGroupTabs");
}


@RequestMapping(params = "dataLogList")
public ModelAndView dataLogList(HttpServletRequest request){
    return new ModelAndView("system/dataLog/dataLogList");
}


@RequestMapping(params = "checkTypeGroup")
@ResponseBody
public ValidForm checkTypeGroup(HttpServletRequest request){
    ValidForm v = new ValidForm();
    String typegroupcode = oConvertUtils.getString(request.getParameter("param"));
    String code = oConvertUtils.getString(request.getParameter("code"));
    List<TSTypegroup> typegroups = systemService.findByProperty(TSTypegroup.class, "typegroupcode", typegroupcode);
    if (typegroups.size() > 0 && !code.equals(typegroupcode)) {
        v.setInfo("分组已存在");
        v.setStatus("n");
    }
    return v;
}


@RequestMapping(params = "commonUpload")
public ModelAndView commonUpload(HttpServletRequest req){
    return new ModelAndView("common/upload/uploadView");
}


@RequestMapping(params = "datagridDataLog")
public void dataLogDatagrid(TSDatalogEntity datalogEntity,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
    CriteriaQuery cq = new CriteriaQuery(TSDatalogEntity.class, dataGrid);
    // 查询条件组装器
    org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, datalogEntity, request.getParameterMap());
    cq.add();
    this.systemService.getDataGridReturn(cq, true);
    TagUtil.datagrid(response, dataGrid);
}


@RequestMapping(params = "addorupdateDepart")
public ModelAndView addorupdateDepart(TSDepart depart,HttpServletRequest req){
    List<TSDepart> departList = systemService.getList(TSDepart.class);
    req.setAttribute("departList", departList);
    if (depart.getId() != null) {
        depart = systemService.getEntity(TSDepart.class, depart.getId());
        req.setAttribute("depart", depart);
    }
    return new ModelAndView("system/depart/depart");
}


@RequestMapping(params = "refreshTypeGroupAndTypes")
@ResponseBody
public AjaxJson refreshTypeGroupAndTypes(HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    try {
        systemService.refreshTypeGroupAndTypes();
        message = mutiLangService.getLang("common.refresh.success");
    } catch (Exception e) {
        message = mutiLangService.getLang("common.refresh.fail");
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "setPFunction")
@ResponseBody
public List<ComboTree> setPFunction(HttpServletRequest request,ComboTree comboTree){
    CriteriaQuery cq = new CriteriaQuery(TSDepart.class);
    if (StringUtil.isNotEmpty(comboTree.getId())) {
        cq.eq("TSPDepart.id", comboTree.getId());
    }
    // ----------------------------------------------------------------
    // ----------------------------------------------------------------
    if (StringUtil.isEmpty(comboTree.getId())) {
        cq.isNull("TSPDepart.id");
    }
    // ----------------------------------------------------------------
    // ----------------------------------------------------------------
    cq.add();
    List<TSDepart> departsList = systemService.getListByCriteriaQuery(cq, false);
    List<ComboTree> comboTrees = new ArrayList<ComboTree>();
    comboTrees = systemService.comTree(departsList, comboTree);
    return comboTrees;
}


@RequestMapping(params = "fun")
public ModelAndView fun(HttpServletRequest request){
    Integer roleid = oConvertUtils.getInt(request.getParameter("roleid"), 0);
    request.setAttribute("roleid", roleid);
    return new ModelAndView("system/role/roleList");
}


}