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
@Entity(name = "aoa_holiday")
public class Holiday {

@Id
@Column(name = "holiday_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long holidayId;

@Column(name = "type_id")
 private  Long typeId;

@Column(name = "leave_days")
 private  Double leaveDays;

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


public Long getHolidayId(){
    return holidayId;
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


public void setPersonnelAdvice(String personnelAdvice){
    this.personnelAdvice = personnelAdvice;
}


public void setNameuser(String nameuser){
    this.nameuser = nameuser;
}


public ProcessList getProId(){
    return proId;
}


public void setManagerAdvice(String managerAdvice){
    this.managerAdvice = managerAdvice;
}


public String getNameuser(){
    return nameuser;
}


public Double getLeaveDays(){
    return leaveDays;
}


public void setLeaveDays(Double leaveDays){
    this.leaveDays = leaveDays;
}


public String getManagerAdvice(){
    return managerAdvice;
}


@Override
public String toString(){
    return "Holiday [holidayId=" + holidayId + ", typeId=" + typeId + ", leaveDays=" + leaveDays + ", personnelAdvice=" + personnelAdvice + ", managerAdvice=" + managerAdvice + ", nameuser=" + nameuser + "]";
}


public void setHolidayId(Long holidayId){
    this.holidayId = holidayId;
}


}