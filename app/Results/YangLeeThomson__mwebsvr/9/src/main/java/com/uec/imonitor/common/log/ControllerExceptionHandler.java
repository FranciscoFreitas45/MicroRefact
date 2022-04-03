package com.uec.imonitor.common.log;
 import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import com.uec.imonitor.common.base.BaseController;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.common.exception.BusinessException;
import com.uec.imonitor.config.bean.ConfigPageParamEntity;
import com.uec.imonitor.config.bean.ConfigTenantParamEntity;
import com.uec.imonitor.config.service.IConfigPageParamService;
import com.uec.imonitor.config.service.IConfigTenantParamService;
import com.uec.imonitor.tenant.bean.TenantEntity;
import com.uec.imonitor.tenant.service.ITenantService;
@ControllerAdvice
public class ControllerExceptionHandler extends BaseController{

 private Log log;

@Autowired
@Qualifier("tenantService")
 private  ITenantService tenantService;

@Autowired
@Qualifier("configPageParamService")
 private  IConfigPageParamService configPageParamService;

@Autowired
@Qualifier("configTenantParamService")
 private  IConfigTenantParamService configTenantParamService;

@Value("${inews.image.server.address}")
 private  String inewsImageServer;

 public  String DEFAULT_ERROR_VIEW;


@ModelAttribute
public void getPageConfig(String tenantMark,Model model,HttpServletRequest request,HttpServletResponse response,HttpSession session){
    Map<String, String> pageConfig = new HashedMap<String, String>();
    if (StringUtils.isBlank(tenantMark) && null == session.getAttribute("tenantId")) {
        List<ConfigPageParamEntity> all = configPageParamService.listAll();
        for (ConfigPageParamEntity config : all) {
            pageConfig.put(config.getName(), config.getValue().replace("${inewsImageServer}", inewsImageServer));
        }
    } else if (StringUtils.isNotBlank(tenantMark) && null == session.getAttribute("tenantId")) {
        TenantEntity tenantEntity = tenantService.findByTenantMark(tenantMark);
        if (null != tenantEntity) {
            List<ConfigTenantParamEntity> list = configTenantParamService.findByTenantId(tenantEntity.getTenantId());
            for (ConfigTenantParamEntity config : list) {
                pageConfig.put(config.getParamName(), config.getValue().replace("${inewsImageServer}", inewsImageServer));
            }
        } else {
            List<ConfigPageParamEntity> all = configPageParamService.listAll();
            for (ConfigPageParamEntity config : all) {
                pageConfig.put(config.getName(), config.getValue().replace("${inewsImageServer}", inewsImageServer));
            }
        }
    } else if (StringUtils.isBlank(tenantMark) && null != session.getAttribute("tenantId")) {
        List<ConfigTenantParamEntity> list = configTenantParamService.findByTenantId(Integer.parseInt(session.getAttribute("tenantId") + ""));
        for (ConfigTenantParamEntity config : list) {
            pageConfig.put(config.getParamName(), config.getValue().replace("${inewsImageServer}", inewsImageServer));
        }
    } else if (StringUtils.isNotBlank(tenantMark) && null != session.getAttribute("tenantId")) {
        if (tenantMark.equals(session.getAttribute("tenantMark"))) {
            List<ConfigTenantParamEntity> list = configTenantParamService.findByTenantId(Integer.parseInt(session.getAttribute("tenantId") + ""));
            for (ConfigTenantParamEntity config : list) {
                pageConfig.put(config.getParamName(), config.getValue().replace("${inewsImageServer}", inewsImageServer));
            }
        } else {
            response.setStatus(403);
            throw new BusinessException("000000004");
        }
    } else {
        List<ConfigPageParamEntity> all = configPageParamService.listAll();
        for (ConfigPageParamEntity config : all) {
            pageConfig.put(config.getName(), config.getValue().replace("${inewsImageServer}", inewsImageServer));
        }
    }
    model.addAttribute("pageConfig", pageConfig);
}


@ResponseBody
@ExceptionHandler(value = BaseException.class)
public ModelMap baseExceptionHandler(HttpServletRequest req,BaseException e){
    log.error(this, e);
    return super.getModelMap(e);
}


@ResponseBody
@ExceptionHandler(value = Exception.class)
public ModelMap defaultExceptionHandler(HttpServletRequest req,Exception e){
    log.error(this, e);
    if ("/peoplesdaily".equals(req.getRequestURI())) {
        ModelMap map = new ModelMap();
        map.put("errorCode", 3);
        map.put("errorMsg", "Forbid  GET Requests!");
        return map;
    } else {
        return super.getModelMap(e);
    }
}


}