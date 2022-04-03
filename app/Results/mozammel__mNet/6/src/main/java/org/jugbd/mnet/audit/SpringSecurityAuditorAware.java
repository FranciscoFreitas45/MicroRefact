package org.jugbd.mnet.audit;
 import org.jugbd.mnet.domain.User;
import org.jugbd.mnet.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
@Component
public class SpringSecurityAuditorAware implements AuditorAware<User>{

 private  Logger log;

@Autowired
 private  UserService userService;


@Override
public User getCurrentAuditor(){
    log.info("getCurrentAuditor()");
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return userService.findById(user.getId());
}


}