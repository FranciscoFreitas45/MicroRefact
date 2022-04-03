package org.live.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.sys.repository.PermissionRepository;
import org.live.sys.entity.Permission;
@Service
public class PermissionPageElementService {

@Autowired
 private PermissionRepository permissionrepository;


public Permission getPermission(String idIJY1){
return permissionrepository.getPermission(idIJY1);
}


public void setPermission(String idIJY1,Permission permission){
permissionrepository.setPermission(idIJY1,permission);
}


}