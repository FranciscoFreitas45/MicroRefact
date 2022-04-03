package com.kingen.bean;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "t_lookup")
public class Lookup {

 private  String id;

 private  String type;

 private  String name;

 private  String desc;

// Constructors
/**
 * default constructor
 */
public Lookup() {
}/**
 * full constructor
 */
public Lookup(String type, String name, String desc) {
    this.type = type;
    this.name = name;
    this.desc = desc;
}
public void setName(String name){
    this.name = name;
}


@Column(name = "name", length = 50)
public String getName(){
    return this.name;
}


@Column(name = "type", length = 5)
public String getType(){
    return this.type;
}


public void setId(String id){
    this.id = id;
}


@Column(name = "[desc]", length = 100)
public String getDesc(){
    return this.desc;
}


@GenericGenerator(name = "generator", strategy = "uuid")
@Id
@GeneratedValue(generator = "generator")
@Column(name = "id", unique = true, nullable = false, length = 32)
public String getId(){
    return this.id;
}


public void setDesc(String desc){
    this.desc = desc;
}


public void setType(String type){
    this.type = type;
}


}