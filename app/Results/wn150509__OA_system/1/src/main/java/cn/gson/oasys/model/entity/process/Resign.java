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
import cn.gson.oasys.model.entity.user.User;
import cn.gson.oasys.Interface.User;
@Table
@Entity(name = "aoa_resign")
public class Resign {

@Id
@Column(name = "resign_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long resignId;

 private  String suggest;

@Column(name = "is_finish")
 private  Boolean finish;

@OneToOne
@JoinColumn(name = "hand_user")
 private  User handUser;

 private  String nofinish;

@Column(name = "financial_advice")
 private  String financialAdvice;

@Column(name = "personnel_advice")
 private  String personnelAdvice;

@Column(name = "manager_advice")
 private  String managerAdvice;

@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "pro_id")
 private  ProcessList proId;

@Transient
 private  String nameuser;

@Transient
 private  String handuser;


public void setFinish(Boolean finish){
    this.finish = finish;
}


public String getPersonnelAdvice(){
    return personnelAdvice;
}


public void setNofinish(String nofinish){
    this.nofinish = nofinish;
}


public Boolean getFinish(){
    return finish;
}


public void setHanduser(String handuser){
    this.handuser = handuser;
}


public Long getResignId(){
    return resignId;
}


public void setProId(ProcessList proId){
    this.proId = proId;
}


public void setNameuser(String nameuser){
    this.nameuser = nameuser;
}


public String getHanduser(){
    return handuser;
}


public void setResignId(Long resignId){
    this.resignId = resignId;
}


public void setPersonnelAdvice(String personnelAdvice){
    this.personnelAdvice = personnelAdvice;
}


public ProcessList getProId(){
    return proId;
}


public void setSuggest(String suggest){
    this.suggest = suggest;
}


public void setManagerAdvice(String managerAdvice){
    this.managerAdvice = managerAdvice;
}


public String getNameuser(){
    return nameuser;
}


public String getSuggest(){
    return suggest;
}


public String getNofinish(){
    return nofinish;
}


public void setFinancialAdvice(String financialAdvice){
    this.financialAdvice = financialAdvice;
}


public String getManagerAdvice(){
    return managerAdvice;
}


@Override
public String toString(){
    return "Resign [resignId=" + resignId + ", suggest=" + suggest + ", finish=" + finish + ", handUser=" + handUser + ", nofinish=" + nofinish + ", financialAdvice=" + financialAdvice + ", personnelAdvice=" + personnelAdvice + ", managerAdvice=" + managerAdvice + ", nameuser=" + nameuser + ", handuser=" + handuser + "]";
}


public void setHandUser(User handUser){
    this.handUser = handUser;
}


public String getFinancialAdvice(){
    return financialAdvice;
}


public User getHandUser(){
    return handUser;
}


}