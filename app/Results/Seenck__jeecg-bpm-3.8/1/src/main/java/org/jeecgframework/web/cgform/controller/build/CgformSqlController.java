package org.jeecgframework.web.cgform.controller.build;
 import java.beans.PropertyDescriptor;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecgframework.web.cgform.dao.config.CgFormVersionDao;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.pojo.config.CgFormHeadPojo;
import org.jeecgframework.web.cgform.service.migrate.MigrateForm;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.common.DBTable;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.IpUtil;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.ReflectHelper;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.SqlInjectionUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.thoughtworks.xstream.XStream;
import Interface.CgFormVersionDao;
import DTO.CgFormHeadEntity;
@Controller
@RequestMapping("/cgformSqlController")
public class CgformSqlController extends BaseController{

@SuppressWarnings("unused")
 private  Logger logger;

@Autowired
 private  CgFormVersionDao cgFormVersionDao;

@Autowired
@Qualifier("jdbcTemplate")
 private  JdbcTemplate jdbcTemplate;

@Autowired
@Qualifier("namedParameterJdbcTemplate")
 private  NamedParameterJdbcTemplate namedJdbcTemplate;


@RequestMapping(params = "toCgformMigrate")
public ModelAndView toCgformMigrate(HttpServletRequest req){
    return new ModelAndView("jeecg/cgform/config/cgformMigrateSqlInclude");
}


@RequestMapping(params = "doMigrateOut")
public void doMigrateOut(HttpServletRequest request,HttpServletResponse response){
    // 获得选择表单ID
    String ids = request.getParameter("ids");
    // List<String> listSQL = new ArrayList<String>();
    try {
        // listSQL = MigrateForm.createSQL(ids, jdbcTemplate);// 创建查询语句
        // MigrateForm.executeSQL(listSQL, jdbcTemplate);// 生成sql并拼装
        // 创建导出的数据对象
        List<DBTable> dbTables = MigrateForm.buildExportDbTableList(ids, jdbcTemplate);
        // MigrateForm.generateXmlDataOutFlieContent(ids, jdbcDao);
        // update by duanqilu 2013-12-05 增加多表单导出功能
        String ls_id = "";
        if (ids.indexOf(",") > 0) {
            ls_id = ids.substring(0, ids.indexOf(","));
        } else {
            ls_id = ids;
        }
        CgFormHeadEntity cgFormHeadEntity = cgFormVersionDao.getCgFormById(ls_id);
        // update by duanqilu 2013-12-05 增加多表单导出功能
        // MigrateForm.createFile(request,cgFormHeadEntity.getTableName())
        // 创建文件
        String ls_filename = cgFormHeadEntity.getTableName();
        // update-begin--Author:dangzhenghui  Date:20170522 for：TASK #1991 【bug】文件上传在linux下路径有问题--------------------
        String destFileDir = ResourceUtil.getSystempPath() + File.separator + ls_filename;
        // update-begin--Author:dangzhenghui  Date:20170522 for：TASK #1991 【bug】文件上传在linux下路径有问题--------------------
        MigrateForm.generateXmlDataOutFlieContent(dbTables, destFileDir);
        // 压缩文件
        ls_filename = MigrateForm.zip(null, "", destFileDir);
        // 文件下载
        File file = new File(ls_filename);
        String filename = file.getName();
        InputStream fis = new BufferedInputStream(new FileInputStream(ls_filename));
        // 输出生成的zip文件
        // 清空response
        response.reset();
        // 设置response的Header
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        response.addHeader("Content-Length", "" + file.length());
        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + new String(filename.getBytes("utf-8"), "ISO8859-1"));
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = fis.read(buffer, 0, 8192)) != -1) {
            toClient.write(buffer, 0, bytesRead);
        }
        toClient.write(buffer);
        toClient.flush();
        toClient.close();
        fis.close();
        logger.info("[" + IpUtil.getIpAddr(request) + "][online表单配置导出]");
    } catch (Exception e) {
        e.printStackTrace();
    }
}


@RequestMapping(params = "doMigrateIn", method = RequestMethod.POST)
@ResponseBody
public AjaxJson doMigrateIn(HttpServletRequest request,HttpServletResponse response){
    String message = null;
    AjaxJson j = new AjaxJson();
    String ls_file = "";
    UploadFile uploadFile = new UploadFile(request, ls_file);
    uploadFile.setCusPath("");
    uploadFile.setSwfpath("");
    // 文件上传根目录
    String uploadbasepath = uploadFile.getBasePath();
    if (uploadbasepath == null) {
        uploadbasepath = ResourceUtil.getConfigByName("uploadpath");
    }
    // update-begin--Author:dangzhenghui  Date:20170522 for：TASK #1991 【bug】文件上传在linux下路径有问题--------------------
    // 文件保存在硬盘的相对路径
    String path = uploadbasepath + File.separator;
    String realPath = uploadFile.getMultipartRequest().getSession().getServletContext().getRealPath(File.separator) + // update-begin--Author:dangzhenghui  Date:20170522 for：TASK #1991 【bug】文件上传在linux下路径有问题--------------------
    path;
    message = null;
    try {
        File file = new File(realPath);
        if (!file.exists()) {
            // 若目录不存在，创建根目录
            file.mkdir();
        }
        uploadFile.getMultipartRequest().setCharacterEncoding("UTF-8");
        MultipartHttpServletRequest multipartRequest = uploadFile.getMultipartRequest();
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        String fileName = "";
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            // 获取上传文件对象
            MultipartFile mf = entity.getValue();
            // 获取文件名
            fileName = mf.getOriginalFilename();
            String savePath = realPath + fileName;
            File savefile = new File(savePath);
            String ls_tmp = savefile.getName();
            /*String sqlfilename = realPath
						+ ls_tmp.substring(0, ls_tmp.lastIndexOf(".")) + "\\"
						+ ls_tmp.substring(0, ls_tmp.lastIndexOf(".")) + ".sql";*/
            FileCopyUtils.copy(mf.getBytes(), savefile);
            MigrateForm.unzip(savePath, "");
            String sqlFileDir = realPath + ls_tmp.substring(0, ls_tmp.lastIndexOf("."));
            File sqlDirFile = new File(sqlFileDir);
            // update-begin--Author:dangzhenghui  Date:20170522 for：TASK #1991 【bug】文件上传在linux下路径有问题--------------------
            String sqlfilename = sqlDirFile.getPath() + File.separator;
            // update-begin--Author:dangzhenghui  Date:20170522 for：TASK #1991 【bug】文件上传在linux下路径有问题--------------------
            if (sqlDirFile.isDirectory()) {
                sqlfilename += sqlDirFile.list()[0];
            }
            /*OfficeHtmlUtil officeHtml = new OfficeHtmlUtil();
				String sqlStr = officeHtml.getInfo(sqlfilename);
				String[] sqlStrs = sqlStr.split(";");
				for (String exesql : sqlStrs) {
					if (!StringUtil.isEmpty(exesql) && !"\n".equals(exesql)) {
						jdbcTemplate.execute(exesql);
					}
				}*/
            XStream xStream = new XStream();
            xStream.processAnnotations(DBTable.class);
            @SuppressWarnings("unchecked")
            List<DBTable> dbTables = (List<DBTable>) xStream.fromXML(new File(sqlfilename));
            if (!dbTables.isEmpty() && dbTables.size() > 0) {
                for (DBTable dbTable : dbTables) {
                    mergeMigrateInComponent(dbTable);
                }
            }
        }
    } catch (Exception e1) {
        e1.printStackTrace();
        LogUtil.error(e1.toString());
        message = e1.toString();
    }
    logger.info("[" + IpUtil.getIpAddr(request) + "][online表单配置导入]" + message);
    if (StringUtil.isNotEmpty(message))
        j.setMsg("SQL文件导入失败," + message);
    else
        j.setMsg("SQL文件导入成功");
    return j;
}


public void mergeMigrateInComponent(DBTable<T> dbTable){
    Class<T> clazz = dbTable.getClass1();
    if (null != clazz) {
        List<T> dataList = dbTable.getTableData();
        if (null == dataList || dataList.size() < 1)
            return;
        Map<String, String> idMap = new HashMap<String, String>();
        String id = "";
        String countSql = "";
        SqlParameterSource sqlParameterSource;
        List<Map<String, Object>> idList = new ArrayList<Map<String, Object>>();
        for (T t : dataList) {
            ReflectHelper reflectHelper = new ReflectHelper(t);
            List<String> ignores = new ArrayList<String>();
            ignores.add("class");
            PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(clazz);
            for (PropertyDescriptor pd : pds) {
                if (null == reflectHelper.getMethodValue(pd.getName())) {
                    ignores.add(pd.getName());
                // update-begin-author:taoyan date：20180726 for:TASK #3014 【bug】online 导入bug
                } else if (reflectHelper.isIgore(pd.getName())) {
                    ignores.add(pd.getName());
                }
            // update-end-author:taoyan date：20180726 for:TASK #3014 【bug】online 导入bug
            }
            if (t instanceof CgFormHeadPojo) {
                reflectHelper.setMethodValue("isDbsynch", "N");
            }
            // update--begin--author:zhoujf Date:20180606 for:TASK #2751 【sql注入】online开发问题
            SqlInjectionUtil.filterContent(dbTable.getTableName());
            // update--end--author:zhoujf Date:20180606 for:TASK #2751 【sql注入】online开发问题
            id = (String) reflectHelper.getMethodValue("id");
            countSql = "select id from " + dbTable.getTableName() + " where id=?";
            if (t instanceof CgFormHeadPojo) {
                countSql = "select id from " + dbTable.getTableName() + " where table_name=?";
                id = oConvertUtils.getString(reflectHelper.getMethodValue("tableName"));
            }
            idList = jdbcTemplate.queryForList(countSql, id);
            sqlParameterSource = MigrateForm.generateParameterMap(t, ignores);
            if (idList.size() > 0 && t instanceof CgFormHeadPojo) {
                idMap.put("id", oConvertUtils.getString(idList.get(0).get("id")));
                namedJdbcTemplate.update("delete from cgform_field where table_id=:id", idMap);
                namedJdbcTemplate.update("delete from cgform_head where id=:id", idMap);
                namedJdbcTemplate.update(MigrateForm.generateInsertSql(dbTable.getTableName(), clazz, ignores), sqlParameterSource);
            } else if (idList.size() > 0) {
                namedJdbcTemplate.update(MigrateForm.generateUpdateSql(dbTable.getTableName(), clazz, ignores), sqlParameterSource);
            } else {
                namedJdbcTemplate.update(MigrateForm.generateInsertSql(dbTable.getTableName(), clazz, ignores), sqlParameterSource);
            }
        }
    }
}


}