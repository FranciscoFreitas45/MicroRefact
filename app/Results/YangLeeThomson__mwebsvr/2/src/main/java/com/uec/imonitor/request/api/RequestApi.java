package com.uec.imonitor.request.api;
 import java.util.List;
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
import com.uec.imonitor.request.bean.RequestNewsEntity;
import com.uec.imonitor.request.bean.TenantRequestEntity;
import com.uec.imonitor.request.service.IRequestNewsService;
import com.uec.imonitor.request.service.ITenantRequestService;
import com.uec.imonitor.Interface.IRequestNewsService;
@Scope("prototype")
@RestController
@RequestMapping(value = "/{tenantMark}/api/request")
public class RequestApi extends BaseController{

 private  Logger logger;

@Autowired
@Qualifier("tenantRequestService")
 private  ITenantRequestService tenantRequestService;

@Autowired
@Qualifier("requestNewsService")
 private  IRequestNewsService requestNewsService;


@RequestMapping(value = "/getTenantRequest", method = RequestMethod.GET)
public ModelMap getTenantRequest(Integer tenantId,Model model){
    if (null == tenantId) {
        tenantId = (Integer) session.getAttribute("tenantId");
    }
    List<TenantRequestEntity> list = tenantRequestService.findByTenantId(tenantId);
    return super.getModelMap(list);
}


@RequestMapping(value = "/saveRequestNews", method = RequestMethod.POST)
public ModelMap saveRequestNews(RequestNewsEntity entity,Model model){
    requestNewsService.saveRequestNews(entity);
    return super.getModelMap("添加原创文章成功");
}


}