package com.yalcin.entity;
 import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import com.yalcin.Request.UserRequest;
import com.yalcin.Request.Impl.UserRequestImpl;
import com.yalcin.DTO.User;
@Entity
@Table(name = "total_order", schema = "public")
public class TotalOrder {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Integer id;

@Size(min = 1, max = 50)
@Column(name = "total_price")
 private  float totalPrice;

 private  boolean enabled;

 private  Date timestamp;

@Transient
 private  User user;

@ManyToMany
@JoinColumn(name = "store_id", nullable = false)
@JsonIgnoreProperties(value = { "enabled" })
 private  Set<Store> store;

@Column(name = "id")
 private Integer id;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

public TotalOrder() {
}
public void setEnabled(boolean enabled){
    this.enabled = enabled;
}


public void setTotalPrice(float totalPrice){
    this.totalPrice = totalPrice;
}


public Set<Store> getStore(){
    return store;
}


public Date getTimestamp(){
    return timestamp;
}


public boolean isEnabled(){
    return enabled;
}


public User getUser(){
  this.user = userrequest.getUser(this.id);
return this.user;
}


public void setId(Integer id){
    this.id = id;
}


public void setStore(Set<Store> store){
    this.store = store;
}


public Integer getId(){
    return id;
}


public float getTotalPrice(){
    return totalPrice;
}


public void setUser(User user){
 userrequest.setUser(user,this.id);
}



public void setTimestamp(Date timestamp){
    this.timestamp = timestamp;
}


}