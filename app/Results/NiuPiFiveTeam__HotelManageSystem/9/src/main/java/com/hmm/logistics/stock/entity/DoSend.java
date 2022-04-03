package com.hmm.logistics.stock.entity;
 import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hmm.employee.entity.Employee;
import com.hmm.finance.logisticst.domain.InStorage;
import com.hmm.logistics.stock.util.InIn;
import com.hmm.Request.EmployeeRequest;
import com.hmm.Request.Impl.EmployeeRequestImpl;
import com.hmm.DTO.Employee;
import com.hmm.Request.InStorageRequest;
import com.hmm.Request.Impl.InStorageRequestImpl;
import com.hmm.DTO.InStorage;
@Entity
@Table(name = "t_doSend")
public class DoSend {

 private  Long id;

 private  InIn inin;

 private  Date doDate;

 private  Employee sendWorker;

 private  InStorage inAll;

@Column(name = "emp_idK9MW")
 private Integer emp_idK9MW;

@Transient
 private EmployeeRequest employeerequest = new EmployeeRequestImpl();;

@Column(name = "inStorageIdEAHF")
 private String inStorageIdEAHF;

@Transient
 private InStorageRequest instoragerequest = new InStorageRequestImpl();;


public InIn getInin(){
    return inin;
}


public void setSendWorker(Employee sendWorker){
this.emp_idK9MW = sendWorker.getSendworker() ;
employeerequest.setSendWorker(sendWorker,this.emp_idK9MW);
 this.sendWorker = sendWorker;
}



public void setDoDate(Date doDate){
    this.doDate = doDate;
}


@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
public Date getDoDate(){
    return doDate;
}


public void setInin(InIn inin){
    this.inin = inin;
}


public void setId(Long id){
    this.id = id;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Long getId(){
    return id;
}


@Transient
public Employee getSendWorker(){
  this.sendWorker = employeerequest.getSendWorker(this.emp_idK9MW);
return this.sendWorker;
}}



public void setInAll(InStorage inAll){
this.inStorageIdEAHF = inAll.getInall() ;
instoragerequest.setInAll(inAll,this.inStorageIdEAHF);
 this.inAll = inAll;
}



@Transient
public InStorage getInAll(){
  this.inAll = instoragerequest.getInAll(this.inStorageIdEAHF);
return this.inAll;
}}



}