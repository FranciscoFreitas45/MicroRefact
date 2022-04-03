package com.ipe.common.entity;
 import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
@MappedSuperclass
public class IDEntity implements Serializable{

 private  String id;


public void setId(String id){
    this.id = id;
}


@Id
@GenericGenerator(name = "system-uuid", strategy = "uuid")
@GeneratedValue(generator = "system-uuid")
@Column(name = "id_", unique = true, nullable = false)
public String getId(){
    return id;
}


}