package org.jeecgframework.web.system.service.impl;
 import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.jeecgframework.core.annotation.Ehcache;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.IpUtil;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.system.dao.JeecgDictDao;
import org.jeecgframework.web.system.pojo.base.DictEntity;
import org.jeecgframework.web.system.pojo.base.TSDatalogEntity;
import org.jeecgframework.web.system.pojo.base.TSDepartAuthGroupEntity;
import org.jeecgframework.web.system.pojo.base.TSDepartAuthgFunctionRelEntity;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSIcon;
import org.jeecgframework.web.system.pojo.base.TSLog;
import org.jeecgframework.web.system.pojo.base.TSOperation;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleFunction;
import org.jeecgframework.web.system.pojo.base.TSRoleOrg;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.CacheServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.util.OrgConstants;
import org.jeecgframework.workflow.service.ActivitiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import Interface.JeecgDictDao;
import Interface.CacheServiceI;
import DTO.TSType;
import DTO.TSTypegroup;
import DTO.CriteriaQuery;
@Service("systemService")
public class SystemServiceImpl extends CommonServiceImplimplements SystemService{

 private  Logger logger;

@Autowired
 private  JeecgDictDao jeecgDictDao;

@Autowired
 private  CacheServiceI cacheService;

@Autowired
 private  ActivitiService activitiService;


@Transactional(readOnly = true)
public void flushRoleFunciton(String id,TSFunction newFunction){
    TSFunction functionEntity = this.getEntity(TSFunction.class, id);
    if (functionEntity.getTSIcon() == null || !StringUtil.isNotEmpty(functionEntity.getTSIcon().getId())) {
        return;
    }
    TSIcon oldIcon = this.getEntity(TSIcon.class, functionEntity.getTSIcon().getId());
    if (!oldIcon.getIconClas().equals(newFunction.getTSIcon().getIconClas())) {
        // ????????????
        HttpSession session = ContextHolderUtils.getSession();
        TSUser user = ResourceUtil.getSessionUser();
        List<TSRoleUser> rUsers = this.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
        for (TSRoleUser ru : rUsers) {
            TSRole role = ru.getTSRole();
            session.removeAttribute(role.getId());
        }
    }
}


@Transactional(readOnly = true)
public String getAuthFilterJS(){
    StringBuilder out = new StringBuilder();
    out.append("<script type=\"text/javascript\">");
    out.append("$(document).ready(function(){");
    // update-begin--Author:anchao  Date:20140822 for???[bugfree???]????????????????????????????????????--------------------
    if (ResourceUtil.getSessionUser().getUserName().equals("admin") || !Globals.BUTTON_AUTHORITY_CHECK) {
        return "";
    } else {
        HttpServletRequest request = ContextHolderUtils.getRequest();
        Set<String> operationCodes = (Set<String>) request.getAttribute(Globals.OPERATIONCODES);
        if (null != operationCodes) {
            for (String MyoperationCode : operationCodes) {
                if (oConvertUtils.isEmpty(MyoperationCode))
                    break;
                TSOperation operation = this.getEntity(TSOperation.class, MyoperationCode);
                if (operation.getOperationcode().startsWith(".") || operation.getOperationcode().startsWith("#")) {
                    if (operation.getOperationType().intValue() == Globals.OPERATION_TYPE_HIDE) {
                        // out.append("$(\""+name+"\").find(\"#"+operation.getOperationcode().replaceAll(" ", "")+"\").hide();");
                        out.append("$(\"" + operation.getOperationcode().replaceAll(" ", "") + "\").hide();");
                    } else {
                        // out.append("$(\""+name+"\").find(\"#"+operation.getOperationcode().replaceAll(" ", "")+"\").find(\":input\").attr(\"disabled\",\"disabled\");");
                        out.append("$(\"" + operation.getOperationcode().replaceAll(" ", "") + "\").attr(\"disabled\",\"disabled\");");
                        out.append("$(\"" + operation.getOperationcode().replaceAll(" ", "") + "\").find(\":input\").attr(\"disabled\",\"disabled\");");
                    }
                }
            }
        } else {
            return "";
        }
    }
    // update-end--Author:anchao  Date:20140822 for???[bugfree???]????????????????????????????????????--------------------
    out.append("});");
    out.append("</script>");
    return out.toString();
}


@Transactional(readOnly = true)
public void refleshTypesCach(TSType type){
    Map<String, List<TSType>> typesList = null;
    TSTypegroup result = null;
    // update-begin--Author:taoyan  Date:20180621 for?????????????????????--------------------
    Object obj = cacheService.get(CacheServiceI.FOREVER_CACHE, ResourceUtil.DICT_TYPES_KEY);
    if (obj != null) {
        typesList = (Map<String, List<TSType>>) obj;
    } else {
        typesList = new HashMap<String, List<TSType>>();
    }
    TSTypegroup tsTypegroup = type.getTSTypegroup();
    TSTypegroup typeGroupEntity = this.commonDao.get(TSTypegroup.class, tsTypegroup.getId());
    // update-begin-author:taoYan date:20180627 for:HQL?????????????????? ??????????????????????????????????????????????????????initAllTypeGroups????????????????????????????????????????????????--
    List<TSType> tempList = this.commonDao.findHql("from TSType where TSTypegroup.id = ? order by orderNum", tsTypegroup.getId());
    List<TSType> types = new ArrayList<TSType>();
    for (TSType t : tempList) {
        TSType tt = new TSType();
        tt.setTSType(null);
        tt.setTSTypegroup(null);
        tt.setTSTypes(null);
        tt.setId(t.getId());
        tt.setOrderNum(t.getOrderNum());
        tt.setTypecode(t.getTypecode());
        tt.setTypename(t.getTypename());
        types.add(tt);
    }
    // update-end-author:taoYan date:20180627 for:HQL?????????????????? ??????????????????????????????????????????????????????initAllTypeGroups????????????????????????????????????????????????--
    // update-end--Author:taoyan  Date:20180621 for?????????????????????--------------------
    typesList.put(typeGroupEntity.getTypegroupcode().toLowerCase(), types);
    cacheService.put(CacheServiceI.FOREVER_CACHE, ResourceUtil.DICT_TYPES_KEY, typesList);
    logger.info("  ------ ????????????????????????????????????  ----------- typegroupcode: [{}] ", typeGroupEntity.getTypegroupcode().toLowerCase());
}


@Transactional(readOnly = true)
public void initAllTypeGroups(){
    List<TSTypegroup> typeGroups = this.commonDao.loadAll(TSTypegroup.class);
    Map<String, TSTypegroup> typeGroupsList = new HashMap<String, TSTypegroup>();
    Map<String, List<TSType>> typesList = new HashMap<String, List<TSType>>();
    for (TSTypegroup tsTypegroup : typeGroups) {
        // update-begin--Author:scott  Date:20180613 for????????????????????????????????????????????????????????????--------------------
        tsTypegroup.setTSTypes(null);
        typeGroupsList.put(tsTypegroup.getTypegroupcode().toLowerCase(), tsTypegroup);
        // update-begin--Author:taoyan  Date:20180621 for?????????????????????--------------------
        List<TSType> types = this.commonDao.findHql("from TSType where TSTypegroup.id = ? order by orderNum", tsTypegroup.getId());
        // update-end--Author:taoyan  Date:20180621 for?????????????????????--------------------
        for (TSType t : types) {
            t.setTSType(null);
            t.setTSTypegroup(null);
            t.setTSTypes(null);
        }
        // update-end--Author:scott  Date:20180613 for????????????????????????????????????????????????????????????--------------------
        typesList.put(tsTypegroup.getTypegroupcode().toLowerCase(), types);
    }
    cacheService.put(CacheServiceI.FOREVER_CACHE, ResourceUtil.DICT_TYPE_GROUPS_KEY, typeGroupsList);
    cacheService.put(CacheServiceI.FOREVER_CACHE, ResourceUtil.DICT_TYPES_KEY, typesList);
    logger.info("  ------ ?????????????????? ??????????????????-----------typeGroupsList-----size: [{}]", typeGroupsList.size());
    logger.info("  ------ ??????????????? ??????????????????-----------typesList-----size: [{}]", typesList.size());
}


@Override
@Transactional(readOnly = true)
public Set<String> getDepartAuthGroupOperationSet(String groupId,String functionId,String type){
    Set<String> operationCodes = new HashSet<String>();
    TSDepartAuthGroupEntity functionGroup = null;
    if (OrgConstants.GROUP_DEPART_ROLE.equals(type)) {
        TSRole role = commonDao.get(TSRole.class, groupId);
        CriteriaQuery cq1 = new CriteriaQuery(TSRoleFunction.class);
        cq1.eq("TSRole.id", role.getId());
        cq1.eq("TSFunction.id", functionId);
        cq1.add();
        List<TSRoleFunction> functionGroups = getListByCriteriaQuery(cq1, false);
        if (null != functionGroups && functionGroups.size() > 0) {
            TSRoleFunction tsFunctionGroup = functionGroups.get(0);
            if (null != tsFunctionGroup.getOperation()) {
                String[] operationArry = tsFunctionGroup.getOperation().split(",");
                for (int i = 0; i < operationArry.length; i++) {
                    operationCodes.add(operationArry[i]);
                }
            }
        }
    } else {
        functionGroup = commonDao.get(TSDepartAuthGroupEntity.class, groupId);
        CriteriaQuery cq1 = new CriteriaQuery(TSDepartAuthgFunctionRelEntity.class);
        cq1.eq("tsDepartAuthGroup.id", functionGroup.getId());
        cq1.eq("tsFunction.id", functionId);
        cq1.add();
        List<TSDepartAuthgFunctionRelEntity> functionGroups = getListByCriteriaQuery(cq1, false);
        if (null != functionGroups && functionGroups.size() > 0) {
            TSDepartAuthgFunctionRelEntity tsFunctionGroup = functionGroups.get(0);
            if (null != tsFunctionGroup.getOperation()) {
                String[] operationArry = tsFunctionGroup.getOperation().split(",");
                for (int i = 0; i < operationArry.length; i++) {
                    operationCodes.add(operationArry[i]);
                }
            }
        }
    }
    return operationCodes;
}


public void upTSIcons(TSIcon icon){
    ResourceUtil.allTSIcons.put(icon.getId(), icon);
}


@Override
@Ehcache(cacheName = "sysAuthCache")
@Transactional(readOnly = true)
public List<TSOperation> getLoginOperationsByUserId(String userId,String functionId,String orgId,String processnodeId){
    String hql = "FROM TSOperation where functionid = ?";
    List<TSOperation> operations = findHql(hql, functionId);
    if (operations == null || operations.size() < 1) {
        return null;
    }
    List<TSRoleUser> rUsers = findByProperty(TSRoleUser.class, "TSUser.id", userId);
    for (TSRoleUser ru : rUsers) {
        TSRole role = ru.getTSRole();
        CriteriaQuery cq1 = new CriteriaQuery(TSRoleFunction.class);
        cq1.eq("TSRole.id", role.getId());
        cq1.eq("TSFunction.id", functionId);
        cq1.add();
        List<TSRoleFunction> rFunctions = getListByCriteriaQuery(cq1, false);
        if (null != rFunctions && rFunctions.size() > 0) {
            TSRoleFunction tsRoleFunction = rFunctions.get(0);
            if (oConvertUtils.isNotEmpty(tsRoleFunction.getOperation())) {
                String[] operationArry = tsRoleFunction.getOperation().split(",");
                for (int i = 0; i < operationArry.length; i++) {
                    for (int j = 0; j < operations.size(); j++) {
                        if (operationArry[i].equals(operations.get(j).getId())) {
                            operations.remove(j);
                            break;
                        }
                    }
                }
            }
        }
    }
    // update-begin-Author:LiShaoQing -- date:20180604 -- for:TASK #2749 ?????????????????????????????????????????????????????????????????????----
    List<TSRoleOrg> tsRoleOrgs = findByProperty(TSRoleOrg.class, "tsDepart.id", orgId);
    for (TSRoleOrg tsRoleOrg : tsRoleOrgs) {
        TSRole role = tsRoleOrg.getTsRole();
        CriteriaQuery cq1 = new CriteriaQuery(TSRoleFunction.class);
        cq1.eq("TSRole.id", role.getId());
        cq1.eq("TSFunction.id", functionId);
        cq1.add();
        List<TSRoleFunction> rFunctions = getListByCriteriaQuery(cq1, false);
        if (null != rFunctions && rFunctions.size() > 0) {
            TSRoleFunction tsRoleFunction = rFunctions.get(0);
            if (oConvertUtils.isNotEmpty(tsRoleFunction.getOperation())) {
                String[] operationArry = tsRoleFunction.getOperation().split(",");
                for (int i = 0; i < operationArry.length; i++) {
                    for (int j = 0; j < operations.size(); j++) {
                        if (operationArry[i].equals(operations.get(j).getId())) {
                            operations.remove(j);
                            break;
                        }
                    }
                }
            }
        }
    }
    // update-end-Author:LiShaoQing -- date:20180604 -- for:TASK #2749 ?????????????????????????????????????????????????????????????????????----
    // -----???BPM?????????????????????????????????????????????---------------------------------------------------------------------------
    // TODO ????????????????????????????????????code???
    if (!StringUtil.isEmpty(processnodeId)) {
        Set<String> nodeOperationCodes = activitiService.getNodeOperaCodesByNodeIdAndFunctionId(processnodeId, functionId);
        for (String operationId : nodeOperationCodes) {
            for (int i = 0; i < operations.size(); i++) {
                if (operationId.equals(operations.get(i).getId())) {
                    operations.remove(i);
                    break;
                }
            }
        }
    }
    // update-begin--Author:zhoujf  Date:20180702 for?????????????????????
    // ???????????????????????????????????????????????????????????????
    if (!StringUtil.isEmpty(processnodeId)) {
        operations = activitiService.getNodeOperaCodesByNodeId(processnodeId, operations);
    }
    // update-end--Author:zhoujf  Date:20180702 for?????????????????????
    // -----???BPM?????????????????????????????????????????????-------------------------------------------------------------------------
    logger.debug("-----[ ???????????????????????????????????????operations ]-------operations: " + operations + "-------userId: " + userId + "------functionId: " + functionId);
    return operations;
}


@Ehcache(cacheName = "sysAuthCache")
@Transactional(readOnly = true)
public Set<String> getLoginDataRuleIdsByUserId(String userId,String functionId,String orgId){
    Set<String> dataRuleIds = new HashSet<String>();
    List<TSRoleUser> rUsers = findByProperty(TSRoleUser.class, "TSUser.id", userId);
    for (TSRoleUser ru : rUsers) {
        TSRole role = ru.getTSRole();
        CriteriaQuery cq1 = new CriteriaQuery(TSRoleFunction.class);
        cq1.eq("TSRole.id", role.getId());
        cq1.eq("TSFunction.id", functionId);
        cq1.add();
        List<TSRoleFunction> rFunctions = getListByCriteriaQuery(cq1, false);
        if (null != rFunctions && rFunctions.size() > 0) {
            TSRoleFunction tsRoleFunction = rFunctions.get(0);
            if (oConvertUtils.isNotEmpty(tsRoleFunction.getDataRule())) {
                String[] dataRuleArry = tsRoleFunction.getDataRule().split(",");
                for (int i = 0; i < dataRuleArry.length; i++) {
                    dataRuleIds.add(dataRuleArry[i]);
                }
            }
        }
    }
    // update-begin-Author:LiShaoQing -- date:20180604 -- for:TASK #2749 ?????????????????????????????????????????????????????????????????????----
    List<TSRoleOrg> tsRoleOrg = findByProperty(TSRoleOrg.class, "tsDepart.id", orgId);
    for (TSRoleOrg roleOrg : tsRoleOrg) {
        TSRole role = roleOrg.getTsRole();
        CriteriaQuery cq1 = new CriteriaQuery(TSRoleFunction.class);
        cq1.eq("TSRole.id", role.getId());
        cq1.eq("TSFunction.id", functionId);
        cq1.add();
        List<TSRoleFunction> rFunctions = getListByCriteriaQuery(cq1, false);
        if (null != rFunctions && rFunctions.size() > 0) {
            TSRoleFunction tsRoleFunction = rFunctions.get(0);
            if (oConvertUtils.isNotEmpty(tsRoleFunction.getDataRule())) {
                String[] dataRuleArry = tsRoleFunction.getDataRule().split(",");
                for (int i = 0; i < dataRuleArry.length; i++) {
                    dataRuleIds.add(dataRuleArry[i]);
                }
            }
        }
    }
    // update-end-Author:LiShaoQing -- date:20180604 -- for:TASK #2749 ?????????????????????????????????????????????????????????????????????----
    logger.debug("-----[ ???????????????????????????????????????IDS ]-------dataRuleIds: " + dataRuleIds + "--------userId: " + userId + "------functionId: " + functionId);
    return dataRuleIds;
}


public void addLog(String logcontent,Short operatetype,Short loglevel){
    HttpServletRequest request = ContextHolderUtils.getRequest();
    String broswer = BrowserUtils.checkBrowse(request);
    TSLog log = new TSLog();
    log.setLogcontent(logcontent);
    log.setLoglevel(loglevel);
    log.setOperatetype(operatetype);
    // update-start--Author:scott  Date:20170518 for?????????????????????IP?????????----
    log.setNote(IpUtil.getIpAddr(request));
    // update-end--Author:scott  Date:20170518 for?????????????????????IP?????????----
    log.setBroswer(broswer);
    /*start dangzhenghui 201703016TASK #1784 ???online bug???Online ??????????????????????????????*/
    log.setOperatetime(new Date());
    /* end dangzhenghui 201703016TASK #1784 ???online bug???Online ??????????????????????????????*/
    // log.setTSUser(ResourceUtil.getSessionUser());
    /*start chenqian 201708031TASK #2317 ????????????????????????????????????????????????????????????????????? [???????????????] [???????????????]*/
    TSUser u = ResourceUtil.getSessionUser();
    if (u != null) {
        log.setUserid(u.getId());
        log.setUsername(u.getUserName());
        log.setRealname(u.getRealName());
    }
    // update-end--Author:chenqian 201708031TASK #2317 ????????????????????????????????????????????????????????????????????? [???????????????] [???????????????]----
    commonDao.save(log);
}


@Transactional(readOnly = true)
public TSTypegroup getTypeGroupByCode(String typegroupCode){
    TSTypegroup tsTypegroup = commonDao.findUniqueByProperty(TSTypegroup.class, "typegroupcode", typegroupCode);
    return tsTypegroup;
}


@Transactional(readOnly = true)
public TSUser checkUserExits(TSUser user){
    return this.commonDao.getUserByUserIdAndUserNameExits(user);
}


@Transactional(readOnly = true)
public List<DictEntity> queryDict(String dicTable,String dicCode,String dicText){
    List<DictEntity> dictList = null;
    // step.1 ?????????????????????????????????????????????
    if (StringUtil.isEmpty(dicTable)) {
        dictList = jeecgDictDao.querySystemDict(dicCode);
        for (DictEntity t : dictList) {
            t.setTypename(MutiLangUtil.getLang(t.getTypename()));
        }
    } else {
        dicText = StringUtil.isEmpty(dicText, dicCode);
        dictList = jeecgDictDao.queryCustomDict(dicTable, dicCode, dicText);
    }
    return dictList;
}


@Override
public void addDataLog(String tableName,String dataId,String dataContent){
    int versionNumber = 0;
    // update-begin-author:taoyan date:20180528 for:TASK #2745 ???Sql ?????????????????????
    Integer integer = null;
    String sql = "select max(VERSION_NUMBER) as mvn from t_s_data_log where TABLE_NAME = ? and DATA_ID = ?";
    Map<String, Object> maxVersion = commonDao.findOneForJdbc(sql, tableName, dataId);
    if (maxVersion.get("mvn") != null) {
        integer = Integer.parseInt(maxVersion.get("mvn").toString());
    }
    // update-end-author:taoyan date:20180528 for:TASK #2745 ???Sql ?????????????????????
    if (integer != null) {
        versionNumber = integer.intValue();
    }
    TSDatalogEntity tsDatalogEntity = new TSDatalogEntity();
    tsDatalogEntity.setTableName(tableName);
    tsDatalogEntity.setDataId(dataId);
    tsDatalogEntity.setDataContent(dataContent);
    tsDatalogEntity.setVersionNumber(versionNumber + 1);
    commonDao.save(tsDatalogEntity);
}


@Transactional(readOnly = true)
public Set<String> getDataRuleIdsByRoleIdAndFunctionId(String roleId,String functionId){
    Set<String> operationCodes = new HashSet<String>();
    TSRole role = commonDao.get(TSRole.class, roleId);
    CriteriaQuery cq1 = new CriteriaQuery(TSRoleFunction.class);
    cq1.eq("TSRole.id", role.getId());
    cq1.eq("TSFunction.id", functionId);
    cq1.add();
    List<TSRoleFunction> rFunctions = getListByCriteriaQuery(cq1, false);
    if (null != rFunctions && rFunctions.size() > 0) {
        TSRoleFunction tsRoleFunction = rFunctions.get(0);
        if (null != tsRoleFunction.getDataRule()) {
            String[] operationArry = tsRoleFunction.getDataRule().split(",");
            for (int i = 0; i < operationArry.length; i++) {
                operationCodes.add(operationArry[i]);
            }
        }
    }
    return operationCodes;
}


@Transactional(readOnly = true)
public TSTypegroup getTypeGroup(String typegroupcode,String typgroupename){
    TSTypegroup tsTypegroup = commonDao.findUniqueByProperty(TSTypegroup.class, "typegroupcode", typegroupcode);
    if (tsTypegroup == null) {
        tsTypegroup = new TSTypegroup();
        tsTypegroup.setTypegroupcode(typegroupcode);
        tsTypegroup.setTypegroupname(typgroupename);
        commonDao.save(tsTypegroup);
    }
    return tsTypegroup;
}


@Transactional(readOnly = true)
public Set<String> getOperationCodesByRoleIdAndFunctionId(String roleId,String functionId){
    Set<String> operationCodes = new HashSet<String>();
    TSRole role = commonDao.get(TSRole.class, roleId);
    CriteriaQuery cq1 = new CriteriaQuery(TSRoleFunction.class);
    cq1.eq("TSRole.id", role.getId());
    cq1.eq("TSFunction.id", functionId);
    cq1.add();
    List<TSRoleFunction> rFunctions = getListByCriteriaQuery(cq1, false);
    if (null != rFunctions && rFunctions.size() > 0) {
        TSRoleFunction tsRoleFunction = rFunctions.get(0);
        if (null != tsRoleFunction.getOperation()) {
            String[] operationArry = tsRoleFunction.getOperation().split(",");
            for (int i = 0; i < operationArry.length; i++) {
                operationCodes.add(operationArry[i]);
            }
        }
    }
    return operationCodes;
}


@Override
@Transactional(readOnly = true)
public Set<String> getDepartAuthGroupDataRuleSet(String groupId,String functionId,String type){
    Set<String> dataRuleCodes = new HashSet<String>();
    TSDepartAuthGroupEntity functionGroup = null;
    if (OrgConstants.GROUP_DEPART_ROLE.equals(type)) {
        TSRole role = commonDao.get(TSRole.class, groupId);
        CriteriaQuery cq1 = new CriteriaQuery(TSRoleFunction.class);
        cq1.eq("TSRole.id", role.getId());
        cq1.eq("TSFunction.id", functionId);
        cq1.add();
        List<TSRoleFunction> functionGroups = getListByCriteriaQuery(cq1, false);
        if (null != functionGroups && functionGroups.size() > 0) {
            TSRoleFunction tsFunctionGroup = functionGroups.get(0);
            if (null != tsFunctionGroup.getDataRule()) {
                String[] dataRuleArry = tsFunctionGroup.getDataRule().split(",");
                for (int i = 0; i < dataRuleArry.length; i++) {
                    dataRuleCodes.add(dataRuleArry[i]);
                }
            }
        }
    } else {
        functionGroup = commonDao.get(TSDepartAuthGroupEntity.class, groupId);
        CriteriaQuery cq1 = new CriteriaQuery(TSDepartAuthgFunctionRelEntity.class);
        cq1.eq("tsDepartAuthGroup.id", functionGroup.getId());
        cq1.eq("tsFunction.id", functionId);
        cq1.add();
        List<TSDepartAuthgFunctionRelEntity> functionGroups = getListByCriteriaQuery(cq1, false);
        if (null != functionGroups && functionGroups.size() > 0) {
            TSDepartAuthgFunctionRelEntity tsFunctionGroup = functionGroups.get(0);
            if (null != tsFunctionGroup.getDatarule()) {
                String[] dataRuleArry = tsFunctionGroup.getDatarule().split(",");
                for (int i = 0; i < dataRuleArry.length; i++) {
                    dataRuleCodes.add(dataRuleArry[i]);
                }
            }
        }
    }
    return dataRuleCodes;
}


@Transactional(readOnly = true)
public void refleshTypeGroupCach(){
    Map<String, TSTypegroup> typeGroupsList = new HashMap<String, TSTypegroup>();
    List<TSTypegroup> typeGroups = this.commonDao.loadAll(TSTypegroup.class);
    for (TSTypegroup tsTypegroup : typeGroups) {
        typeGroupsList.put(tsTypegroup.getTypegroupcode().toLowerCase(), tsTypegroup);
    }
    cacheService.put(CacheServiceI.FOREVER_CACHE, ResourceUtil.DICT_TYPE_GROUPS_KEY, typeGroupsList);
    logger.info("  ------ ????????????????????????&??????????????????????????????  ------ refleshTypeGroupCach --------  ");
}


@Transactional(readOnly = true)
public String generateOrgCode(String id,String pid){
    // update-start--Author:zhangguoming  Date:20140901 for??????????????????????????????
    // ??????????????????
    int orgCodeLength = 2;
    if ("3".equals(ResourceUtil.getOrgCodeLengthType())) {
        // ??????2-???????????????3??????001
        orgCodeLength = 3;
    }
    // update-end--Author:zhangguoming  Date:20140901 for??????????????????????????????
    String newOrgCode = "";
    if (!StringUtils.hasText(pid)) {
        // ???????????????
        String sql = "select max(t.org_code) orgCode from t_s_depart t where t.parentdepartid is null";
        Map<String, Object> pOrgCodeMap = commonDao.findOneForJdbc(sql);
        if (pOrgCodeMap.get("orgCode") != null) {
            String curOrgCode = pOrgCodeMap.get("orgCode").toString();
            newOrgCode = String.format("%0" + orgCodeLength + "d", Integer.valueOf(curOrgCode) + 1);
        } else {
            newOrgCode = String.format("%0" + orgCodeLength + "d", 1);
        }
    } else {
        // ????????????
        String sql = "select max(t.org_code) orgCode from t_s_depart t where t.parentdepartid = ?";
        Map<String, Object> orgCodeMap = commonDao.findOneForJdbc(sql, pid);
        if (orgCodeMap.get("orgCode") != null) {
            // ????????????????????????
            String curOrgCode = orgCodeMap.get("orgCode").toString();
            String pOrgCode = curOrgCode.substring(0, curOrgCode.length() - orgCodeLength);
            String subOrgCode = curOrgCode.substring(curOrgCode.length() - orgCodeLength, curOrgCode.length());
            newOrgCode = pOrgCode + String.format("%0" + orgCodeLength + "d", Integer.valueOf(subOrgCode) + 1);
        } else {
            // ???????????????????????????
            String pOrgCodeSql = "select max(t.org_code) orgCode from t_s_depart t where t.id = ?";
            Map<String, Object> pOrgCodeMap = commonDao.findOneForJdbc(pOrgCodeSql, pid);
            String curOrgCode = pOrgCodeMap.get("orgCode").toString();
            newOrgCode = curOrgCode + String.format("%0" + orgCodeLength + "d", 1);
        }
    }
    return newOrgCode;
}


@Transactional(readOnly = true)
public void initAllTSIcons(){
    List<TSIcon> list = this.loadAll(TSIcon.class);
    for (TSIcon tsIcon : list) {
        ResourceUtil.allTSIcons.put(tsIcon.getId(), tsIcon);
    }
}


@Override
@Ehcache(cacheName = "sysAuthCache")
public String getFunctionIdByUrl(String url,String menuPath){
    // ???????????????????????????ID
    // ??????rest????????? ??????????????????
    String functionId = "";
    String realRequestPath = null;
    if (url.endsWith(".do") || url.endsWith(".action")) {
        realRequestPath = menuPath;
    } else {
        realRequestPath = url;
    }
    // ----???????????????????????????????????????---------------
    if (realRequestPath.indexOf("autoFormController/af/") > -1 && realRequestPath.indexOf("?") != -1) {
        realRequestPath = realRequestPath.substring(0, realRequestPath.indexOf("?"));
    }
    List<TSFunction> functions = this.findByProperty(TSFunction.class, "functionUrl", realRequestPath);
    if (functions.size() > 0) {
        functionId = functions.get(0).getId();
    }
    logger.debug("-----[ ??????????????????????????????????????????ID ]-------functionId: " + functionId + "------ url: " + url + "-----menuPath: " + menuPath);
    return functionId;
}


@Ehcache(cacheName = "sysAuthCache")
public boolean loginUserIsHasMenuAuth(String requestPath,String clickFunctionId,String userid,String orgId){
    // step.1 ??????????????????????????????????????????????????????????????????????????????[??????????????????????????????????????????]
    String hasMenuSql = "select count(*) from t_s_function where functiontype = 0 and functionurl = ?";
    Long hasMenuCount = this.getCountForJdbcParam(hasMenuSql, requestPath);
    logger.debug("-----[ ????????????????????????????????? ]-------requestPath----------" + requestPath + "------------hasMenuCount--------" + hasMenuCount);
    if (hasMenuCount <= 0) {
        return true;
    }
    // step.2 ?????????????????????????????????
    Long authSize = Long.valueOf(0);
    String sql = "SELECT count(*) FROM t_s_function f,t_s_role_function  rf,t_s_role_user ru " + " WHERE f.id=rf.functionid AND rf.roleid=ru.roleid AND " + "ru.userid=? AND f.functionurl = ?";
    authSize = this.getCountForJdbcParam(sql, userid, requestPath);
    if (authSize <= 0) {
        // step.3 ?????????????????????????????????????????????
        Long orgAuthSize = Long.valueOf(0);
        String functionOfOrgSql = "SELECT count(*) from t_s_function f, t_s_role_function rf, t_s_role_org ro  " + "WHERE f.ID=rf.functionid AND rf.roleid=ro.role_id " + "AND ro.org_id=? AND f.functionurl = ?";
        orgAuthSize = this.getCountForJdbcParam(functionOfOrgSql, orgId, requestPath);
        return orgAuthSize > 0;
    } else {
        return true;
    }
}


@Transactional(readOnly = true)
public TSType getType(String typecode,String typename,TSTypegroup tsTypegroup){
    // TSType actType = commonDao.findUniqueByProperty(TSType.class, "typecode", typecode,tsTypegroup.getId());
    List<TSType> ls = commonDao.findHql("from TSType where typecode = ? and typegroupid = ?", typecode, tsTypegroup.getId());
    TSType actType = null;
    if (ls == null || ls.size() == 0) {
        actType = new TSType();
        actType.setTypecode(typecode);
        actType.setTypename(typename);
        actType.setTSTypegroup(tsTypegroup);
        commonDao.save(actType);
    } else {
        actType = ls.get(0);
    }
    return actType;
}


@Transactional(readOnly = true)
public void refreshTypeGroupAndTypes(){
    logger.info("  ------ ????????????????????????&??????????????????????????????  ------ refreshTypeGroupAndTypes --------  ");
    this.initAllTypeGroups();
}


public void delTSIcons(TSIcon icon){
    ResourceUtil.allTSIcons.remove(icon.getId());
}


}