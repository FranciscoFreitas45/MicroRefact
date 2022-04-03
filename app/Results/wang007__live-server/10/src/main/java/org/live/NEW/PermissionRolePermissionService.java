package org.live.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.sys.repository.PermissionRepository;
import org.live.sys.entity.Permission;
@Service
public class PermissionRolePermissionService {

@Autowired
 private PermissionRepository permissionrepository;


public Permission getPermission(String idJL3E){
return permissionrepository.getPermission(idJL3E);
}


public void setPermission(String idJL3E,Permission permission){
permissionrepository.setPermission(idJL3E,permission);
}


}