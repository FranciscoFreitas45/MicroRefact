package cn.gson.oasys.model.entity.note;
 import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import cn.gson.oasys.model.entity.user.User;
import cn.gson.oasys.Request.UserRequest;
import cn.gson.oasys.Request.Impl.UserRequestImpl;
import cn.gson.oasys.DTO.User;
@Entity
@Table(name = "aoa_catalog")
public class Catalog {

@Id
@Column(name = "catalog_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long catalogId;

@Column(name = "catalog_name")
 private  String catalogName;

@Transient
 private  User user;

@Column(name = "parent_id")
 private  Integer parentId;

@Column(name = "userId")
 private Long userId;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

public Catalog() {
    super();
// TODO Auto-generated constructor stub
}public Catalog(String catalogName, User user) {
    super();
    this.catalogName = catalogName;
    this.user = user;
}
public void setCatalogName(String catalogName){
    this.catalogName = catalogName;
}


public String getCatalogName(){
    return catalogName;
}


public User getUser(){
  this.user = userrequest.getUser(this.userId);
return this.user;
}


@Override
public String toString(){
    return "Catalog [catalogName=" + catalogName + "]";
}


public void setCatalogId(Long catalogId){
    this.catalogId = catalogId;
}


public Long getCatalogId(){
    return catalogId;
}


public void setUser(User user){
 userrequest.setUser(user,this.userId);
}



}