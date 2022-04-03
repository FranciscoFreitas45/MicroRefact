package cn.gson.oasys.model.entity.process;
 import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
@Table
@Entity(name = "aoa_evection")
public class Evection {

@Id
@Column(name = "evection_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long evectionId;

@Column(name = "type_id")
 private  Long typeId;

@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "pro_id")
 private  ProcessList proId;

@Column(name = "personnel_advice")
 private  String personnelAdvice;

@Column(name = "manager_advice")
 private  String managerAdvice;

@Transient
 private  String nameuser;


public String getPersonnelAdvice(){
    return personnelAdvice;
}


public Long getEvectionId(){
    return evectionId;
}


public void setTypeId(Long typeId){
    this.typeId = typeId;
}


public Long getTypeId(){
    return typeId;
}


public void setProId(ProcessList proId){
    this.proId = proId;
}


public void setNameuser(String nameuser){
    this.nameuser = nameuser;
}


public void setPersonnelAdvice(String personnelAdvice){
    this.personnelAdvice = personnelAdvice;
}


public ProcessList getProId(){
    return proId;
}


public String getNameuser(){
    return nameuser;
}


public void setManagerAdvice(String managerAdvice){
    this.managerAdvice = managerAdvice;
}


public String getManagerAdvice(){
    return managerAdvice;
}


public void setEvectionId(Long evectionId){
    this.evectionId = evectionId;
}


@Override
public String toString(){
    return "Evection [evectionId=" + evectionId + ", typeId=" + typeId + ", personnelAdvice=" + personnelAdvice + ", managerAdvice=" + managerAdvice + ", nameuser=" + nameuser + "]";
}


}