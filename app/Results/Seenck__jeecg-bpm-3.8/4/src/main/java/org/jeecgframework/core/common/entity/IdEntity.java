package org.jeecgframework.core.common.entity;
 import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;
@MappedSuperclass
public class IdEntity {

 private  long serialVersionUID;

 private  String id;


public void setId(String id){
    this.id = id;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 32)
public String getId(){
    return id;
}


}