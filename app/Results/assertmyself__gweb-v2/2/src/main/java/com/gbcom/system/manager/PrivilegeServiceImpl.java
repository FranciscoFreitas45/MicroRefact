package com.gbcom.system.manager;
 import com.hc.core.security.privilege.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
@SuppressWarnings("unchecked")
public class PrivilegeServiceImpl implements PrivilegeService{

@Autowired
 private  SysPrivilegeManager sysPrivilegeManager;


public List getAllPrivileges(){
    return sysPrivilegeManager.getAllPrivileges();
}


}