package com.kingen.bean;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "t_contract_attach")
public class ContractAttach {

 private  String id;

 private  String contractId;

 private  byte[] attachContent;

 private  String attachName;

// Constructors
/**
 * default constructor
 */
public ContractAttach() {
}
public void setAttachContent(byte[] attachContent){
    this.attachContent = attachContent;
}


public void setContractId(String contract_id){
    this.contractId = contract_id;
}


@Column(name = "contract_id")
public String getContractId(){
    return contractId;
}


@Column(name = "attach")
public byte[] getAttachContent(){
    return attachContent;
}


public void setId(String id){
    this.id = id;
}


@GenericGenerator(name = "generator", strategy = "uuid")
@Id
@GeneratedValue(generator = "generator")
@Column(name = "id", unique = true, nullable = false, length = 32)
public String getId(){
    return this.id;
}


public void setAttachName(String attach_name){
    this.attachName = attach_name;
}


@Column(name = "attach_name")
public String getAttachName(){
    return attachName;
}


}