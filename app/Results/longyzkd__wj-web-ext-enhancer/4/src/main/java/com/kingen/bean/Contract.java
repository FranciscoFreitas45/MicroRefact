package com.kingen.bean;
 import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "t_contract")
public class Contract {

 private  String id;

 private  String contractName;

 private  String clientId;

 private  String clientContactId;

 private  String contractNo;

 private  String seviceLv;

 private  String contractType;

 private  BigDecimal contractAmt;

 private  String serviceBeginTime;

 private  String serviceEndTime;

 private  String desc;

 private  String procDefName;

 private  String unitName;

// Constructors
/**
 * default constructor
 */
public Contract() {
}/**
 * full constructor
 */
public Contract(String contractName, String clientId, String clientContactId, String contractNo, String seviceLv, String contractType, BigDecimal contractAmt, String serviceBeginTime, String serviceEndTime, String desc, String procDefName) {
    this.contractName = contractName;
    this.clientId = clientId;
    this.clientContactId = clientContactId;
    this.contractNo = contractNo;
    this.seviceLv = seviceLv;
    this.contractType = contractType;
    this.contractAmt = contractAmt;
    this.serviceBeginTime = serviceBeginTime;
    this.serviceEndTime = serviceEndTime;
    this.desc = desc;
    this.procDefName = procDefName;
}
@Transient
public String getUnitName(){
    return unitName;
}


@Column(name = "client_id", length = 32)
public String getClientId(){
    return this.clientId;
}


@Column(name = "service_end_time", length = 32)
public String getServiceEndTime(){
    return this.serviceEndTime;
}


@Column(name = "client_contact_id", length = 32)
public String getClientContactId(){
    return this.clientContactId;
}


@GenericGenerator(name = "generator", strategy = "uuid")
@Id
@GeneratedValue(generator = "generator")
@Column(name = "id", unique = true, nullable = false, length = 32)
public String getId(){
    return this.id;
}


public void setContractType(String contractType){
    this.contractType = contractType;
}


public void setServiceEndTime(String serviceEndTime){
    this.serviceEndTime = serviceEndTime;
}


@Column(name = "proc_def_name", length = 32)
public String getProcDefName(){
    return this.procDefName;
}


public void setId(String id){
    this.id = id;
}


public void setDesc(String desc){
    this.desc = desc;
}


@Column(name = "contract_no", length = 100)
public String getContractNo(){
    return this.contractNo;
}


public void setContractName(String contractName){
    this.contractName = contractName;
}


@Column(name = "contract_amt", precision = 12)
public BigDecimal getContractAmt(){
    return this.contractAmt;
}


@Column(name = "contract_name", length = 100)
public String getContractName(){
    return this.contractName;
}


public void setContractNo(String contractNo){
    this.contractNo = contractNo;
}


@Column(name = "sevice_lv", length = 10)
public String getSeviceLv(){
    return this.seviceLv;
}


public void setUnitName(String unitName){
    this.unitName = unitName;
}


public void setSeviceLv(String seviceLv){
    this.seviceLv = seviceLv;
}


@Column(name = "service_begin_time", length = 32)
public String getServiceBeginTime(){
    return this.serviceBeginTime;
}


@Column(name = "[desc]", length = 100)
public String getDesc(){
    return this.desc;
}


public void setClientContactId(String clientContactId){
    this.clientContactId = clientContactId;
}


public void setProcDefName(String procDefName){
    this.procDefName = procDefName;
}


@Column(name = "contract_type", length = 10)
public String getContractType(){
    return this.contractType;
}


public void setContractAmt(BigDecimal contractAmt){
    this.contractAmt = contractAmt;
}


public void setClientId(String clientId){
    this.clientId = clientId;
}


public void setServiceBeginTime(String serviceBeginTime){
    this.serviceBeginTime = serviceBeginTime;
}


}