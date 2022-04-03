package org.jeecgframework.web.cgform.service.impl.build;
 import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.constant.DataBaseConstant;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.DBTypeUtil;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.SqlInjectionUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.UUIDGenerator;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.minidao.util.FreemarkerParseFactory;
import org.jeecgframework.web.cgform.common.CgAutoListConstant;
import org.jeecgframework.web.cgform.common.CommUtils;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecgframework.web.cgform.entity.button.CgformButtonSqlEntity;
import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
import org.jeecgframework.web.cgform.entity.config.CgFormHeadEntity;
import org.jeecgframework.web.cgform.entity.enhance.CgformEnhanceJavaEntity;
import org.jeecgframework.web.cgform.exception.BusinessException;
import org.jeecgframework.web.cgform.service.build.DataBaseService;
import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;
import org.springframework.jdbc.support.incrementer.PostgreSQLSequenceMaxValueIncrementer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import DTO.CgformEnhanceJavaEntity;
import DTO.CgformEnhanceJavaInter;
import DTO.CgformButtonSqlEntity;
@Service("dataBaseService")
// --author：luobaoli---------date:20150616--------for: 修改spring事务回滚异常设置，以便支持BusinessException
@Transactional(rollbackFor = Exception.class)
public class DataBaseServiceImpl extends CommonServiceImplimplements DataBaseService{

 private  Logger logger;

@Autowired
 private  CgFormFieldServiceI cgFormFieldService;

@Autowired
 private  AbstractRoutingDataSource dataSource;

@Autowired
 private  JdbcTemplate jdbcTemplate;

@Autowired
 private  NamedParameterJdbcTemplate namedParameterJdbcTemplate;


public Map<String,Object> findOneForJdbc(String tableName,String id){
    // update--begin--author:zhoujf Date:20180606 for:TASK #2751 【sql注入】online开发问题
    SqlInjectionUtil.filterContent(tableName);
    // update--end--author:zhoujf Date:20180606 for:TASK #2751 【sql注入】online开发问题
    StringBuffer sqlBuffer = new StringBuffer();
    sqlBuffer.append("select * from ").append(tableName);
    sqlBuffer.append(" where id= ? ");
    Map<String, Object> map = commonDao.findOneForJdbc(sqlBuffer.toString(), id);
    return map;
}


public CgformButtonSqlEntity getCgformButtonSqlByCodeFormId(String buttonCode,String formId){
    StringBuilder hql = new StringBuilder("");
    hql.append(" from CgformButtonSqlEntity t");
    hql.append(" where t.formId=?");
    hql.append(" and  t.buttonCode =?");
    List<CgformButtonSqlEntity> list = this.findHql(hql.toString(), formId, buttonCode);
    if (list != null && list.size() > 0) {
        return list.get(0);
    }
    return null;
}


public void insertTable(String tableName,Map<String,Object> data){
    CgFormHeadEntity cgFormHeadEntity = cgFormFieldService.getCgFormHeadByTableName(tableName);
    // 系统上下文变量赋值
    fillInsertSysVar(tableName, data);
    // 主键适配器（根据不同的数据库进行生成）
    keyAdapter(cgFormHeadEntity, data);
    // 数据类型转换（date，int，double等等）
    dataAdapter(tableName, data);
    String comma = "";
    StringBuffer insertKey = new StringBuffer();
    StringBuffer insertValue = new StringBuffer();
    for (Entry<String, Object> entry : data.entrySet()) {
        // 判断key是否为表配置的属性
        if (isContainsFieled(tableName, entry.getKey())) {
            // 插入SQL语法,兼容多数据库,去掉单引号
            insertKey.append(comma + entry.getKey());
            if (entry.getValue() != null && entry.getValue().toString().length() > 0) {
                insertValue.append(comma + ":" + entry.getKey());
            } else {
                insertValue.append(comma + "null");
            }
            comma = ", ";
        }
    }
    String sql = "INSERT INTO " + tableName + " (" + insertKey + ") VALUES (" + insertValue + ")";
    Object key = null;
    // update-begin--Author:	jg_huangxg Date: 20150626 for：[bugfree号]删除异常捕获
    key = this.executeSqlReturnKey(sql, data);
    // update-end--Author: jg_huangxg Date: 20150626 for：[bugfree号]删除异常捕获
    if (key != null && key instanceof Long) {
        data.put("id", key);
    }
    if (cgFormHeadEntity != null) {
        executeSqlExtend(cgFormHeadEntity.getId(), "add", data);
        // update-start--Author:luobaoli  Date:20150630 for：    增加add按纽java增强逻辑处理
        executeJavaExtend(cgFormHeadEntity.getId(), "add", data);
    // update-end--Author:luobaoli  Date:20150630 for：    增加add按纽java增强逻辑处理
    }
}


@Override
public void executeJavaExtend(String formId,String buttonCode,Map<String,Object> data){
    CgformEnhanceJavaEntity cgformEnhanceJavaEntity = getCgformEnhanceJavaEntityByCodeFormId(buttonCode, formId);
    if (cgformEnhanceJavaEntity != null) {
        String cgJavaType = cgformEnhanceJavaEntity.getCgJavaType();
        String cgJavaValue = cgformEnhanceJavaEntity.getCgJavaValue();
        // update-start--Author:luobaoli  Date:20150701 for： 允许springKey或者javaClass为空
        if (StringUtil.isNotEmpty(cgJavaValue)) {
            Object obj = null;
            try {
                if ("class".equals(cgJavaType)) {
                    // 因新增时已经校验了实例化是否可以成功，所以这块就不需要再做一次判断
                    obj = MyClassLoader.getClassByScn(cgJavaValue).newInstance();
                } else if ("spring".equals(cgJavaType)) {
                    obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
                }
                if (obj instanceof CgformEnhanceJavaInter) {
                    // update-begin--author:zhoujf  date:20170307 for：java 增强扩展tableName字段
                    CgFormHeadEntity head = this.get(CgFormHeadEntity.class, formId);
                    CgformEnhanceJavaInter javaInter = (CgformEnhanceJavaInter) obj;
                    javaInter.execute(head.getTableName(), data);
                // update-end--author:zhoujf  date:20170307 for：java 增强扩展tableName字段
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
                e.printStackTrace();
                throw new BusinessException("执行JAVA增强出现异常！");
            }
        }
    // update-end--Author:luobaoli  Date:20150701 for： 允许springKey或者javaClass为空
    }
}


public Map<String,CgFormFieldEntity> getAllFieldByTableName(String tableName){
    // 获取版本号
    String version = cgFormFieldService.getCgFormVersionByTableName(tableName);
    Map<String, CgFormFieldEntity> map = cgFormFieldService.getAllCgFormFieldByTableName(tableName, version);
    return map;
}


public Map<String,Object> dataAdapter(String tableName,Map<String,Object> data){
    // update--begin--author:zhoujf Date:20180606 for:TASK #2751 【sql注入】online开发问题
    SqlInjectionUtil.filterContent(tableName);
    // update--end--author:zhoujf Date:20180606 for:TASK #2751 【sql注入】online开发问题
    // step.1 获取表单的字段配置
    Map<String, CgFormFieldEntity> fieldConfigs = cgFormFieldService.getAllCgFormFieldByTableName(tableName);
    // step.2 迭代将要持久化的数据
    Iterator it = fieldConfigs.keySet().iterator();
    for (; it.hasNext(); ) {
        Object key = it.next();
        // 根据表单配置的字段名 获取 前台数据
        Object beforeV = data.get(key.toString().toLowerCase());
        // 如果值不为空
        if (oConvertUtils.isNotEmpty(beforeV)) {
            // 获取字段配置-字段类型
            CgFormFieldEntity fieldConfig = fieldConfigs.get(key);
            String type = fieldConfig.getType();
            // 根据类型进行值的适配
            if ("date".equalsIgnoreCase(type)) {
                // 日期->java.util.Date
                Object newV = String.valueOf(beforeV);
                try {
                    // --author：zhoujf---start------date:20170207--------for:日期格式化问题
                    String dateStr = String.valueOf(beforeV);
                    if (dateStr.indexOf(":") == -1 && dateStr.length() == 10) {
                        newV = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
                    } else if (dateStr.indexOf(":") > 0 && dateStr.length() == 19) {
                        newV = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
                    // author:scott----start-----date:20170725--------for：SqlServer、oracle时间格式兼容问题----
                    } else if (dateStr.indexOf(":") > 0 && dateStr.indexOf(".0") > 0 && dateStr.length() == 21) {
                        newV = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr.substring(0, dateStr.indexOf(".0")));
                    // update-begin--Author:Yandong Date: 20180419 for：TASK #2632 【online表单】oracle数据库环境下，online表单，编辑时 创建时间，更新时间格式问题
                    } else if (dateStr.indexOf(":") != -1) {
                        newV = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
                    } else {
                        newV = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
                    }
                    // update-end--Author:Yandong Date: 20180419 for：TASK #2632 【online表单】oracle数据库环境下，online表单，编辑时 创建时间，更新时间格式问题
                    // author:scott----end-----date:20170725--------for：SqlServer、oracle时间格式兼容问题----
                    /*String dateType = fieldConfig.getShowType();
						if("datetime".equalsIgnoreCase(dateType)){
							newV =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(String.valueOf(beforeV));
						}else if("date".equalsIgnoreCase(dateType)){
							newV = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(beforeV));
						}*/
                    // --author：zhoujf---end------date:20170207--------for:日期格式化问题
                    if (data.containsKey(key)) {
                        data.put(String.valueOf(key), newV);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else if ("int".equalsIgnoreCase(type)) {
                // int->java.lang.Integer
                Object newV = null;
                try {
                    newV = Integer.parseInt(String.valueOf(beforeV));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (data.containsKey(key)) {
                    data.put(String.valueOf(key), newV);
                }
            } else if ("double".equalsIgnoreCase(type)) {
                // double->java.lang.Double
                Object newV = new Double(0);
                try {
                    newV = Double.parseDouble(String.valueOf(beforeV));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (data.containsKey(key)) {
                    data.put(String.valueOf(key), newV);
                }
            }
        // update---start--author：JueYue---------date：20140824---------for：默认值无效
        } else if (oConvertUtils.isNotEmpty(fieldConfigs.get(key).getFieldDefault())) {
            data.remove(key.toString().toLowerCase());
        }
    // update---end--author：JueYue---------date：20140824---------for：默认值无效
    }
    return data;
}


public boolean isContainsFieled(String tableName,String fieledName){
    boolean flag = false;
    if (getAllFieldByTableName(tableName).containsKey(fieledName)) {
        flag = true;
    }
    return flag;
}


public Object getPkValue(String tableName){
    Object pkValue = null;
    CgFormHeadEntity cghead = cgFormFieldService.getCgFormHeadByTableName(tableName);
    String dbType = DBTypeUtil.getDBType();
    String pkType = cghead.getJformPkType();
    String pkSequence = cghead.getJformPkSequence();
    if (StringUtil.isNotEmpty(pkType) && "UUID".equalsIgnoreCase(pkType)) {
        pkValue = UUIDGenerator.generate();
    } else if (StringUtil.isNotEmpty(pkType) && "NATIVE".equalsIgnoreCase(pkType)) {
        if (StringUtil.isNotEmpty(dbType) && "oracle".equalsIgnoreCase(dbType)) {
            OracleSequenceMaxValueIncrementer incr = new OracleSequenceMaxValueIncrementer(dataSource, "HIBERNATE_SEQUENCE");
            try {
                pkValue = incr.nextLongValue();
            } catch (Exception e) {
                logger.error(e, e);
            }
        } else if (StringUtil.isNotEmpty(dbType) && "postgres".equalsIgnoreCase(dbType)) {
            PostgreSQLSequenceMaxValueIncrementer incr = new PostgreSQLSequenceMaxValueIncrementer(dataSource, "HIBERNATE_SEQUENCE");
            try {
                pkValue = incr.nextLongValue();
            } catch (Exception e) {
                logger.error(e, e);
            }
        } else {
            pkValue = null;
        }
    } else if (StringUtil.isNotEmpty(pkType) && "SEQUENCE".equalsIgnoreCase(pkType)) {
        if (StringUtil.isNotEmpty(dbType) && "oracle".equalsIgnoreCase(dbType)) {
            OracleSequenceMaxValueIncrementer incr = new OracleSequenceMaxValueIncrementer(dataSource, pkSequence);
            try {
                pkValue = incr.nextLongValue();
            } catch (Exception e) {
                logger.error(e, e);
            }
        } else if (StringUtil.isNotEmpty(dbType) && "postgres".equalsIgnoreCase(dbType)) {
            PostgreSQLSequenceMaxValueIncrementer incr = new PostgreSQLSequenceMaxValueIncrementer(dataSource, pkSequence);
            try {
                pkValue = incr.nextLongValue();
            } catch (Exception e) {
                logger.error(e, e);
            }
        } else {
            pkValue = null;
        }
    } else {
        pkValue = UUIDGenerator.generate();
    }
    return pkValue;
}


public Map<Object,Map<String,Object>> getSubTableData(List<Map<String,Object>> fkFieldList,String mainTableName,String subTableName,Object mainTableId){
    StringBuilder sql2 = new StringBuilder("");
    sql2.append("select sub.* from ").append(subTableName).append(" sub ");
    sql2.append(", ").append(mainTableName).append(" main ");
    sql2.append("where 1=1 ");
    if (fkFieldList != null && fkFieldList.size() > 0) {
        for (Map<String, Object> map : fkFieldList) {
            if (map.get("main_field") != null) {
                sql2.append(" and sub.").append((String) map.get("field_name")).append("=").append("main.").append((String) map.get("main_field"));
            }
        }
    }
    sql2.append(" and main.id= ? ");
    List<Map<String, Object>> subTableDataList = this.findForJdbc(sql2.toString(), mainTableId);
    Map<Object, Map<String, Object>> dataMap = new HashMap<Object, Map<String, Object>>();
    if (subTableDataList != null) {
        for (Map<String, Object> map : subTableDataList) {
            // update-begin--Author:zhoujf  Date:20180329 for：TASK #2584 【问题确认】online 论坛问题确认主键策略为NATIVE 再次编辑附表数据会被删除
            dataMap.put(map.get("id").toString(), map);
        // update-end--Author:zhoujf  Date:20180329 for：TASK #2584 【问题确认】online 论坛问题确认主键策略为NATIVE 再次编辑附表数据会被删除
        }
    }
    return dataMap;
}


public int updateTable(String tableName,Object id,Map<String,Object> data){
    fillUpdateSysVar(tableName, data);
    dataAdapter(tableName, data);
    String comma = "";
    StringBuffer sqlBuffer = new StringBuffer();
    sqlBuffer.append("update ").append(tableName).append(" set ");
    for (Entry<String, Object> entry : data.entrySet()) {
        // 判断key是否为表配置的属性
        if (isContainsFieled(tableName, entry.getKey())) {
            if (entry.getValue() != null && entry.getValue().toString().length() > 0) {
                sqlBuffer.append(comma).append(entry.getKey()).append("=:" + entry.getKey() + " ");
            } else {
                sqlBuffer.append(comma).append(entry.getKey()).append("=null");
            }
            comma = ", ";
        }
    }
    // update--begin--author:zhoujf Date:20180605 for:TASK #2745 【Sql 注入问题修改】
    // if(id instanceof java.lang.String){
    // sqlBuffer.append(" where id='").append(id).append("'");
    // }else{
    // sqlBuffer.append(" where id=").append(id);
    // }
    sqlBuffer.append(" where id=:id");
    data.put("id", id);
    // update--end--author:zhoujf Date:20180605 for:TASK #2745 【Sql 注入问题修改】
    CgFormHeadEntity cgFormHeadEntity = cgFormFieldService.getCgFormHeadByTableName(tableName);
    int num = this.executeSql(sqlBuffer.toString(), data);
    if (cgFormHeadEntity != null) {
        // update-begin--Author:likai  Date:20160912 for：[1354 ]测试onlinecoding的sql增强是否存在问题
        data.put("id", id);
        // update-begin--Author:likai  Date:20160912 for：[1354 ]测试onlinecoding的sql增强是否存在问题
        executeSqlExtend(cgFormHeadEntity.getId(), "update", data);
        // update-start--Author:luobaoli  Date:20150630 for：   增加update按纽java增强逻辑处理
        executeJavaExtend(cgFormHeadEntity.getId(), "update", data);
    // update-end--Author:luobaoli  Date:20150630 for：   增加update按纽java增强逻辑处理
    }
    return num;
}


@SuppressWarnings("unchecked")
public boolean updateTableMore(Map<String,List<Map<String,Object>>> mapMore,String mainTableName){
    // 更新主表信息
    Map<String, Object> mainMap = mapMore.get(mainTableName).get(0);
    Object mainTableId = mainMap.get("id");
    // 消除字段
    String[] filterName = { "tableName", "saveOrUpdateMore", "id" };
    mainMap = CommUtils.attributeMapFilter(mainMap, filterName);
    updateTable(mainTableName, mainTableId, mainMap);
    mainMap.put("id", mainTableId);
    // 更新附表信息
    String[] filterMainTable = { mainTableName };
    mapMore = CommUtils.attributeMapFilter(mapMore, filterMainTable);
    Iterator it = mapMore.entrySet().iterator();
    while (it.hasNext()) {
        Map.Entry entry = (Map.Entry) it.next();
        String ok = (String) entry.getKey();
        List<Map<String, Object>> ov = (List<Map<String, Object>>) entry.getValue();
        // 处理关联键
        List<Map<String, Object>> fkFieldList = getFKField(mainTableName, ok);
        // 根据主表id获取附表信息
        Map<Object, Map<String, Object>> subTableDateMap = getSubTableData(fkFieldList, mainTableName, ok, mainTableId);
        for (Map<String, Object> fieldMap : ov) {
            Object subId = fieldMap.get("id") == null ? "" : fieldMap.get("id");
            if (subId == null || "".equals(String.valueOf(subId))) {
                fieldMap = CommUtils.convertFKMap(fieldMap, mainMap, fkFieldList);
                fieldMap.put("id", getPkValue(ok));
                insertTable(ok, fieldMap);
            } else {
                fieldMap = CommUtils.convertFKMap(fieldMap, mainMap, fkFieldList);
                // 消除字段
                String[] subFilterName = { "id" };
                fieldMap = CommUtils.attributeMapFilter(fieldMap, subFilterName);
                updateTable(ok, subId, fieldMap);
                // 剔除已经更新了的数据
                if (subTableDateMap.containsKey(subId)) {
                    subTableDateMap.remove(subId);
                }
            }
        }
        // subTableDateMap中剩余的数据就是删除的数据
        if (subTableDateMap.size() > 0) {
            Iterator itSub = subTableDateMap.entrySet().iterator();
            while (itSub.hasNext()) {
                Map.Entry entrySub = (Map.Entry) itSub.next();
                Object subId = entrySub.getKey();
                deleteSubTableDataById(subId, ok);
            }
        }
    }
    return true;
}


public void deleteSubTableDataById(Object subId,String subTableName){
    StringBuilder sql = new StringBuilder("");
    // update--begin--author:zhoujf Date:20180606 for:TASK #2751 【sql注入】online开发问题
    SqlInjectionUtil.filterContent(subTableName);
    // update--end--author:zhoujf Date:20180606 for:TASK #2751 【sql注入】online开发问题
    sql.append(" delete from ").append(subTableName).append(" where id = ? ");
    this.executeSql(sql.toString(), subId);
}


@SuppressWarnings("unchecked")
public Map<String,Object> insertTableMore(Map<String,List<Map<String,Object>>> mapMore,String mainTableName){
    // 插入主表信息
    Map<String, Object> mainMap = mapMore.get(mainTableName).get(0);
    // 消除字段
    String[] filterName = { "tableName", "saveOrUpdateMore" };
    mainMap = CommUtils.attributeMapFilter(mainMap, filterName);
    if (mainMap.get("id") == null || "".equals((String) mainMap.get("id"))) {
        Object pkValue = getPkValue(mainTableName);
        mainMap.put("id", pkValue);
    }
    insertTable(mainTableName, mainMap);
    // 插入附表信息
    // 去除主表信息
    String[] filterMainTable = { mainTableName };
    mapMore = CommUtils.attributeMapFilter(mapMore, filterMainTable);
    Iterator it = mapMore.entrySet().iterator();
    while (it.hasNext()) {
        Map.Entry entry = (Map.Entry) it.next();
        String ok = (String) entry.getKey();
        List<Map<String, Object>> ov = (List<Map<String, Object>>) entry.getValue();
        for (Map<String, Object> fieldMap : ov) {
            // 处理关联键
            List<Map<String, Object>> fkFieldList = getFKField(mainTableName, ok);
            Object subPkValue = getPkValue(ok);
            fieldMap.put("id", subPkValue);
            fieldMap = CommUtils.convertFKMap(fieldMap, mainMap, fkFieldList);
            insertTable(ok, fieldMap);
        }
    }
    return mainMap;
}


public String replaceExtendSqlSysVar(String sql){
    sql = sql.replace("#{sys." + DataBaseConstant.SYS_USER_CODE_TABLE + "}", ResourceUtil.getUserSystemData(DataBaseConstant.SYS_USER_CODE)).replace("#{sys." + DataBaseConstant.SYS_USER_NAME_TABLE + "}", ResourceUtil.getUserSystemData(DataBaseConstant.SYS_USER_NAME)).replace("#{sys." + DataBaseConstant.SYS_ORG_CODE_TABLE + "}", ResourceUtil.getUserSystemData(DataBaseConstant.SYS_ORG_CODE)).replace("#{sys." + DataBaseConstant.SYS_COMPANY_CODE_TABLE + "}", ResourceUtil.getUserSystemData(DataBaseConstant.SYS_COMPANY_CODE)).replace("#{sys." + DataBaseConstant.SYS_DATE_TABLE + "}", DateUtils.formatDate()).replace("#{sys." + DataBaseConstant.SYS_TIME_TABLE + "}", DateUtils.formatTime());
    return sql;
}


public void fillInsertSysVar(String tableName,Map<String,Object> data){
    // update-begin--Author:dangzhenghui  Date:20170402 for：对外接口改造 对外api没有session 判断session是否存在
    if (ResourceUtil.getSessionUser() == null) {
        return;
    }
    // update-end--Author:dangzhenghui  Date:20170402 for：对外接口改造 对外api没有session 判断session是否存在
    // --author：zhoujf---start------date:20170207--------for:online表单 Excel数据导入时基本字段没有初始化数据
    // --author：zhoujf---start------date:20170207--------for:创建时间格式统一为yyyy-MM-dd HH:mm:ss
    if (data.containsKey(DataBaseConstant.CREATE_DATE_TABLE) || getAllFieldByTableName(tableName).containsKey(DataBaseConstant.CREATE_DATE_TABLE)) {
        data.put(DataBaseConstant.CREATE_DATE_TABLE, DateUtils.formatDateTime());
    }
    if (data.containsKey(DataBaseConstant.CREATE_TIME_TABLE) || getAllFieldByTableName(tableName).containsKey(DataBaseConstant.CREATE_TIME_TABLE)) {
        data.put(DataBaseConstant.CREATE_TIME_TABLE, DateUtils.formatDateTime());
    }
    // --author：zhoujf---end------date:20170207--------for:创建时间格式统一为yyyy-MM-dd HH:mm:ss
    if (data.containsKey(DataBaseConstant.CREATE_BY_TABLE) || getAllFieldByTableName(tableName).containsKey(DataBaseConstant.CREATE_BY_TABLE)) {
        data.put(DataBaseConstant.CREATE_BY_TABLE, ResourceUtil.getUserSystemData(DataBaseConstant.SYS_USER_CODE));
    }
    if (data.containsKey(DataBaseConstant.CREATE_NAME_TABLE) || getAllFieldByTableName(tableName).containsKey(DataBaseConstant.CREATE_NAME_TABLE)) {
        data.put(DataBaseConstant.CREATE_NAME_TABLE, ResourceUtil.getUserSystemData(DataBaseConstant.SYS_USER_NAME));
    }
    if (data.containsKey(DataBaseConstant.SYS_COMPANY_CODE_TABLE) || getAllFieldByTableName(tableName).containsKey(DataBaseConstant.SYS_COMPANY_CODE_TABLE)) {
        data.put(DataBaseConstant.SYS_COMPANY_CODE_TABLE, ResourceUtil.getUserSystemData(DataBaseConstant.SYS_COMPANY_CODE));
    }
    if (data.containsKey(DataBaseConstant.SYS_ORG_CODE_TABLE) || getAllFieldByTableName(tableName).containsKey(DataBaseConstant.SYS_ORG_CODE_TABLE)) {
        data.put(DataBaseConstant.SYS_ORG_CODE_TABLE, ResourceUtil.getUserSystemData(DataBaseConstant.SYS_ORG_CODE));
    }
    if (data.containsKey(DataBaseConstant.SYS_USER_CODE_TABLE) || getAllFieldByTableName(tableName).containsKey(DataBaseConstant.SYS_USER_CODE_TABLE)) {
        data.put(DataBaseConstant.SYS_USER_CODE_TABLE, ResourceUtil.getUserSystemData(DataBaseConstant.SYS_USER_CODE));
    }
    // --author：scott---begin------date:20170725--------for: 流程状态赋予默认值----------
    if (data.containsKey(DataBaseConstant.BPM_STATUS_TABLE) || getAllFieldByTableName(tableName).containsKey(DataBaseConstant.BPM_STATUS_TABLE)) {
        data.put(DataBaseConstant.BPM_STATUS_TABLE, ResourceUtil.getUserSystemData(DataBaseConstant.BPM_STATUS_TABLE));
    }
// --author：scott---end------date:20170725--------for:流程状态赋予默认值----------
// --author：zhoujf---end------date:20170207--------for:online表单 Excel数据导入时基本字段没有初始化数据
}


public void executeSqlExtend(String formId,String buttonCode,Map<String,Object> data){
    // 根据formId和buttonCode获取
    CgformButtonSqlEntity cgformButtonSqlVo = getCgformButtonSqlByCodeFormId(buttonCode, formId);
    if (cgformButtonSqlVo != null) {
        // 获取sql参数注入参数
        String sqlPlugin = cgformButtonSqlVo.getCgbSqlStr();
        if (StringUtils.isNotEmpty(sqlPlugin)) {
            // update-begin--Author:yugwu  Date:20170808 for:TASK #1826 【改造】online表单，sql增强支持minidao语法----
            boolean isMiniDao = false;
            logger.debug("sql plugin -------->" + sqlPlugin);
            String sqlPluginTemp = formateSQl(sqlPlugin, data);
            logger.debug("sql formate plugin -------->" + sqlPluginTemp);
            try {
                // 注入系统环境变量（支持占位符用法）
                data = minidaoReplaceExtendSqlSysVar(data);
                sqlPlugin = FreemarkerParseFactory.parseTemplateContent(sqlPluginTemp, data);
                isMiniDao = true;
            } catch (Exception e) {
                logger.debug(e.getMessage());
                logger.debug("minidao转换不成功，使用正常sql处理");
                sqlPlugin = sqlPluginTemp;
            }
            // update-end--Author:yugwu  Date:20170808 for:TASK #1826 【改造】online表单，sql增强支持minidao语法----
            String[] sqls = sqlPlugin.split(";");
            for (String sql : sqls) {
                // update-begin--Author:yugwu  Date:20170808 for:TASK #1826 【改造】online表单，sql增强支持minidao语法----
                // 添加逻辑后，有可能造成sql为空
                if (sql == null || sql.toLowerCase().trim().equals("")) {
                    continue;
                }
                // update-end--Author:yugwu  Date:20170808 for:TASK #1826 【改造】online表单，sql增强支持minidao语法----
                // update-start--Author:os-renjie  Date:20160602 for：   TASK #1092 【设计-online】sql增强支持调用存储过程或CURD方法
                /*if(sql.toLowerCase().indexOf(CgAutoListConstant.SQL_INSERT)!=-1
							||sql.toLowerCase().indexOf(CgAutoListConstant.SQL_UPDATE)!=-1){*/
                if (true) {
                    // update-end--Author:os-renjie  Date:20160602 for：   TASK #1092 【设计-online】sql增强支持调用存储过程或CURD方法
                    // 执行sql
                    // update-begin--Author:yugwu  Date:20170808 for:TASK #1826 【改造】online表单，sql增强支持minidao语法----
                    int num = 0;
                    if (isMiniDao) {
                        try {
                            num = namedParameterJdbcTemplate.update(sql, data);
                        } catch (Throwable e) {
                            e.printStackTrace();
                        }
                    } else {
                        num = this.executeSql(sql);
                    }
                    // update-end--Author:yugwu  Date:20170808 for:TASK #1826 【改造】online表单，sql增强支持minidao语法----
                    if (num > 0) {
                        logger.debug("sql plugin --execute success------>" + sql);
                    } else {
                        logger.debug("sql plugin --execute fail------>" + sql);
                    }
                }
            }
        }
    }
}


public List<Map<String,Object>> getFKField(String mainTableName,String subTableName){
    StringBuilder sql1 = new StringBuilder("");
    sql1.append("select f.* from cgform_field f ,cgform_head h");
    sql1.append(" where f.table_id = h.id ");
    sql1.append(" and h.table_name=? ");
    sql1.append(" and f.main_table=? ");
    List<Map<String, Object>> list = this.findForJdbc(sql1.toString(), subTableName, mainTableName);
    return list;
}


public CgformEnhanceJavaEntity getCgformEnhanceJavaEntityByCodeFormId(String buttonCode,String formId){
    StringBuilder hql = new StringBuilder("");
    hql.append(" from CgformEnhanceJavaEntity t");
    // update--begin--author:zhoujf Date:20180605 for:TASK #2745 【Sql 注入问题修改】
    hql.append(" where t.formId=?");
    hql.append(" and  t.buttonCode =?");
    // update-begin--author:scott  date:20170408 for：java增强设置有效无效状态---------
    hql.append(" and  t.activeStatus ='1'");
    // update-end--author:scott  date:20170408 for：java增强设置有效无效状态---------
    List<CgformEnhanceJavaEntity> list = this.findHql(hql.toString(), formId, buttonCode);
    // update--end--author:zhoujf Date:20180605 for:TASK #2745 【Sql 注入问题修改】
    if (list != null && list.size() > 0) {
        return list.get(0);
    }
    return null;
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
        // update-begin--Author:JueYue  Date:20140425 for：String不能强转
        // sql = sql.replace("${" + key + "}", "'"+String.valueOf(params.get(key))+"'");
        sql = sql.replace("#{" + key + "}", String.valueOf(params.get(key)));
    // update-end--Author:JueYue  Date:20140425 for：String不能强转
    }
    return sql;
}


public void keyAdapter(CgFormHeadEntity cgFormHeadEntity,Map<String,Object> data){
    String pkType = cgFormHeadEntity.getJformPkType();
    String dbType = DBTypeUtil.getDBType();
    if ("NATIVE".equalsIgnoreCase(pkType) || "SEQUENCE".equalsIgnoreCase(pkType)) {
        if ("sqlserver".equalsIgnoreCase(dbType)) {
            // 如果是数据库自增,sqlserver的insert语句中不允许出现该字段
            data.remove("id");
        }
    }
}


public Map<String,Object> minidaoReplaceExtendSqlSysVar(Map<String,Object> data){
    data.put("sys." + DataBaseConstant.SYS_USER_CODE_TABLE, ResourceUtil.getUserSystemData(DataBaseConstant.SYS_USER_CODE));
    data.put("sys." + DataBaseConstant.SYS_USER_NAME_TABLE, ResourceUtil.getUserSystemData(DataBaseConstant.SYS_USER_NAME));
    data.put("sys." + DataBaseConstant.SYS_ORG_CODE_TABLE, ResourceUtil.getUserSystemData(DataBaseConstant.SYS_ORG_CODE));
    data.put("sys." + DataBaseConstant.SYS_COMPANY_CODE_TABLE, ResourceUtil.getUserSystemData(DataBaseConstant.SYS_COMPANY_CODE));
    data.put("sys." + DataBaseConstant.SYS_DATE_TABLE, DateUtils.formatDate());
    data.put("sys." + DataBaseConstant.SYS_TIME_TABLE, DateUtils.formatTime());
    return data;
}


public List<CgformEnhanceJavaEntity> getCgformEnhanceJavaEntityByFormId(String formId){
    StringBuilder hql = new StringBuilder("");
    hql.append(" from CgformEnhanceJavaEntity t");
    hql.append(" where t.formId=?");
    List<CgformEnhanceJavaEntity> list = this.findHql(hql.toString(), formId);
    return list;
}


public void fillUpdateSysVar(String tableName,Map<String,Object> data){
    // update-begin--Author:dangzhenghui  Date:20170402 for：对外接口改造 对外api没有session 判断session是否存在
    if (ResourceUtil.getSessionUser() == null) {
        return;
    }
    // update-end--Author:dangzhenghui  Date:20170402 for：对外接口改造 对外api没有session 判断session是否存在
    // --author：zhoujf---start------date:20170207--------for:online表单 更新时基本字段没有初始化数据
    // --author：zhoujf---start------date:20170207--------for:更新时间格式统一为yyyy-MM-dd HH:mm:ss
    if (data.containsKey(DataBaseConstant.UPDATE_DATE_TABLE) || getAllFieldByTableName(tableName).containsKey(DataBaseConstant.CREATE_DATE_TABLE)) {
        data.put(DataBaseConstant.UPDATE_DATE_TABLE, DateUtils.formatDateTime());
    }
    if (data.containsKey(DataBaseConstant.UPDATE_TIME_TABLE) || getAllFieldByTableName(tableName).containsKey(DataBaseConstant.CREATE_DATE_TABLE)) {
        data.put(DataBaseConstant.UPDATE_TIME_TABLE, DateUtils.formatDateTime());
    }
    // --author：zhoujf---end------date:20170207--------for:更新时间格式统一为yyyy-MM-dd HH:mm:ss
    if (data.containsKey(DataBaseConstant.UPDATE_BY_TABLE) || getAllFieldByTableName(tableName).containsKey(DataBaseConstant.CREATE_DATE_TABLE)) {
        data.put(DataBaseConstant.UPDATE_BY_TABLE, ResourceUtil.getUserSystemData(DataBaseConstant.SYS_USER_CODE));
    }
    if (data.containsKey(DataBaseConstant.UPDATE_NAME_TABLE) || getAllFieldByTableName(tableName).containsKey(DataBaseConstant.CREATE_DATE_TABLE)) {
        data.put(DataBaseConstant.UPDATE_NAME_TABLE, ResourceUtil.getUserSystemData(DataBaseConstant.SYS_USER_NAME));
    }
// --author：zhoujf---end------date:20170207--------for:online表单 更新时基本字段没有初始化数据
}


}