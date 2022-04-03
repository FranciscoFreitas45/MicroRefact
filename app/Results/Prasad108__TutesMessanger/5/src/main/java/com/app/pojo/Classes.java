package com.app.pojo;
 import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "classes", catalog = "tutesmessanger")
public class Classes {

 private  Integer id;

 private  Branch branch;

 private  String name;

 private  Set<Division> divisions;

 private  Set<Division> divisions_1;

public Classes() {
}public Classes(Branch branch, String name) {
    super();
    this.branch = branch;
    this.name = name;
}public Classes(Branch branch, String name, Set<Division> divisions, Set<Division> divisions_1) {
    this.branch = branch;
    this.name = name;
    this.divisions = divisions;
    this.divisions_1 = divisions_1;
}
public void setName(String name){
    this.name = name;
}


@Column(name = "name", length = 50)
public String getName(){
    return this.name;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "classes")
public Set<Division> getDivisions(){
    return this.divisions;
}


public void setDivisions_1(Set<Division> divisions_1){
    this.divisions_1 = divisions_1;
}


public void setId(Integer id){
    this.id = id;
}


@OneToMany(fetch = FetchType.LAZY, mappedBy = "classes")
public Set<Division> getDivisions_1(){
    return this.divisions_1;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "branchid")
public Branch getBranch(){
    return this.branch;
}


public void setDivisions(Set<Division> divisions){
    this.divisions = divisions;
}


public void setBranch(Branch branch){
    this.branch = branch;
}


}