package org.live.common.base;
 import org.hibernate.annotations.GenericGenerator;
import javax.persistence;
import java.io.Serializable;
@MappedSuperclass
public class BaseEntity implements Serializable{

 private  long serialVersionUID;

@Id
@GenericGenerator(name = "idGenerator", strategy = "org.hibernate.id.UUIDGenerator")
@GeneratedValue(generator = "idGenerator")
@Column(name = "id")
 protected  String id;

@Version
 protected  long version;


public long getVersion(){
    return version;
}


public void setVersion(long version){
    this.version = version;
}


public void setId(String id){
    this.id = id;
}


public String getId(){
    return id;
}


}