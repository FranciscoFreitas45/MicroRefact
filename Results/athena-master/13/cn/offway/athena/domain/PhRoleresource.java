import java.io.Serializable;
import javax.persistence;
import java.util.Comparator;
import java.util.Date;
@Entity
@Table(name = "ph_roleresource")
public class PhRoleresource implements Serializable{

 private  Long id;

 private  Date createdtime;

 private  Long resourceId;

 private  Long roleId;


public void setRoleId(Long roleId){
    this.roleId = roleId;
}


@Column(name = "resource_id", length = 20)
public Long getResourceId(){
    return resourceId;
}


public void setCreatedtime(Date createdtime){
    this.createdtime = createdtime;
}


@Override
public boolean equals(Object obj){
    return this.toString().equals(obj.toString());
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


@Override
public String toString(){
    return "PhRoleresource [id=" + id + ", createdtime=" + createdtime + ", resourceId=" + resourceId + ", roleId=" + roleId + "]";
}


public void setResourceId(Long resourceId){
    this.resourceId = resourceId;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "createdtime")
public Date getCreatedtime(){
    return createdtime;
}


}