package com.zammc.service.user;
 import com.zammc.domain.user.AdminUserEntity;
import com.zammc.response.Message;
import javax.servlet.http.HttpServletRequest;
public interface AdminUserService {


public Message userLogin(AdminUserEntity adminUserEntity,HttpServletRequest request)
;

}