package pl.edu.wat.wcy.pz.restaurantServer.DTO;
 import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class Role {


 private  Long roleId;

 private  String roleName;




public String getRoleName(){
    return roleName;
}




public Long getRoleId(){
    return roleId;
}


}