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
@Entity(name = "aoa_overtime")
public class Overtime {

@Id
@Column(name = "overtime_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long overtimeId;

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


public void setTypeId(Long typeId){
    this.typeId = typeId;
}


public void setOvertimeId(Long overtimeId){
    this.overtimeId = overtimeId;
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


@Override
public String toString(){
    return "Overtime [overtimeId=" + overtimeId + ", typeId=" + typeId + ", personnelAdvice=" + personnelAdvice + ", managerAdvice=" + managerAdvice + ", nameuser=" + nameuser + "]";
}


public Long getOvertimeId(){
    return overtimeId;
}


}