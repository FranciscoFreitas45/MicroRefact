package com.yalcin.entity;
 import javax.persistence;
@Entity
@Table(name = "cloud_roles", schema = "public")
public class Role {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Integer id;

@Enumerated(EnumType.STRING)
@Column(name = "role", length = 60)
 private  Roles role;

@Column(name = "is_account_admin")
 private  Boolean isAccountAdmin;

public Role() {
}
public void setRole(Roles role){
    this.role = role;
}


public Roles getRole(){
    return role;
}


public void setId(Integer id){
    this.id = id;
}


public Integer getId(){
    return id;
}


}