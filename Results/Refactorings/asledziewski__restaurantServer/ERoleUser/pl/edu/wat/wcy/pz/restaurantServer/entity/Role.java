import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence;
@NoArgsConstructor
@Entity
public class Role {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "ROLE_ID")
 private  Long roleId;

@Column(name = "ROLE_NAME")
 private  String roleName;


public void setRoleId(Long roleId){
    this.roleId = roleId;
}


public String getRoleName(){
    return roleName;
}


public void setRoleName(String roleName){
    this.roleName = roleName;
}


public Long getRoleId(){
    return roleId;
}


}