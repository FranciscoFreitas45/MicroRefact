package com.poseidon.company.dao.entities;
 import com.poseidon.init.entity.CommonEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "companyterms")
public class CompanyTerms extends CommonEntity{

@Column(name = "terms")
 private  String terms;

@Column(name = "companyAddress")
 private  String companyAddress;

@Column(name = "companyName")
 private  String companyName;

@Column(name = "companyPhone")
 private  String companyPhone;

@Column(name = "companyEmail")
 private  String companyEmail;

@Column(name = "companyWebsite")
 private  String companyWebsite;

@Column(name = "vatTin")
 private  String vatTin;

@Column(name = "cstTin")
 private  String cstTin;


public void setCompanyEmail(String companyEmail){
    this.companyEmail = companyEmail;
}


public void setVatTin(String vatTin){
    this.vatTin = vatTin;
}


public String getCompanyEmail(){
    return companyEmail;
}


public void setCompanyPhone(String companyPhone){
    this.companyPhone = companyPhone;
}


public void setCompanyName(String companyName){
    this.companyName = companyName;
}


public String getCompanyWebsite(){
    return companyWebsite;
}


public void setCompanyWebsite(String companyWebsite){
    this.companyWebsite = companyWebsite;
}


public String getCompanyPhone(){
    return companyPhone;
}


public void setCstTin(String cstTin){
    this.cstTin = cstTin;
}


public String getCompanyAddress(){
    return companyAddress;
}


public void setCompanyAddress(String companyAddress){
    this.companyAddress = companyAddress;
}


public String getCompanyName(){
    return companyName;
}


public String getVatTin(){
    return vatTin;
}


public String getCstTin(){
    return cstTin;
}


public void setTerms(String terms){
    this.terms = terms;
}


public String getTerms(){
    return terms;
}


}