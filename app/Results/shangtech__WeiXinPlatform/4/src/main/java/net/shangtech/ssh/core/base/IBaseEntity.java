package net.shangtech.ssh.core.base;
 import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
@MappedSuperclass
public class IBaseEntity implements Serializable{

 private  long serialVersionUID;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Integer id;


public void setId(Integer id){
    this.id = id;
}


public Integer getId(){
    return id;
}


}