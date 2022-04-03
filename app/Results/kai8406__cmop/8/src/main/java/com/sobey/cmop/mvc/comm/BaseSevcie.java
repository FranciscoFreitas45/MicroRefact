package com.sobey.cmop.mvc.comm;
 import javax.annotation.Resource;
import org.apache.shiro.SecurityUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import com.sobey.cmop.mvc.service.account.ShiroDbRealm.ShiroUser;
import com.sobey.framework.utils.PropertiesLoader;
import com.sobey.cmop.mvc.Interface.CommonService;
import com.sobey.cmop.mvc.Interface.PropertiesLoader;
public class BaseSevcie {

@Resource
 public  CommonService comm;

 public  PropertiesLoader CONFIG_LOADER;


public Integer getCurrentUserId(){
    ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
    return user != null ? user.id : null;
}


public PageRequest buildPageRequest(int pageNumber,int pagzSize,Sort sort){
    return new PageRequest(pageNumber - 1, pagzSize, sort);
}


}