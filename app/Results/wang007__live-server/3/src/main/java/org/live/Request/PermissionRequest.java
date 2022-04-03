package org.live.Request;
import org.live.DTO.Permission;
public interface PermissionRequest {

   public Permission getPermission(String idIJY1);
   public void setPermission(Permission permission,String idIJY1);
}