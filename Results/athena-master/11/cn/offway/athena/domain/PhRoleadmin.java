import java.io.Serializable;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "ph_roleadmin")
public class PhRoleadmin implements Serializable{

 private  Long id;

 private  Date createdtime;

 private  Long adminId;

 private  Long roleId;


public void setRoleId(Long roleId){
    this.roleId = roleId;
}


public void setCreatedtime(Date createdtime){
    this.createdtime = createdtime;
}


public void setAdminId(Long adminId){
    this.adminId = adminId;
}


public void setId(Long id){
    this.id = id;
}


@Column(name = "role_id", length = 20)
public Long getRoleId(){
    return roleId;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Long getId(){
    return id;
}


@Column(name = "admin_id", length = 20)
public Long getAdminId(){
    return adminId;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "createdtime")
public Date getCreatedtime(){
    return createdtime;
}


}