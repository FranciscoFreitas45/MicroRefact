package DTO;
 import java.util.List;
import java.util.Map;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.UserQuery;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import com.google.common.collect.Maps;
import com.kingen.aop.ServiceLogAnnotation;
import com.kingen.bean.Menu;
import com.kingen.bean.SysOrgMenu;
import com.kingen.bean.User;
import com.kingen.repository.account.MenuDao;
import com.kingen.repository.account.UserDao;
import com.kingen.service.CommonService;
import com.kingen.util.BeanUtils;
import com.kingen.util.Constants;
import com.kingen.util.Digests;
import com.kingen.util.Encodes;
import com.kingen.util.Global;
import com.kingen.util.Page;
public class AccountService extends CommonService<User, String>{

 public  String HASH_ALGORITHM;

 public  int HASH_INTERATIONS;

 private  int SALT_SIZE;

 private  Logger logger;

 private  UserDao userDao;

 private  MenuDao menuDao;

 protected  IdentityService identityService;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";


public Page<User> getUsers(Page<User> page,User user){
    Page<User> p = userDao.findUsers(page, user);
    return p;
}


public String getCurrentUserName(){
    User user = (User) SecurityUtils.getSubject().getPrincipal();
    return user.getUsername();
}


@Transactional(readOnly = true)
public User findUserByLoginName(String loginName){
    return userDao.findByLoginName(loginName);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findUserByLoginName"))

.queryParam("loginName",loginName)
;
User aux = restTemplate.getForObject(builder.toUriString(),User.class);
return aux;
}


}