package com.csquard.mregister.model;
 import org.hibernate.annotations.NaturalId;
import javax.persistence;
@Entity
@Table(name = "roles")
public class Roles {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Enumerated(EnumType.STRING)
@NaturalId
@Column(length = 60)
 private  RoleName name;

public Roles() {
}public Roles(RoleName name) {
    this.name = name;
}
public void setName(RoleName name){
    this.name = name;
}


public RoleName getName(){
    return name;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


}