package cn.gson.oasys.model.entity.process;
 import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name = "aoa_evectionmoney")
public class EvectionMoney {

@Id
@Column(name = "evectionmoney_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long evectionmoneyId;

 private  Double money;

 private  String name;

@Column(name = "manager_advice")
 private  String managerAdvice;

@Column(name = "financial_advice")
 private  String financialAdvice;

@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "pro_id")
 private  ProcessList proId;

 private  Long pro;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "evection")
 private List<Traffic> traffic;

@OneToMany(cascade = CascadeType.ALL, mappedBy = "evemoney")
 private List<Stay> stay;

@Transient
 private  String shenname;


public void setName(String name){
    this.name = name;
}


public void setMoney(Double money){
    this.money = money;
}


public String getName(){
    return name;
}


public List<Traffic> getTraffic(){
    return traffic;
}


public void setPro(Long pro){
    this.pro = pro;
}


public List<Stay> getStay(){
    return stay;
}


public void setStay(List<Stay> stay){
    this.stay = stay;
}


public Long getEvectionmoneyId(){
    return evectionmoneyId;
}


public void setProId(ProcessList proId){
    this.proId = proId;
}


public ProcessList getProId(){
    return proId;
}


public void setManagerAdvice(String managerAdvice){
    this.managerAdvice = managerAdvice;
}


public void setEvectionmoneyId(Long evectionmoneyId){
    this.evectionmoneyId = evectionmoneyId;
}


public void setFinancialAdvice(String financialAdvice){
    this.financialAdvice = financialAdvice;
}


public String getManagerAdvice(){
    return managerAdvice;
}


public void setShenname(String shenname){
    this.shenname = shenname;
}


@Override
public String toString(){
    return "EvectionMoney [evectionmoneyId=" + evectionmoneyId + ", money=" + money + ", name=" + name + ", managerAdvice=" + managerAdvice + ", financialAdvice=" + financialAdvice + ", pro=" + pro + ", shenname=" + shenname + "]";
}


public String getShenname(){
    return shenname;
}


public Double getMoney(){
    return money;
}


public void setTraffic(List<Traffic> traffic){
    this.traffic = traffic;
}


public String getFinancialAdvice(){
    return financialAdvice;
}


public Long getPro(){
    return pro;
}


}