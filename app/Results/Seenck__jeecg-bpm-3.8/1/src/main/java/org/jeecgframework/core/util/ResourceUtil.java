package org.jeecgframework.core.util;
 import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.annotation.Ehcache;
import org.jeecgframework.core.constant.DataBaseConstant;
import org.jeecgframework.web.cgform.common.CgAutoListConstant;
import org.jeecgframework.web.system.manager.ClientManager;
import org.jeecgframework.web.system.pojo.base.Client;
import org.jeecgframework.web.system.pojo.base.DynamicDataSourceEntity;
import org.jeecgframework.web.system.pojo.base.TSIcon;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.CacheServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Interface.CacheServiceI;
import DTO.ClientManager;
public class ResourceUtil {

 private  Logger log;

 private  CacheServiceI cacheService;

 public  String LOCAL_CLINET_USER;

 public  String DICT_TYPE_GROUPS_KEY;

 public  String DICT_TYPES_KEY;

 public  String MUTI_LANG_FOREVER_CACHE_KEY;

 public  String DYNAMIC_DB_CONFIGS_FOREVER_CACHE_KEY;

 private  Map<String,BasicDataSource> dbSources;

 public  Map<String,TSIcon> allTSIcons;

 private  ResourceBundle bundle;


public String getUserSystemData(String key){
    // #{sys_user_code}%
    String moshi = "";
    if (key.indexOf("}") != -1) {
        moshi = key.substring(key.indexOf("}") + 1);
    }
    String returnValue = null;
    // 针对特殊标示处理#{sysOrgCode}，判断替换
    if (key.contains("#{")) {
        key = key.substring(2, key.indexOf("}"));
    } else {
        key = key;
    }
    // ----------------------------------------------------------------
    // update-begin--Author:zhangdaihao  Date:20140913 for：获取系统上下文变量
    // 替换为系统的登录用户账号
    // if (key.equals(DataBaseConstant.CREATE_BY)
    // || key.equals(DataBaseConstant.CREATE_BY_TABLE)
    // || key.equals(DataBaseConstant.UPDATE_BY)
    // || key.equals(DataBaseConstant.UPDATE_BY_TABLE)
    // ||
    if (key.equals(DataBaseConstant.SYS_USER_CODE) || key.equals(DataBaseConstant.SYS_USER_CODE_TABLE)) {
        returnValue = getSessionUser().getUserName();
    }
    // 替换为系统登录用户真实名字
    // if (key.equals(DataBaseConstant.CREATE_NAME)
    // || key.equals(DataBaseConstant.CREATE_NAME_TABLE)
    // || key.equals(DataBaseConstant.UPDATE_NAME_TABLE)
    // || key.equals(DataBaseConstant.UPDATE_NAME)
    if (key.equals(DataBaseConstant.SYS_USER_NAME) || key.equals(DataBaseConstant.SYS_USER_NAME_TABLE)) {
        returnValue = getSessionUser().getRealName();
    }
    // update-end--Author:zhangdaihao  Date:20140913 for：获取系统上下文变量
    // ----------------------------------------------------------------
    // 替换为系统登录用户的公司编码
    if (key.equals(DataBaseConstant.SYS_COMPANY_CODE) || key.equals(DataBaseConstant.SYS_COMPANY_CODE_TABLE)) {
        // update-begin--author:zhangjiaqiang Date:20170112 for:业务数据当中的公司编码错误
        returnValue = getSessionUser().getCurrentDepart().getOrgCode().substring(0, Integer.valueOf(getOrgCodeLengthType()) + 1);
    // update-end--author:zhangjiaqiang Date:20170112 for:业务数据当中的公司编码错误
    }
    // 替换为系统用户登录所使用的机构编码
    if (key.equals(DataBaseConstant.SYS_ORG_CODE) || key.equals(DataBaseConstant.SYS_ORG_CODE_TABLE)) {
        returnValue = getSessionUser().getCurrentDepart().getOrgCode();
    }
    // 替换为当前系统时间(年月日)
    if (key.equals(DataBaseConstant.SYS_DATE) || key.equals(DataBaseConstant.SYS_DATE_TABLE)) {
        returnValue = DateUtils.formatDate();
    }
    // 替换为当前系统时间（年月日时分秒）
    if (key.equals(DataBaseConstant.SYS_TIME) || key.equals(DataBaseConstant.SYS_TIME_TABLE)) {
        returnValue = DateUtils.formatTime();
    }
    // 流程状态默认值（默认未发起）
    if (key.equals(DataBaseConstant.BPM_STATUS_TABLE) || key.equals(DataBaseConstant.BPM_STATUS_TABLE)) {
        returnValue = "1";
    }
    if (returnValue != null) {
        returnValue = returnValue + moshi;
    }
    return returnValue;
}


public String getSystempPath(){
    return System.getProperty("java.io.tmpdir");
}


public String getMutiLan(String key){
    String result = null;
    Object obj = cacheService.get(CacheServiceI.FOREVER_CACHE, MUTI_LANG_FOREVER_CACHE_KEY);
    if (obj != null) {
        Map<String, String> ls = (Map<String, String>) obj;
        result = ls.get(key);
        log.debug("-----------从缓存获取国际化-----key：[{}] , result[{}]", key, result);
        return result;
    }
    return null;
}


public String getJgAuthRequsetPath(HttpServletRequest request){
    // update-begin--Author:zhoujf  Date:20170331 for：BUG请求中没有参数时 会给加上?null
    // String requestPath = request.getRequestURI() + "?" + request.getQueryString();
    String queryString = request.getQueryString();
    String requestPath = request.getRequestURI();
    if (StringUtils.isNotEmpty(queryString)) {
        requestPath += "?" + queryString;
    }
    // update-begin--Author:zhoujf  Date:20170331 for：BUG请求中没有参数时 会给加上?null
    if (requestPath.indexOf("&") > -1) {
        // 去掉其他参数(保留一个参数) 例如：loginController.do?login
        requestPath = requestPath.substring(0, requestPath.indexOf("&"));
    }
    // update-begin--Author:scott  Date:20170906 for：TASK #2298 【权限请求带参问题】权限拦截，请求带参问题
    if (requestPath.indexOf("=") != -1) {
        // update-begin--Author:scott  Date:20171108 for：restful请求报错--------
        if (requestPath.indexOf(".do") != -1) {
            requestPath = requestPath.substring(0, requestPath.indexOf(".do") + 3);
        } else {
            requestPath = requestPath.substring(0, requestPath.indexOf("?"));
        }
    // update-end--Author:scott  Date:20171108 for：restful请求报错--------
    }
    // update-end--Author:scott  Date:20170906 for：TASK #2298 【权限请求带参问题】权限拦截，请求带参问题
    // 去掉项目路径
    requestPath = requestPath.substring(request.getContextPath().length() + 1);
    return requestPath;
}


public void main(String[] args){
    org.jeecgframework.core.util.LogUtil.info(getPorjectPath());
    org.jeecgframework.core.util.LogUtil.info(getSysPath());
}


public String getOrgCodeLengthType(){
    return bundle.getString("orgCodeLengthType");
}


public String getPorjectPath(){
    // 当前tomcat的bin目录的路径 如
    String nowpath;
    // D:\java\software\apache-tomcat-6.0.14\bin
    String tempdir;
    nowpath = System.getProperty("user.dir");
    // 把bin 文件夹变到 webapps文件里面
    tempdir = nowpath.replace("bin", "webapps");
    // 拼成D:\java\software\apache-tomcat-6.0.14\webapps\sz_pro
    tempdir += "\\";
    return tempdir;
}


public Map<String,DynamicDataSourceEntity> getCacheAllDynamicDataSourceEntity(){
    Object obj = cacheService.get(CacheServiceI.FOREVER_CACHE, DYNAMIC_DB_CONFIGS_FOREVER_CACHE_KEY);
    if (obj != null) {
        Map<String, DynamicDataSourceEntity> ls = (Map<String, DynamicDataSourceEntity>) obj;
        log.debug("-----------从缓存获取动态数据源配置-------getCacheAllDynamicDataSourceEntity--------size：[{}] ", ls.size());
        return ls;
    }
    return null;
}


public String getClassPath(){
    String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
    String temp = path.replaceFirst("file:/", "");
    String separator = System.getProperty("file.separator");
    String resultPath = temp.replaceAll("/", separator + separator);
    return resultPath;
}


public TSTypegroup getCacheTypeGroup(String typegroupcode){
    TSTypegroup result = null;
    Object obj = cacheService.get(CacheServiceI.FOREVER_CACHE, DICT_TYPE_GROUPS_KEY);
    if (obj != null) {
        Map<String, TSTypegroup> mp = (Map<String, TSTypegroup>) obj;
        result = mp.get(typegroupcode);
        log.debug("-----------从缓存获取字典组-----typegroupcode：[{}]", typegroupcode);
        return result;
    }
    return null;
}


public String getSessionattachmenttitle(String sessionName){
    return bundle.getString(sessionName);
}


public String formateSQl(String sql,Map<String,Object> params){
    sql = replaceExtendSqlSysVar(sql);
    if (params == null) {
        return sql;
    }
    if (sql.toLowerCase().indexOf(CgAutoListConstant.SQL_INSERT) != -1) {
        sql = sql.replace("#{UUID}", UUIDGenerator.generate());
    }
    for (String key : params.keySet()) {
        sql = sql.replace("#{" + key + "}", String.valueOf(params.get(key)));
    }
    return sql;
}


@Ehcache
public String getConfigByName(String name){
    return bundle.getString(name);
}


public String getSeparator(){
    return System.getProperty("file.separator");
}


public String getRandCodeType(){
    return bundle.getString("randCodeType");
}


public String getSessionData(String key){
    // ${myVar}%
    // 得到${} 后面的值
    String moshi = "";
    if (key.indexOf("}") != -1) {
        moshi = key.substring(key.indexOf("}") + 1);
    }
    String returnValue = null;
    // ---author:jg_xugj----start-----date:20151226--------for：修改bug 1、key.contains("${")  应改为 key.contains("#{") 2、StringUtil.isEmpty(key) 判断 不为空
    if (key.contains("#{")) {
        key = key.substring(2, key.indexOf("}"));
    }
    // 从session中取得值
    if (!StringUtil.isEmpty(key)) {
        HttpSession session = ContextHolderUtils.getSession();
        returnValue = (String) session.getAttribute(key);
    }
    // ---author:jg_xugj----end-----date:20151226--------for：修改bug 1、key.contains("${")  应改为 key.contains("#{") 2、StringUtil.isEmpty(key) 判断 不为空
    // 结果加上${} 后面的值
    if (returnValue != null) {
        returnValue = returnValue + moshi;
    }
    return returnValue;
}


public BasicDataSource getCacheBasicDataSource(String dbKey){
    return dbSources.get(dbKey);
}


public String getParameter(String field){
    HttpServletRequest request = ContextHolderUtils.getRequest();
    return request.getParameter(field);
}


public String getRandCodeLength(){
    return bundle.getString("randCodeLength");
}


public void cleanCacheBasicDataSource(){
    dbSources.clear();
}


public String getJdbcUrl(){
    return DBTypeUtil.getDBType().toLowerCase();
}


public List<TSType> getCacheTypes(String typegroupcode){
    List<TSType> result = null;
    Object obj = cacheService.get(CacheServiceI.FOREVER_CACHE, DICT_TYPES_KEY);
    if (obj != null) {
        Map<String, List<TSType>> mp = (Map<String, List<TSType>>) obj;
        result = mp.get(typegroupcode);
        log.debug("-----------从缓存获取字典-----typegroupcode：[{}]", typegroupcode);
        return result;
    }
    return null;
}


public String replaceExtendSqlSysVar(String sql){
    sql = sql.replace("#{sys." + DataBaseConstant.SYS_USER_CODE_TABLE + "}", ResourceUtil.getUserSystemData(DataBaseConstant.SYS_USER_CODE)).replace("#{sys." + DataBaseConstant.SYS_USER_NAME_TABLE + "}", ResourceUtil.getUserSystemData(DataBaseConstant.SYS_USER_NAME)).replace("#{sys." + DataBaseConstant.SYS_ORG_CODE_TABLE + "}", ResourceUtil.getUserSystemData(DataBaseConstant.SYS_ORG_CODE)).replace("#{sys." + DataBaseConstant.SYS_COMPANY_CODE_TABLE + "}", ResourceUtil.getUserSystemData(DataBaseConstant.SYS_COMPANY_CODE)).replace("#{sys." + DataBaseConstant.SYS_DATE_TABLE + "}", DateUtils.formatDate()).replace("#{sys." + DataBaseConstant.SYS_TIME_TABLE + "}", DateUtils.formatTime());
    return sql;
}


public String converRuleValue(String ruleValue){
    String value = ResourceUtil.getSessionData(ruleValue);
    if (StringUtil.isEmpty(value))
        value = ResourceUtil.getUserSystemData(ruleValue);
    return value != null ? value : ruleValue;
}


public String getBasePath(){
    HttpServletRequest request = ContextHolderUtils.getRequest();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    return basePath;
}


public void putCacheBasicDataSource(String dbKey,BasicDataSource db){
    dbSources.put(dbKey, db);
}


public String getSysPath(){
    String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
    String temp = path.replaceFirst("file:/", "").replaceFirst("WEB-INF/classes/", "");
    String separator = System.getProperty("file.separator");
    String resultPath = temp.replaceAll("/", separator + separator).replaceAll("%20", " ");
    return resultPath;
}


public DynamicDataSourceEntity getCacheDynamicDataSourceEntity(String dbKey){
    DynamicDataSourceEntity result = null;
    Object obj = cacheService.get(CacheServiceI.FOREVER_CACHE, DYNAMIC_DB_CONFIGS_FOREVER_CACHE_KEY);
    if (obj != null) {
        Map<String, DynamicDataSourceEntity> ls = (Map<String, DynamicDataSourceEntity>) obj;
        result = ls.get(dbKey);
        log.debug("-----------从缓存获取动态数据源配置----getCacheDynamicDataSourceEntity-----dbKey：[{}]", dbKey);
        return result;
    }
    return null;
}


public TSUser getSessionUser(){
    ClientManager clientManager = ClientManager.getInstance();
    HttpSession session = ContextHolderUtils.getSession();
    if (clientManager.getClient(session.getId()) != null) {
        return clientManager.getClient(session.getId()).getUser();
    // update-begin--update---author:scott-----------date:20151218-------for:解决分布式登录问题-------
    } else {
        TSUser u = (TSUser) session.getAttribute(ResourceUtil.LOCAL_CLINET_USER);
        Client client = new Client();
        client.setIp("");
        client.setLogindatetime(new Date());
        client.setUser(u);
        clientManager.addClinet(session.getId(), client);
    }
    // update-end--update---author:scott-----------date:20151218-------for:解决分布式登录问题-------
    return null;
}


public Map<String,Object> minidaoReplaceExtendSqlSysVar(Map<String,Object> data){
    data.put("sys." + DataBaseConstant.SYS_USER_CODE_TABLE, getUserSystemData(DataBaseConstant.SYS_USER_CODE));
    data.put("sys." + DataBaseConstant.SYS_USER_NAME_TABLE, getUserSystemData(DataBaseConstant.SYS_USER_NAME));
    data.put("sys." + DataBaseConstant.SYS_ORG_CODE_TABLE, getUserSystemData(DataBaseConstant.SYS_ORG_CODE));
    data.put("sys." + DataBaseConstant.SYS_COMPANY_CODE_TABLE, getUserSystemData(DataBaseConstant.SYS_COMPANY_CODE));
    data.put("sys." + DataBaseConstant.SYS_DATE_TABLE, DateUtils.formatDate());
    data.put("sys." + DataBaseConstant.SYS_TIME_TABLE, DateUtils.formatTime());
    return data;
}


public Map<Object,Object> getConfigMap(String path){
    ResourceBundle bundle = ResourceBundle.getBundle(path);
    Set set = bundle.keySet();
    return oConvertUtils.SetToMap(set);
}


}