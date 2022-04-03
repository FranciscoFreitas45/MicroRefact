package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;
import com.ukefu.util.UKTools;
@Document(indexName = "uckefu", type = "uk_entcustomer")
@Entity
@Table(name = "uk_entcustomer")
@org.hibernate.annotations.Proxy(lazy = false)
public class EntCustomer extends ESBean{

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  String etype;

 private  String ekind;

 private  String maturity;

 private  String elevel;

 private  String ecode;

 private  String nickname;

 private  String esource;

 private  String organ;

 private  String corporation;

 private  String leadername;

 private  String leadermobile;

 private  String leadermobile2;

 private  String leaderphone;

 private  String leaderemail;

 private  String website;

 private  String email;

 private  String emailalt;

 private  String phone;

 private  String phonealt;

 private  String fax;

 private  String faxalt;

 private  String country;

 private  String province;

 private  String entcusid;

 private  String city;

 private  String sarea;

 private  String address;

 private  String postcode;

 private  String businessscope;

 private  String capital;

 private  String stockcode;

 private  String bankaccount;

 private  String registeredaddress;

 private  String esize;

 private  String industry;

 private  String validstatus;

 private  String weixin;

 private  String weibo;

 private  Date touchtime;

 private  String dzip;

 private  String daddress;

 private  String darea;

 private  String dcity;

 private  String dprovince;

 private  boolean datastatus;

 private  String processid;

 private  String description;

 private  String creater;

 private  String username;

 private  String updateuser;

 private  String updateusername;

 private  Date updatetime;

 private  String orgi;

 private  Date createtime;

 private  String assignedto;

 private  String wfstatus;

 private  String shares;

 private  String owner;

 private  String datadept;

 private  String batid;

 private  String pinyin;


public String getRegisteredaddress(){
    return registeredaddress;
}


public String getPostcode(){
    return postcode;
}


public void setProvince(String province){
    this.province = province;
}


public void setShares(String shares){
    this.shares = shares;
}


public void setProcessid(String processid){
    this.processid = processid;
}


public String getEtype(){
    return etype;
}


public String getBatid(){
    return batid;
}


public void setDatastatus(boolean datastatus){
    this.datastatus = datastatus;
}


public String getOwner(){
    return owner;
}


public void setLeaderphone(String leaderphone){
    this.leaderphone = leaderphone;
}


public void setOwner(String owner){
    this.owner = owner;
}


public void setBatid(String batid){
    this.batid = batid;
}


public void setUpdateusername(String updateusername){
    this.updateusername = updateusername;
}


public String getLeaderphone(){
    return leaderphone;
}


public void setEtype(String etype){
    this.etype = etype;
}


public void setCorporation(String corporation){
    this.corporation = corporation;
}


public String getLeadermobile(){
    return leadermobile;
}


public void setTouchtime(Date touchtime){
    this.touchtime = touchtime;
}


public String getProcessid(){
    return processid;
}


public void setNickname(String nickname){
    this.nickname = nickname;
}


public String getIndustry(){
    return industry;
}


public String getEmailalt(){
    return emailalt;
}


public void setName(String name){
    this.name = name;
}


public String getPhone(){
    return phone;
}


public String getStockcode(){
    return stockcode;
}


public void setIndustry(String industry){
    this.industry = industry;
}


public String getEcode(){
    return ecode;
}


public void setAssignedto(String assignedto){
    this.assignedto = assignedto;
}


public void setDescription(String description){
    this.description = description;
}


public void setDatadept(String datadept){
    this.datadept = datadept;
}


public void setWeibo(String weibo){
    this.weibo = weibo;
}


public String getUsername(){
    return username;
}


public String getUpdateusername(){
    return updateusername;
}


public String getShares(){
    return shares;
}


public String getEsize(){
    return esize;
}


public void setEsize(String esize){
    this.esize = esize;
}


public String getDcity(){
    return dcity;
}


public Date getCreatetime(){
    return createtime;
}


public void setMaturity(String maturity){
    this.maturity = maturity;
}


public void setDcity(String dcity){
    this.dcity = dcity;
}


public String getProvince(){
    return province;
}


public void setFax(String fax){
    this.fax = fax;
}


public void setEsource(String esource){
    this.esource = esource;
}


public String getLeadername(){
    return leadername;
}


public void setBankaccount(String bankaccount){
    this.bankaccount = bankaccount;
}


public void setLeadermobile2(String leadermobile2){
    this.leadermobile2 = leadermobile2;
}


public void setValidstatus(String validstatus){
    this.validstatus = validstatus;
}


public void setEntcusid(String entcusid){
    this.entcusid = entcusid;
}


public void setLeadername(String leadername){
    this.leadername = leadername;
}


public String getWeibo(){
    return weibo;
}


public String getFax(){
    return fax;
}


public void setDarea(String darea){
    this.darea = darea;
}


public String getDarea(){
    return darea;
}


public void setEcode(String ecode){
    this.ecode = ecode;
}


public void setWeixin(String weixin){
    this.weixin = weixin;
}


public String getPhonealt(){
    return phonealt;
}


public void setCountry(String country){
    this.country = country;
}


public String getName(){
    return name;
}


public String getCountry(){
    return country;
}


public void setBusinessscope(String businessscope){
    this.businessscope = businessscope;
}


public String getCapital(){
    return capital;
}


public void setElevel(String elevel){
    this.elevel = elevel;
}


public void setFaxalt(String faxalt){
    this.faxalt = faxalt;
}


public String getMaturity(){
    return maturity;
}


public void setDaddress(String daddress){
    this.daddress = daddress;
}


public void setCapital(String capital){
    this.capital = capital;
}


public String getLeadermobile2(){
    return leadermobile2;
}


public void setSarea(String sarea){
    this.sarea = sarea;
}


public String getDprovince(){
    return dprovince;
}


public void setId(String id){
    this.id = id;
}


public String getEkind(){
    return ekind;
}


public void setStockcode(String stockcode){
    this.stockcode = stockcode;
}


public String getCity(){
    return city;
}


public String getWebsite(){
    return website;
}


public void setCity(String city){
    this.city = city;
}


public String getUpdateuser(){
    return updateuser;
}


public String getElevel(){
    return elevel;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setPhone(String phone){
    this.phone = phone;
}


public String getPinyin(){
    return pinyin;
}


public String getWeixin(){
    return weixin;
}


public void setOrgan(String organ){
    this.organ = organ;
}


public String getLeaderemail(){
    return leaderemail;
}


public String getValidstatus(){
    return validstatus;
}


public String getOrgan(){
    return organ;
}


public void setEmail(String email){
    this.email = email;
}


public String getBankaccount(){
    return bankaccount;
}


public void setPostcode(String postcode){
    this.postcode = postcode;
}


public void setPhonealt(String phonealt){
    this.phonealt = phonealt;
}


public String getEsource(){
    return esource;
}


public String getEmail(){
    return email;
}


public void setCreater(String creater){
    this.creater = creater;
}


public String getEntcusid(){
    return entcusid != null ? entcusid : id;
}


public void setWfstatus(String wfstatus){
    this.wfstatus = wfstatus;
}


public void setLeaderemail(String leaderemail){
    this.leaderemail = leaderemail;
}


public void setUpdateuser(String updateuser){
    this.updateuser = updateuser;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "assigned")
public String getId(){
    return id;
}


public String getDzip(){
    return dzip;
}


public String getDescription(){
    return description;
}


public void setPinyin(String pinyin){
    this.pinyin = pinyin;
}


public String getFaxalt(){
    return faxalt;
}


public String getCorporation(){
    return corporation;
}


public String getAssignedto(){
    return assignedto;
}


public void setEkind(String ekind){
    this.ekind = ekind;
}


public String getAddress(){
    return address;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getBusinessscope(){
    return businessscope;
}


public void setWebsite(String website){
    this.website = website;
}


public String getDaddress(){
    return daddress;
}


public String getDatadept(){
    return datadept;
}


public void setRegisteredaddress(String registeredaddress){
    this.registeredaddress = registeredaddress;
}


public void setAddress(String address){
    this.address = address;
}


public void setUsername(String username){
    this.username = username;
}


public String getSarea(){
    return sarea;
}


public void setDprovince(String dprovince){
    this.dprovince = dprovince;
}


public void setLeadermobile(String leadermobile){
    this.leadermobile = leadermobile;
}


public String getNickname(){
    return nickname;
}


public void setDzip(String dzip){
    this.dzip = dzip;
}


public String getOrgi(){
    return orgi;
}


public String getWfstatus(){
    return wfstatus;
}


public Date getTouchtime(){
    return touchtime;
}


public boolean isDatastatus(){
    return datastatus;
}


public void setEmailalt(String emailalt){
    this.emailalt = emailalt;
}


}