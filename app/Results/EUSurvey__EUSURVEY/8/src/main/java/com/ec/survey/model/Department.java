package com.ec.survey.model;
 import javax.persistence;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "DEPARTMENTS")
public class Department {

 private  long serialVersionUID;

 private  Integer id;

 private  String name;

 private  String domainCode;

 private  List<Department> children;

public Department(String pname, String pdomainCode) {
    name = pname;
    domainCode = pdomainCode;
}public Department() {
}
public void setName(String name){
    this.name = name;
}


@Transient
public List<Department> getChildren(){
    return children;
}


@Column(name = "NAME")
public String getName(){
    return name;
}


@Column(name = "DOMAIN_CODE")
public String getDomainCode(){
    return domainCode;
}


public void setId(Integer id){
    this.id = id;
}


@Id
@Column(name = "DEP_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


public void setDomainCode(String domainCode){
    this.domainCode = domainCode;
}


public void setChildren(List<Department> children){
    this.children = children;
}


}