import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence;
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