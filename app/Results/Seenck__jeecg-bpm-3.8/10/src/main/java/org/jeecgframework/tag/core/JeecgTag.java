package org.jeecgframework.tag.core;
 import javax.servlet.jsp.tagext.TagSupport;
import org.apache.log4j.Logger;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.web.cgform.common.CgAutoListConstant;
import org.jeecgframework.web.cgform.engine.TempletContext;
import org.jeecgframework.web.system.controller.core.LoginController;
import org.jeecgframework.web.system.service.CacheServiceI;
public class JeecgTag extends TagSupport{

 private  Logger log;

 private  long serialVersionUID;


public StringBuffer getTagCache(){
    CacheServiceI cacheService = ApplicationContextUtil.getContext().getBean(CacheServiceI.class);
    if (CgAutoListConstant.SYS_MODE_DEV.equalsIgnoreCase(TempletContext._sysMode)) {
        return null;
    }
    log.debug("-----TagCache-----toString()-----" + toString());
    return (StringBuffer) cacheService.get(CacheServiceI.TAG_CACHE, toString());
}


public void putTagCache(StringBuffer tagCache){
    CacheServiceI cacheService = ApplicationContextUtil.getContext().getBean(CacheServiceI.class);
    cacheService.put(CacheServiceI.TAG_CACHE, toString(), tagCache);
}


}