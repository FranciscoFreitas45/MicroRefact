package com.gs.controller.pickingManage;
 import ch.qos.logback.classic.Logger;
import com.gs.bean;
import com.gs.bean.view.MaterialURTemp;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.RoleUtil;
import com.gs.common.util.SessionUtil;
import com.gs.common.util.UUIDUtil;
import com.gs.controller.SystemManageController;
import com.gs.service;
import org.activiti.engine;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntity;
import org.activiti.engine.impl.persistence.entity.HistoricVariableInstanceEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util;
import com.gs.Interface.AccessoriesService;
import com.gs.Interface.WorkInfoService;
import com.gs.DTO.Pager;
import com.gs.DTO.Pager4EasyUI;
import com.gs.DTO.User;
import com.gs.DTO.ControllerResult;
import com.gs.DTO.Accessories;
import com.gs.DTO.ComboBox4EasyUI;
@Controller
@RequestMapping("/materials")
public class MaterialsController {

 private  Logger logger;

@Resource
 private  MaterialListService materialListService;

@Resource
 private  AccessoriesService accessoriesService;

@Resource
 private  MaterialUseService materialUseService;

@Resource
 private  MaterialReturnService materialReturnService;

@Resource
 private  WorkInfoService workInfoService;

@Resource
 private  RepositoryService repositoryService;

@Resource
 private  RuntimeService runtimeService;

@Resource
 private  TaskService taskService;

@Resource
 private  FormService formService;

@Resource
 private  HistoryService historyService;

@Resource
 private  ManagementService managementService;

@Resource
 private  IdentityService identityService;


@ResponseBody
// ???????????????????????????????????????
@RequestMapping("finishWorkByUser")
public Pager4EasyUI queryFinishWorkByUser(int pageNumber,int pageSize,HttpSession session){
    User user = (User) session.getAttribute("user");
    if (SessionUtil.isLogin(session)) {
        Pager4EasyUI pager4EasyUI = new Pager4EasyUI();
        Pager pager = new Pager();
        // ???????????????1?????????,?????????????????????????????????????????????????????????????????????
        int total = materialUseService.countUserWorksStatus(user, "Y");
        pager.setPageNo(pageNumber);
        pager.setPageSize(pageSize);
        List workInfos = materialUseService.userWorksStatusByPager(user, "Y", pager);
        pager4EasyUI.setTotal(total);
        pager4EasyUI.setRows(workInfos);
        return pager4EasyUI;
    } else {
        logger.info("????????????");
        return null;
    }
}


@ResponseBody
// ???????????????????????????????????????
@RequestMapping("history")
public Pager4EasyUI historyByPager(int pageNumber,int pageSize,HttpSession session){
    // TODO ????????????
    if (SessionUtil.isLogin(session)) {
        User user = (User) session.getAttribute("user");
        Pager4EasyUI pager4EasyUI = new Pager4EasyUI();
        Pager pager = new Pager();
        // ???????????????1?????????,?????????????????????????????????????????????????????????????????????
        int total = materialUseService.countUserHist(user.getUserId());
        pager.setPageNo(pageNumber);
        pager.setPageSize(pageSize);
        pager4EasyUI.setTotal(total);
        List rows = materialUseService.userHistByPager(pager, user.getUserId());
        pager4EasyUI.setRows(rows);
        return pager4EasyUI;
    } else {
        logger.info("????????????");
        return null;
    }
}


@ResponseBody
@RequestMapping(value = "nearCompanys")
public List<Company> nearCompanys(HttpServletRequest req){
    Map pointsMap = new HashMap();
    double maxLng = str2dou(req.getParameter("maxLng"));
    double maxLat = str2dou(req.getParameter("maxLat"));
    double minLng = str2dou(req.getParameter("minLng"));
    double minLat = str2dou(req.getParameter("minLat"));
    pointsMap.put("maxLng", maxLng);
    pointsMap.put("maxLat", maxLat);
    pointsMap.put("minLng", minLng);
    pointsMap.put("minLat", minLat);
    return materialUseService.queryNearCompanys(pointsMap);
}


public ControllerResult isSuc(int resultCount,String sucmsg,String ermsg){
    if (resultCount > 0) {
        return ControllerResult.getSuccessResult(sucmsg);
    }
    return ControllerResult.getFailResult(ermsg);
}


@ResponseBody
@RequestMapping(value = "queryAllAccInv", method = RequestMethod.GET)
public List<ComboBox4EasyUI> queryAllAccInv(HttpSession session){
    if (SessionUtil.isLogin(session)) {
        User user = (User) session.getAttribute("user");
        String roles = "?????????????????????,?????????????????????,?????????????????????,?????????????????????,??????????????????,??????????????????,????????????????????????,????????????????????????,????????????????????????,????????????????????????,?????????????????????????????????,?????????????????????,?????????????????????";
        if (RoleUtil.checkRoles(roles)) {
            logger.info("????????????????????????");
            List<Accessories> accessories = materialUseService.accQueryAll(user);
            List<ComboBox4EasyUI> comboxs = new ArrayList<ComboBox4EasyUI>();
            for (Accessories c : accessories) {
                ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
                comboBox4EasyUI.setId(c.getAccId());
                comboBox4EasyUI.setText(c.getAccName());
                comboxs.add(comboBox4EasyUI);
            }
            return comboxs;
        } else {
            logger.info("?????????????????????????????????");
            return null;
        }
    } else {
        logger.info("????????????");
        return null;
    }
}


@ResponseBody
@RequestMapping(value = "queryByResultCondition")
public List<MaterialReturn> queryByResultCondition(HttpSession session,String start,String end,String type,String companyId,String accTypeId){
    if (SessionUtil.isLogin(session)) {
        String roles = "?????????????????????,?????????????????????,?????????????????????,?????????????????????";
        if (RoleUtil.checkRoles(roles)) {
            List<MaterialReturn> list = null;
            User user = (User) session.getAttribute("user");
            if (type != null && !type.equals("")) {
                if (type.equals("year")) {
                    if (companyId != null) {
                        list = materialReturnService.queryByCondition(start, end, companyId, accTypeId, "year");
                    } else {
                        list = materialReturnService.queryByCondition(start, end, user.getCompanyId(), accTypeId, "year");
                    }
                } else if (type.equals("quarter")) {
                    if (companyId != null) {
                        list = materialReturnService.queryByCondition(start, end, companyId, accTypeId, "quarter");
                    } else {
                        list = materialReturnService.queryByCondition(start, end, user.getCompanyId(), accTypeId, "quarter");
                    }
                } else if (type.equals("month")) {
                    if (companyId != null) {
                        list = materialReturnService.queryByCondition(start, end, companyId, accTypeId, "month");
                    } else {
                        list = materialReturnService.queryByCondition(start, end, user.getCompanyId(), accTypeId, "month");
                    }
                } else if (type.equals("week")) {
                    if (companyId != null) {
                        list = materialReturnService.queryByCondition(start, end, companyId, accTypeId, "week");
                    } else {
                        list = materialReturnService.queryByCondition(start, end, user.getCompanyId(), accTypeId, "week");
                    }
                } else if (type.equals("day")) {
                    if (companyId != null) {
                        list = materialReturnService.queryByCondition(start, end, companyId, accTypeId, "day");
                    } else {
                        list = materialReturnService.queryByCondition(start, end, user.getCompanyId(), accTypeId, "day");
                    }
                }
            }
            return list;
        } else {
            logger.info("?????????????????????????????????????????????");
            return null;
        }
    } else {
        logger.info("????????????");
        return null;
    }
}


@ResponseBody
@RequestMapping("insert")
public ControllerResult insertMaterials(MaterialList materialList,HttpSession session){
    // TODO ??????, ?????????????????????
    if (SessionUtil.isLogin(session)) {
        String roles = "?????????????????????,?????????????????????,?????????????????????,?????????????????????,??????????????????,??????????????????,????????????????????????,????????????????????????,????????????????????????,????????????????????????,?????????????????????????????????";
        if (RoleUtil.checkRoles(roles)) {
            int resultCount = materialListService.insert(materialList);
            return isSuc(resultCount, "????????????", "????????????");
        } else {
            logger.info("???????????????????????????????????????");
            return ControllerResult.getNotRoleResult("????????????");
        }
    } else {
        logger.info("????????????");
        return null;
    }
}


public double str2dou(String str){
    try {
        return Double.parseDouble(str);
    } catch (Exception e) {
        e.printStackTrace();
        return 0;
    }
}


public void setFlowingVars(List<MaterialList> list){
    for (MaterialList mater : list) {
        String recordId = mater.getRecord().getRecordId();
        String accId = mater.getAccId();
        MaterialURTemp temp = materialListService.queryFlowVarsByRecordId(recordId, accId);
        Map map = new HashMap();
        map.put("materialURTemp", temp);
        mater.setOther(map);
    }
}


public boolean addMaterialUseAReturnTable(Map varMap,String startUserId){
    String recordId = varMap.get("recordId").toString();
    String accId = varMap.get("accId").toString();
    int accCount = Integer.parseInt(varMap.get("accCount").toString());
    int resultCount = 0;
    MaterialUse materialUse = new MaterialUse();
    materialUse.setMaterialUseId(UUIDUtil.uuid());
    // TODO  ????????????????????????
    if (accCount > 0) {
        materialUse.setMatainRecordId(recordId);
        materialUse.setAccId(accId);
        materialUse.setAccCount(Math.abs(accCount));
        materialUse.setMuCreatedTime(new Date());
        materialUse.setMuUseDate(new Date());
        resultCount = materialUseService.insert(materialUse);
        if (resultCount > 0) {
            accessoriesService.reduceCount(Math.abs(accCount), accId);
        }
    } else {
        MaterialReturn materialReturn = new MaterialReturn();
        materialReturn.setMaterialReturnId(materialUse.getMaterialUseId());
        materialReturn.setAccCount(Math.abs(accCount));
        materialReturn.setAccId(accId);
        materialReturn.setMatainRecordId(recordId);
        materialReturn.setMrCreatedDate(new Date());
        materialReturn.setMrReturnDate(new Date());
        resultCount = materialReturnService.insert(materialReturn);
        if (resultCount > 0) {
            accessoriesService.reduceCount(-Math.abs(accCount), accId);
        }
    }
    if (resultCount > 0) {
        return true;
    }
    return false;
}


// ??????????????????
@ResponseBody
@RequestMapping("queryUserMaterialsByPager")
public Pager4EasyUI materialsByPager(int pageNumber,int pageSize,HttpSession session){
    // TODO ????????????
    if (SessionUtil.isLogin(session)) {
        User user = (User) session.getAttribute("user");
        Pager pager = new Pager();
        Pager4EasyUI pager4EasyUI = new Pager4EasyUI();
        pager.setPageNo(pageNumber);
        pager.setPageSize(pageSize);
        int total = materialListService.count(user.getUserId());
        pager4EasyUI.setTotal(total);
        List list = materialListService.queryByPager(user.getUserId(), pager);
        pager4EasyUI.setRows(list);
        return pager4EasyUI;
    } else {
        logger.info("????????????");
        return null;
    }
}


@ResponseBody
@RequestMapping("recordAccsByPager")
public Pager4EasyUI recordAccsByPager(int pageNumber,int pageSize,String recordId,HttpSession session){
    // TODO ????????????????????????
    if (SessionUtil.isLogin(session)) {
        User user = (User) session.getAttribute("user");
        Pager pager = new Pager();
        pager.setPageNo(pageNumber);
        pager.setPageSize(pageSize);
        int total = materialListService.countRecordAccs(recordId, user);
        Pager4EasyUI pager4EasyUI = new Pager4EasyUI();
        pager4EasyUI.setTotal(total);
        List list = materialListService.recordAccsByPager(recordId, user, pager);
        setFlowingVars(list);
        pager4EasyUI.setRows(list);
        return pager4EasyUI;
    } else {
        logger.info("????????????");
        return null;
    }
}


@ResponseBody
@RequestMapping("doReview")
public ControllerResult doReview(MaterialURTemp materialUse,HttpServletRequest req,HttpSession session){
    // ???????????????????????????
    // TODO ??????
    if (SessionUtil.isLogin(session)) {
        String roles = "?????????????????????,?????????????????????,????????????????????????";
        if (RoleUtil.checkRoles(roles)) {
            User user = (User) session.getAttribute("user");
            final String reviewTaskKey = "usertask2";
            TaskQuery taskQuery = taskService.createTaskQuery();
            String proInsId = materialUse.getProcessInstanceId();
            Task task = taskQuery.processInstanceId(proInsId).singleResult();
            if (task != null && task.getTaskDefinitionKey().equals(reviewTaskKey)) {
                boolean isOK = Boolean.parseBoolean(req.getParameter("isOK"));
                String respMsg = materialUse.getRespMsg();
                Map map = new HashMap();
                map.put("isOK", isOK);
                map.put("respMsg", respMsg);
                String resultPre = "??????";
                if (isOK) {
                    resultPre = "??????";
                }
                taskService.setAssignee(task.getId(), user.getUserId());
                return nextTask(proInsId, task.getId(), map, isOK);
            } else {
                return ControllerResult.getFailResult("????????????,???????????????????????????");
            }
        } else {
            logger.info("???????????????????????????/???????????????");
            return ControllerResult.getNotRoleResult("????????????");
        }
    } else {
        logger.info("????????????");
        return null;
    }
}


public ControllerResult nextTask(String proInsId,String taskId,Map map,Boolean isOK){
    HistoricProcessInstance hisProInst = historyService.createHistoricProcessInstanceQuery().processInstanceId(proInsId).singleResult();
    Map varMap = taskService.getVariables(taskId);
    String recordId = varMap.get("recordId").toString();
    String accId = varMap.get("accId").toString();
    int accCount = Integer.parseInt(varMap.get("accCount").toString());
    String startUserId = hisProInst.getStartUserId();
    try {
        taskService.complete(taskId, map);
        if (isOK) {
            if (addMaterialUseAReturnTable(varMap, startUserId)) {
                return ControllerResult.getSuccessResult("????????????");
            }
            return ControllerResult.getFailResult("????????????");
        } else {
            return ControllerResult.getSuccessResult("????????????");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return ControllerResult.getFailResult("????????????");
}


@ResponseBody
@RequestMapping(value = "queryByCondition")
public List<MaterialUse> queryByCondition(HttpSession session,String start,String end,String type,String companyId,String accTypeId){
    if (SessionUtil.isLogin(session)) {
        String roles = "?????????????????????,?????????????????????,?????????????????????,?????????????????????";
        if (RoleUtil.checkRoles(roles)) {
            List<MaterialUse> list = null;
            User user = (User) session.getAttribute("user");
            if (type != null && !type.equals("")) {
                if (type.equals("year")) {
                    if (companyId != null) {
                        list = materialUseService.queryByCondition(start, end, companyId, accTypeId, "year");
                    } else {
                        list = materialUseService.queryByCondition(start, end, user.getCompanyId(), accTypeId, "year");
                    }
                } else if (type.equals("quarter")) {
                    if (companyId != null) {
                        list = materialUseService.queryByCondition(start, end, companyId, accTypeId, "quarter");
                    } else {
                        list = materialUseService.queryByCondition(start, end, user.getCompanyId(), accTypeId, "quarter");
                    }
                } else if (type.equals("month")) {
                    if (companyId != null) {
                        list = materialUseService.queryByCondition(start, end, companyId, accTypeId, "month");
                    } else {
                        list = materialUseService.queryByCondition(start, end, user.getCompanyId(), accTypeId, "month");
                    }
                } else if (type.equals("week")) {
                    if (companyId != null) {
                        list = materialUseService.queryByCondition(start, end, companyId, accTypeId, "week");
                    } else {
                        list = materialUseService.queryByCondition(start, end, user.getCompanyId(), accTypeId, "week");
                    }
                } else if (type.equals("day")) {
                    if (companyId != null) {
                        list = materialUseService.queryByCondition(start, end, companyId, accTypeId, "day");
                    } else {
                        list = materialUseService.queryByCondition(start, end, user.getCompanyId(), accTypeId, "day");
                    }
                }
            }
            return list;
        } else {
            logger.info("?????????????????????????????????????????????");
            return null;
        }
    } else {
        logger.info("????????????");
        return null;
    }
}


}