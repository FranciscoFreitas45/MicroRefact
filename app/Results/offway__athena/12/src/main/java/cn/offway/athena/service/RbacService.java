package cn.offway.athena.service;
 import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
public interface RbacService {


public boolean hasPermission(HttpServletRequest request,Authentication authentication)
;

}