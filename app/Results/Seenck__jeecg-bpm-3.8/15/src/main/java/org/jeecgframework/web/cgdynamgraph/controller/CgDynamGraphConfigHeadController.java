package org.jeecgframework.web.cgdynamgraph.controller;
 import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.online.def.CgReportConstant;
import org.jeecgframework.core.util.IpUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.cgdynamgraph.entity.core.CgDynamGraphConfigHeadEntity;
import org.jeecgframework.web.cgdynamgraph.entity.core.CgDynamGraphConfigItemEntity;
import org.jeecgframework.web.cgdynamgraph.entity.core.CgDynamGraphConfigParamEntity;
import org.jeecgframework.web.cgdynamgraph.page.core.CgDynamGraphConfigHeadPage;
import org.jeecgframework.web.cgdynamgraph.service.core.CgDynamGraphConfigHeadServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import Interface.SystemService;
import DTO.AjaxJson;
import DTO.CriteriaQuery;
@Controller
@RequestMapping("/cgDynamGraphConfigHeadController.do")
public class CgDynamGraphConfigHeadController extends BaseController{

 private  Logger logger;

@Autowired
 private  CgDynamGraphConfigHeadServiceI cgDynamGraphConfigHeadService;

@Autowired
 private  SystemService systemService;


@RequestMapping(params = "goUpdate")
public ModelAndView goUpdate(CgDynamGraphConfigHeadEntity cgDynamGraphConfigHead,HttpServletRequest req){
    if (StringUtil.isNotEmpty(cgDynamGraphConfigHead.getId())) {
        cgDynamGraphConfigHead = cgDynamGraphConfigHeadService.getEntity(CgDynamGraphConfigHeadEntity.class, cgDynamGraphConfigHead.getId());
        req.setAttribute("cgDynamGraphConfigHeadPage", cgDynamGraphConfigHead);
    }
    return new ModelAndView("jeecg/cgdynamgraph/core/cgDynamGraphConfigHead-update");
}


@RequestMapping(params = "cgDynamGraphConfigItemList")
public ModelAndView cgDynamGraphConfigItemList(CgDynamGraphConfigHeadEntity cgDynamGraphConfigHead,HttpServletRequest req){
    // ===================================================================================
    // ????????????
    Object id0 = cgDynamGraphConfigHead.getId();
    // ===================================================================================
    // ??????-????????????????????????
    String hql0 = "from CgDynamGraphConfigItemEntity where 1 = 1 AND cgrheadId = ? ";
    try {
        List<CgDynamGraphConfigItemEntity> cgDynamGraphConfigItemEntityList = systemService.findHql(hql0, id0);
        req.setAttribute("cgDynamGraphConfigItemList", cgDynamGraphConfigItemEntityList);
    } catch (Exception e) {
        logger.info(e.getMessage());
    }
    return new ModelAndView("jeecg/cgdynamgraph/core/cgDynamGraphConfigItemList");
}


@RequestMapping(params = "cgDynamGraphConfigParamList")
public ModelAndView cgDynamGraphConfigParamList(CgDynamGraphConfigHeadEntity cgDynamGraphConfigHead,HttpServletRequest req){
    // ===================================================================================
    // ????????????
    Object id0 = cgDynamGraphConfigHead.getId();
    // ===================================================================================
    // ??????-????????????????????????
    String hql0 = "from CgDynamGraphConfigParamEntity where 1 = 1 AND cgrheadId = ? ";
    try {
        List<CgDynamGraphConfigParamEntity> cgDynamGraphConfigParamEntityList = systemService.findHql(hql0, id0);
        req.setAttribute("cgDynamGraphConfigParamList", cgDynamGraphConfigParamEntityList);
    } catch (Exception e) {
        logger.info(e.getMessage());
    }
    return new ModelAndView("jeecg/cgdynamgraph/core/cgDynamGraphConfigParamList");
}


@RequestMapping(params = "doAdd")
@ResponseBody
public AjaxJson doAdd(CgDynamGraphConfigHeadEntity cgDynamGraphConfigHead,CgDynamGraphConfigHeadPage cgDynamGraphConfigHeadPage,HttpServletRequest request){
    String message = null;
    List<CgDynamGraphConfigItemEntity> cgDynamGraphConfigItemList = cgDynamGraphConfigHeadPage.getCgDynamGraphConfigItemList();
    List<CgDynamGraphConfigParamEntity> cgDynamGraphConfigParamList = cgDynamGraphConfigHeadPage.getCgDynamGraphConfigParamList();
    AjaxJson j = new AjaxJson();
    message = "????????????";
    try {
        // ??????????????????????????????????????????
        for (CgDynamGraphConfigParamEntity parm : cgDynamGraphConfigParamList) {
            for (CgDynamGraphConfigItemEntity item : cgDynamGraphConfigItemList) {
                if (CgReportConstant.BOOL_TRUE.equalsIgnoreCase(item.getSFlag()) && parm.getParamName().equals(item.getFieldName())) {
                    message = "?????????????????????" + parm.getParamName() + "??????????????????????????????????????????????????????????????????";
                    j.setMsg(message);
                    j.setSuccess(false);
                    return j;
                }
            }
        }
        cgDynamGraphConfigHeadService.addMain(cgDynamGraphConfigHead, cgDynamGraphConfigItemList, cgDynamGraphConfigParamList);
        systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        logger.info("[" + IpUtil.getIpAddr(request) + "][online??????????????????][" + cgDynamGraphConfigHead.getCode() + "]" + message);
    } catch (Exception e) {
        e.printStackTrace();
        message = "????????????????????????????????????";
        throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "doDel")
@ResponseBody
public AjaxJson doDel(CgDynamGraphConfigHeadEntity cgDynamGraphConfigHead,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    cgDynamGraphConfigHead = systemService.getEntity(CgDynamGraphConfigHeadEntity.class, cgDynamGraphConfigHead.getId());
    message = "????????????????????????????????????";
    try {
        cgDynamGraphConfigHeadService.delMain(cgDynamGraphConfigHead);
        systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        logger.info("[" + IpUtil.getIpAddr(request) + "][online??????????????????][" + cgDynamGraphConfigHead.getCode() + "]" + message);
    } catch (Exception e) {
        e.printStackTrace();
        message = "????????????????????????????????????";
        throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "cgDynamGraphConfigHead")
public ModelAndView CgDynamGraphConfigHead(HttpServletRequest request){
    return new ModelAndView("jeecg/cgdynamgraph/core/cgDynamGraphConfigHeadList");
}


@RequestMapping(params = "doBatchDel")
@ResponseBody
public AjaxJson doBatchDel(String ids,HttpServletRequest request){
    String message = null;
    AjaxJson j = new AjaxJson();
    message = "????????????????????????????????????";
    try {
        for (String id : ids.split(",")) {
            CgDynamGraphConfigHeadEntity cgDynamGraphConfigHead = systemService.getEntity(CgDynamGraphConfigHeadEntity.class, id);
            cgDynamGraphConfigHeadService.delMain(cgDynamGraphConfigHead);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            logger.info("[" + IpUtil.getIpAddr(request) + "][online????????????????????????][" + cgDynamGraphConfigHead.getCode() + "]" + message);
        }
    } catch (Exception e) {
        e.printStackTrace();
        message = "????????????????????????????????????";
        throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "goAdd")
public ModelAndView goAdd(CgDynamGraphConfigHeadEntity cgDynamGraphConfigHead,HttpServletRequest req){
    if (StringUtil.isNotEmpty(cgDynamGraphConfigHead.getId())) {
        cgDynamGraphConfigHead = cgDynamGraphConfigHeadService.getEntity(CgDynamGraphConfigHeadEntity.class, cgDynamGraphConfigHead.getId());
        req.setAttribute("cgDynamGraphConfigHeadPage", cgDynamGraphConfigHead);
    }
    return new ModelAndView("jeecg/cgdynamgraph/core/cgDynamGraphConfigHead-add");
}


@RequestMapping(params = "doUpdate")
@ResponseBody
public AjaxJson doUpdate(CgDynamGraphConfigHeadEntity cgDynamGraphConfigHead,CgDynamGraphConfigHeadPage cgDynamGraphConfigHeadPage,HttpServletRequest request){
    String message = null;
    List<CgDynamGraphConfigItemEntity> cgDynamGraphConfigItemList = cgDynamGraphConfigHeadPage.getCgDynamGraphConfigItemList();
    List<CgDynamGraphConfigParamEntity> cgDynamGraphConfigParamList = cgDynamGraphConfigHeadPage.getCgDynamGraphConfigParamList();
    AjaxJson j = new AjaxJson();
    message = "????????????";
    try {
        // ??????????????????????????????????????????
        for (CgDynamGraphConfigParamEntity parm : cgDynamGraphConfigParamList) {
            for (CgDynamGraphConfigItemEntity item : cgDynamGraphConfigItemList) {
                if (CgReportConstant.BOOL_TRUE.equalsIgnoreCase(item.getSFlag()) && parm.getParamName().equals(item.getFieldName())) {
                    message = "?????????????????????" + parm.getParamName() + "??????????????????????????????????????????????????????????????????";
                    j.setMsg(message);
                    j.setSuccess(false);
                    return j;
                }
            }
        }
        cgDynamGraphConfigHeadService.updateMain(cgDynamGraphConfigHead, cgDynamGraphConfigItemList, cgDynamGraphConfigParamList);
        systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        logger.info("[" + IpUtil.getIpAddr(request) + "][online??????????????????][" + cgDynamGraphConfigHead.getCode() + "]" + message);
    } catch (Exception e) {
        e.printStackTrace();
        message = "????????????????????????????????????";
        throw new BusinessException(e.getMessage());
    }
    j.setMsg(message);
    return j;
}


@RequestMapping(params = "datagrid")
public void datagrid(CgDynamGraphConfigHeadEntity cgDynamGraphConfigHead,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid){
    CriteriaQuery cq = new CriteriaQuery(CgDynamGraphConfigHeadEntity.class, dataGrid);
    // ?????????????????????
    org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, cgDynamGraphConfigHead);
    try {
    // ???????????????????????????
    } catch (Exception e) {
        throw new BusinessException(e.getMessage());
    }
    cq.add();
    this.cgDynamGraphConfigHeadService.getDataGridReturn(cq, true);
    TagUtil.datagrid(response, dataGrid);
}


@RequestMapping(params = "popmenulink")
public ModelAndView popmenulink(ModelMap modelMap,String url,String title,HttpServletRequest request){
    modelMap.put("title", title);
    modelMap.put("url", url);
    StringBuilder sb = new StringBuilder("");
    try {
        CgDynamGraphConfigHeadEntity cgDynamGraphConfigHeadEntity = systemService.findUniqueByProperty(CgDynamGraphConfigHeadEntity.class, "code", title);
        String hql0 = "from CgDynamGraphConfigParamEntity where 1 = 1 AND cgrheadId = ? ";
        List<CgDynamGraphConfigParamEntity> cgreportConfigParamList = systemService.findHql(hql0, cgDynamGraphConfigHeadEntity.getId());
        if (cgreportConfigParamList != null & cgreportConfigParamList.size() > 0) {
            for (CgDynamGraphConfigParamEntity cgreportConfigParam : cgreportConfigParamList) {
                sb.append("&").append(cgreportConfigParam.getParamName()).append("=");
                if (StringUtil.isNotEmpty(cgreportConfigParam.getParamValue())) {
                    sb.append(cgreportConfigParam.getParamValue());
                } else {
                    sb.append("${" + cgreportConfigParam.getParamName() + "}");
                }
            }
        }
    } catch (Exception e) {
    }
    modelMap.put("params", sb.toString());
    return new ModelAndView("jeecg/cgreport/core/popmenulink");
}


}