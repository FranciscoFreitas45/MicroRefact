package pl.edu.wat.wcy.pz.restaurantServer.DTO;
 import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence;
public class Role {

 private  Long roleId;

 private  String roleName;

public Role(Long roleId, String roleName) {
    this.roleId = roleId;
    this.roleName = roleName;
}
public String getRoleName(){
    return roleName;
}


public Long getRoleId(){
    return roleId;
}


}