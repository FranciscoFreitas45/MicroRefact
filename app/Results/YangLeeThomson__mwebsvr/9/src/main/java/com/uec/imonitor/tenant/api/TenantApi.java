package com.uec.imonitor.tenant.api;
 import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.uec.imonitor.common.base.BaseController;
import com.uec.imonitor.common.exception.BaseException;
import com.uec.imonitor.tenant.bean.TenantEntity;
import com.uec.imonitor.tenant.service.ITenantService;
@Scope("prototype")
@RestController
@RequestMapping(value = "/{tenantMark}/api/tenant")
public class TenantApi extends BaseController{

 private  Logger logger;

@Autowired
@Qualifier("tenantService")
 private  ITenantService tenantService;


@RequestMapping(value = "/getTenant", method = RequestMethod.GET)
public ModelMap getTenant(Integer tenantId,Model model){
    if (null == tenantId) {
        tenantId = (Integer) session.getAttribute("tenantId");
    }
    TenantEntity entity = tenantService.findById(tenantId);
    return super.getModelMap(entity);
}


}